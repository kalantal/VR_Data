package com.stmk.sddatavr.search

import com.google.gson.Gson
import com.stmk.sddatavr.search.models.Occur
import com.stmk.sddatavr.search.models.Query
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.Client
import org.elasticsearch.index.query.*
import org.elasticsearch.index.reindex.BulkByScrollResponse
import org.elasticsearch.index.reindex.DeleteByQueryAction

/**
 * Generic class that contains methods to retrieve records from the Elasticsearch cluster using the search client.
 * Most Dao's should extend this class to avoid writing a lot of duplicate code. The generic T should be the record class
 *
 * @param searchClient the default search client to connect to Elasticsearch
 * @param gson the Json -> Java converter
 * @param index the name of the index in Elasticsearch for the current dataset
 * @param indexType the name of the indexType in Elastisearch for the current dataset
 * @param recordClazz the data class for the Elasticsearch record which extends AbstractElasticsearchRecord
 *
 * @see AbstractElasticsearchRecord
 * @author Krishna Chaitanya Kandula
 * @since 12-07-2017
 */
abstract class AbstractDao<T : AbstractElasticsearchRecord>(protected val searchClient: Client,
                                                            protected val gson: Gson,
                                                            protected val index: String,
                                                            protected val indexType: String,
                                                            protected val recordClazz: Class<T>) : Dao<T> {

    private companion object {
        //The max number of items to return in a query
        val QUERY_RESPONSE_SIZE = 50
    }

    /**
     * Returns all items from a certain index with a pagination token
     *
     * @param paginationToken the pagination token from the last response, or 0
     * @return the response containing all the records in the index and pagination token
     */
    override fun getAll(paginationToken: Int): AbstractResponse<T> {
        val response: SearchResponse = searchClient.prepareSearch()
                .setIndices(index)
                .setTypes(indexType)
                .setSize(QUERY_RESPONSE_SIZE)
                .setFrom(paginationToken)
                .setQuery(QueryBuilders.matchAllQuery())
                .execute()
                .actionGet()

        val formattedResponse: List<T> = response.hits.map { gson.fromJson(it.sourceAsString, recordClazz) }
        return AbstractResponse(formattedResponse, paginationToken + formattedResponse.size)
    }

    /**
     * Returns the item from the index with matching id
     *
     * @param id the id of the desired item
     * @return the item with the matching id
     */
    override fun getWithId(id: Long): T {
        val response: SearchResponse = searchClient.prepareSearch()
                .setIndices(index)
                .setTypes(indexType)
                .setSize(QUERY_RESPONSE_SIZE)
                .setQuery(QueryBuilders.matchQuery("id", id))
                .execute()
                .actionGet()

        return response.hits.map { gson.fromJson(it.sourceAsString, recordClazz) }.first()
    }

    /**
     * Adds an item to the Elasticsearch index
     *
     * @param data the item to add
     * return true if the item was added to the cluster, false otherwise
     */
    override fun add(data: T): Boolean {
        val jsonStr: String = gson.toJson(data)
        return try {
            searchClient.prepareIndex(index, indexType)
                    .setSource(jsonStr)
                    .execute()
                    .actionGet()
            true
        } catch (e: Exception) {
            //TODO: Change to Log
            println(e.message)
            false
        }
    }

    /**
     * Deletes an item in the Elasticsearch cluster given an id if it exists
     *
     * @param id the id of the item to delete
     * @return true if the item was deleted, false otherwise
     */
    override fun deleteWithId(id: Long): Boolean {
        val response: BulkByScrollResponse = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(searchClient)
                .filter(QueryBuilders.matchQuery("id", id))
                .source(index)
                .get()

        return response.deleted.toInt() != 0
    }

    /**
     * Converts a query to an Elasticsearch query and queries the cluster using the pagination token
     *
     * @param queries
     * @param paginationToken
     * @return the response containing the items that matched the query and the corresponding pagination token
     */
    override fun getWithQuery(queries: List<Query>, paginationToken: Int): AbstractResponse<T> {
        var qb = BoolQueryBuilder()
        queries.forEach({ query ->
            val matchQuery: QueryBuilder = createQuery(query)
            qb = when (query.occurance) {
                Occur.MUST -> qb.must(matchQuery)
                Occur.MUST_NOT -> qb.mustNot(matchQuery)
                Occur.SHOULD -> qb.should(matchQuery)
            }
        })


        val response: SearchResponse = searchClient.prepareSearch()
                .setIndices(index)
                .setTypes(indexType)
                .setSize(QUERY_RESPONSE_SIZE)
                .setQuery(qb)
                .setFrom(paginationToken)
                .execute()
                .actionGet()

        val formattedResponse: List<T> = response.hits.map { gson.fromJson(it.sourceAsString, recordClazz) }
        return AbstractResponse(formattedResponse, paginationToken + formattedResponse.size)
    }

    /**
     * Creates an Elasticsearch query given a query from the frontend
     *
     * @param query the query created by the frontend
     * @return the Elasticsearch query builder
     */
    private fun createQuery(query: Query): BoolQueryBuilder {
        var qb = BoolQueryBuilder()
        val queryList: MutableList<QueryBuilder> = mutableListOf()

        if (query.field != null && query.value != null) {
            val matchQuery: MatchQueryBuilder = QueryBuilders.matchQuery(query.field, query.value)
            queryList.add(matchQuery)

        } else if (query.nestedQueries != null) {
            query.nestedQueries.forEach { queryList.add(createQuery(it)) }
        }

        queryList.forEach { nestedQuery ->
            qb = when (query.occurance) {
                Occur.MUST -> qb.must(nestedQuery)
                Occur.MUST_NOT -> qb.mustNot(nestedQuery)
                Occur.SHOULD -> qb.should(nestedQuery)
            }
        }
        return qb
    }
}
