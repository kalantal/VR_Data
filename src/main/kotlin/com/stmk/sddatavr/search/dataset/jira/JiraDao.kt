package com.stmk.sddatavr.search.dataset.jira

import com.stmk.sddatavr.search.AbstractResponse
import com.stmk.sddatavr.search.models.QueryListWrapper

/**
 * This is an interface for interacting with Jira data. Extend this to develop your own custom functionality
 * for retrieving Jira tickets. The default implementation is JiraDaoImpl which is linked below.
 *
 * @see com.stmk.sddatavr.search.dataset.jira.JiraDaoImpl
 * @author Krishna Chaitanya Kandula
 * @since 12-07-2017
 */
interface JiraDao {

    /**
     * Retrieves all Jira tickets using pagination
     *
     * @param paginationToken The query containing the pagination token
     * @return The response containing the Jira tickets that match the query, and the pagination token
     */
    fun getAll(paginationToken: Int): AbstractResponse<JiraTicket>

    /**
     * Retrieves a Jira ticket with a specific id if it exists
     *
     * //TODO: check for null returns and update REST controller
     *
     * @param id the id of the Jira ticket
     * @return the Jira ticket with the matching id if it exists
     */
    fun getTicketWithId(id: Long): JiraTicket

    /**
     * Retrieves Jira tickets that match a query in a paginated set
     *
     * @param queries The queries containing the appropriate attributes, values, and filters
     * @return The response containing the Jira tickets that match the queries, and the pagination token
     */
    fun getTicketsWithQueries(queries: QueryListWrapper): AbstractResponse<JiraTicket>
}
