package com.stmk.sddatavr.search

import com.google.gson.Gson
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.Client
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.reindex.BulkByScrollResponse
import org.elasticsearch.index.reindex.DeleteByQueryAction

/**
 * Created by Krishna Chaitanya Kandula on 10/5/17.
 */

abstract class AbstractDao<T : AbstractElasticsearchRecord>(protected val searchClient: Client,
                                                            protected val gson: Gson,
                                                            protected val index: String,
                                                            protected val indexType: String,
                                                            protected val recordClazz: Class<T>) : Dao<T> {

    override fun getAll(): List<T> {
        val response: SearchResponse = searchClient.prepareSearch()
                .setIndices(index)
                .setTypes(indexType)
                .setQuery(QueryBuilders.matchAllQuery())
                .execute()
                .actionGet()

        return response.hits.map { gson.fromJson(it.sourceAsString, recordClazz) }
    }

    override fun getWithId(id: Long): T {
        val response: SearchResponse = searchClient.prepareSearch()
                .setIndices(index)
                .setTypes(indexType)
                .setQuery(QueryBuilders.matchQuery("id", id))
                .execute()
                .actionGet()

        return response.hits.map { gson.fromJson(it.sourceAsString, recordClazz) }.first()
    }

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

    override fun deleteWithId(id: Long): Boolean {
        val response: BulkByScrollResponse = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(searchClient)
                .filter(QueryBuilders.matchQuery("id", id))
                .source(index)
                .get()

        return response.deleted.toInt() != 0
    }
}
