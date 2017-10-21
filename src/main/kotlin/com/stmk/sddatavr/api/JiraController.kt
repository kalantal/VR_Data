package com.stmk.sddatavr.api

import com.stmk.sddatavr.search.jiradataset.JiraDao
import com.stmk.sddatavr.search.jiradataset.JiraTicket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Krishna Chaitanya Kandula on 10/13/17.
 */
@RestController
@RequestMapping("/jira")
class JiraController @Autowired constructor(private val jiraDao: JiraDao) {

    @RequestMapping("", method = arrayOf(RequestMethod.GET))
    fun mapGetAllJiraTickets(): List<JiraTicket> = jiraDao.getAll()

    @RequestMapping("{id}")
    fun mapGetJiraTicketWithId(@RequestParam("id") id: Long): JiraTicket = jiraDao.getTicketWithId(id)
}
