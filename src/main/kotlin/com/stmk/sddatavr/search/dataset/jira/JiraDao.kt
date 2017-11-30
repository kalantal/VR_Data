package com.stmk.sddatavr.search.dataset.jira

import com.stmk.sddatavr.search.AbstractResponse
import com.stmk.sddatavr.search.models.PaginationWrapper
import com.stmk.sddatavr.search.models.QueryListWrapper

/**
 * Created by Krishna Chaitanya Kandula on 10/12/17.
 */

interface JiraDao {

    fun getAll(query: PaginationWrapper): AbstractResponse<JiraTicket>

    fun getTicketWithId(id: Long): JiraTicket

    fun getTicketsWithQueries(queries: QueryListWrapper): AbstractResponse<JiraTicket>
}
