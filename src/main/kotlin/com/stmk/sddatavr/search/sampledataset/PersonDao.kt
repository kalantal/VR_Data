package com.stmk.sddatavr.search.sampledataset

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
interface PersonDao {

    fun getAllPeople(): List<Person>

    fun getWithId(id: Long): Person

    fun getWithName(name: String): List<Person>

    fun deleteWithId(id: Long): Boolean
}
