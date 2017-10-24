package com.stmk.sddatavr.search.dataset.sample

import com.google.gson.Gson
import com.stmk.sddatavr.search.AbstractDao
import org.elasticsearch.client.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
@Component
class PersonDaoImpl
    @Autowired
    constructor(searchClient: Client,
                gson: Gson) : AbstractDao<Person>(searchClient, gson, index = "people", indexType = "person", recordClazz = Person::class.java), PersonDao {

    override fun getAllPeople(): List<Person> = super.getAll()

    override fun getWithName(name: String): List<Person> {
        return listOf()
    }
}
