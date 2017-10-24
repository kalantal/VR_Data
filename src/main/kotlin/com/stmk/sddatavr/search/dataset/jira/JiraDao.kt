package com.stmk.sddatavr.search.dataset.jira

import com.stmk.sddatavr.search.models.QueryListWrapper

/**
 * Created by Krishna Chaitanya Kandula on 10/12/17.
 */

interface JiraDao {

    fun getAll(): List<JiraTicket>

    fun getTicketWithId(id: Long): JiraTicket

    fun getTicketsWithQueries(queries: QueryListWrapper): List<JiraTicket>
}
