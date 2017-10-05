package com.stmk.sddatavr.search.sampledataset

import org.springframework.stereotype.Component
import java.util.*

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
@Component
class PersonDaoImpl() : PersonDao {
    override fun getAllPersons(): List<Person> {
        return listOf(Person("Shane", 21, null),
                Person("Mandy", 21, null))
    }

    override fun getPersonsWithName(name: String): List<Person> {
        return listOf(Person("Krishna", 21, null))
    }
}