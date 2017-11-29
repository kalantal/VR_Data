package com.stmk.sddatavr.search.dataset.jira

import com.google.gson.Gson
import com.stmk.sddatavr.search.AbstractDao
import com.stmk.sddatavr.search.AbstractResponse
import com.stmk.sddatavr.search.models.QueryListWrapper
import org.elasticsearch.client.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Krishna Chaitanya Kandula on 10/13/17.
 */
@Component
class JiraDaoImpl
    @Autowired
    constructor(searchClient: Client,
                gson: Gson) : JiraDao, AbstractDao<JiraTicket>(searchClient, gson, index = "jiratickets", indexType = "Jira_Ticket_Type", recordClazz = JiraTicket::class.java) {
    
    override fun getTicketWithId(id: Long): JiraTicket = getWithId(id)

    override fun getTicketsWithQueries(queries: QueryListWrapper, paginationToken: Int): AbstractResponse<JiraTicket> = getWithQuery(queries.queries, paginationToken)
}
