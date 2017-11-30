package com.stmk.sddatavr.api

import com.stmk.sddatavr.search.AbstractResponse
import com.stmk.sddatavr.search.dataset.jira.JiraDao
import com.stmk.sddatavr.search.dataset.jira.JiraTicket
import com.stmk.sddatavr.search.models.PaginationWrapper
import com.stmk.sddatavr.search.models.QueryListWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Krishna Chaitanya Kandula on 10/13/17.
 */
@RestController
class JiraController @Autowired constructor(private val jiraDao: JiraDao) {

    @RequestMapping("/jira", method = arrayOf(RequestMethod.POST), consumes = arrayOf("application/json"))
    @ResponseBody
    fun mapGetAllJiraTickets(@RequestBody query: PaginationWrapper): AbstractResponse<JiraTicket> = jiraDao.getAll(query)

    @RequestMapping("/jira/id", method = arrayOf(RequestMethod.GET))
    fun mapGetJiraTicketWithId(@RequestParam("id", required = true) id: Long): JiraTicket = jiraDao.getTicketWithId(id)

    @RequestMapping("/jira/queries", method = arrayOf(RequestMethod.POST), consumes = arrayOf("application/json"))
    @ResponseBody
    fun mapGetJiraTicketsMultiQuery(@RequestBody queries: QueryListWrapper): AbstractResponse<JiraTicket>
            = jiraDao.getTicketsWithQueries(queries)
}
