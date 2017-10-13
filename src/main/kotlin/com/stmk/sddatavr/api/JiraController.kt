package com.stmk.sddatavr.api

import com.stmk.sddatavr.search.jiradataset.JiraDao
import com.stmk.sddatavr.search.jiradataset.JiraTicket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Krishna Chaitanya Kandula on 10/13/17.
 */
@RestController
class JiraController @Autowired constructor(private val jiraDao: JiraDao){

    @RequestMapping("/jira", method = arrayOf(RequestMethod.GET))
    fun mapGetAllJiraTickets(): List<JiraTicket> = jiraDao.getAll()
}
