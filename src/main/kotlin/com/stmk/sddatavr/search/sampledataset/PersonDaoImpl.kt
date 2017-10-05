package com.stmk.sddatavr.search.sampledataset

import com.google.gson.Gson
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.Client
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
@Component
class PersonDaoImpl @Autowired constructor(private val searchClient: Client,
                                           private val gson: Gson) : PersonDao {

    override fun getAllPersons(): List<Person> {
        return listOf(Person("Shane", 21, null),
                Person("Mandy", 21, null))
    }

    override fun getPersonsWithName(name: String): List<Person> {
        val query: QueryBuilder = QueryBuilders.matchQuery("name", name)
        val response = searchClient.prepareSearch()
                .setIndices("people")
                .setTypes("person")
                .setQuery(query)
                .execute()
                .actionGet()

        val results: List<Person> = mutableListOf()
        response.hits.forEach({gson.fromJson(it.sourceAsString, Person::class.java)})
        return results
    }

    override fun addPerson(person: Person): Boolean {
        val jsonStr: String = gson.toJson(person)
        val response: IndexResponse = searchClient.prepareIndex("people", "person")
                .setSource(jsonStr)
                .execute()
                .actionGet()

        return response.isCreated
    }
}