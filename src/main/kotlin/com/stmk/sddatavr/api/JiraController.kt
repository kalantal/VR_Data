package com.stmk.sddatavr.api

import com.stmk.sddatavr.search.AbstractResponse
import com.stmk.sddatavr.search.dataset.jira.JiraDao
import com.stmk.sddatavr.search.dataset.jira.JiraTicket
import com.stmk.sddatavr.search.models.QueryListWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * The REST controller for the Jira dataset
 *
 * @param jiraDao the Jira DAO implementation, injected using Spring DI
 * @author Krishna Chaitanya Kandula
 * @since 12-07-2017
 */
@RestController
class JiraController @Autowired constructor(private val jiraDao: JiraDao) {

    /**
     * GET /jira
     *
     * REST method that returns all Jira tickets in the index and a pagination token. Uses the JiraDao implementation
     * to retrieve the tickets
     *
     * @param paginationToken the request parameter for the pagination token
     * @return the response containing Jira tickets and the pagination token
     */
    @RequestMapping("/jira", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun mapGetAllJiraTickets(@RequestParam paginationToken: Int): AbstractResponse<JiraTicket> = jiraDao.getAll(paginationToken)

    /**
     * GET /jira/id
     *
     * REST method that returns a Jira ticket with the corresponding id
     *
     * @param id the request parameter containing the id of the desired Jira ticket
     * @return the Jira ticket with the id if it exists
     */
    @RequestMapping("/jira/id", method = arrayOf(RequestMethod.GET))
    fun mapGetJiraTicketWithId(@RequestParam("id", required = true) id: Long): JiraTicket = jiraDao.getTicketWithId(id)

    @RequestMapping("/jira/queries", method = arrayOf(RequestMethod.POST), consumes = arrayOf("application/json"))
    @ResponseBody
    fun mapGetJiraTicketsMultiQuery(@RequestBody queries: QueryListWrapper): AbstractResponse<JiraTicket>
            = jiraDao.getTicketsWithQueries(queries)
}
