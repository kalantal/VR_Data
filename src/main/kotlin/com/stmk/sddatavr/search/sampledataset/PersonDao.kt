package com.stmk.sddatavr.search.sampledataset

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
interface PersonDao {

    fun getAllPersons(): List<Person>

    fun getPersonsWithName(name: String): List<Person>

    fun addPerson(person: Person): Boolean
}