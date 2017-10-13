package com.stmk.sddatavr.api

import com.stmk.sddatavr.search.sampledataset.Person
import com.stmk.sddatavr.search.sampledataset.PersonDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
@RestController
class PersonController @Autowired constructor(private val personDao: PersonDao) {

    @RequestMapping("/person", method = arrayOf(RequestMethod.GET))
    fun getPersons(): List<Person> = personDao.getAllPeople()

    @RequestMapping("/person/{name}", method = arrayOf(RequestMethod.GET))
    fun getPersonsWithName(@PathVariable("name") name: String): List<Person> = personDao.getWithName(name)

    //TODO: Add add() function to PersonDao interface
    @RequestMapping("/person", method = arrayOf(RequestMethod.POST))
    fun postPerson(): Boolean = false
}
