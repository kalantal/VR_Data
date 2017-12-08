package com.stmk.sddatavr.search.dataset.jira

import com.google.gson.Gson
import com.stmk.sddatavr.search.AbstractDao
import com.stmk.sddatavr.search.AbstractResponse
import com.stmk.sddatavr.search.models.QueryListWrapper
import org.elasticsearch.client.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Implementation for JiraDao using Elasticsearch and AbstractDao.
 * Creates a Spring bean to expose the service to the Spring dependency graph.
 * Uses JiraTicket as the record type. The parameters for the constructor are fulfilled using Spring dependency injection
 *
 * @param searchClient the client that connects to
 * @param gson the Json -> Kotlin conversion client
 * @constructor Creates and exposes the default JiraDao implementation
 * @see com.stmk.sddatavr.search.Config
 * @see com.stmk.sddatavr.search.dataset.jira.JiraDao
 * @see com.stmk.sddatavr.search.AbstractDao
 * @see com.stmk.sddatavr.search.dataset.jira.JiraTicket
 * @author Krishna Chaitanya Kandula
 * @since 12-07-2017
 */
@Component
class JiraDaoImpl
@Autowired
constructor(searchClient: Client,
            gson: Gson) : JiraDao, AbstractDao<JiraTicket>(searchClient, gson, index = "jiratickets", indexType = "Jira_Ticket_Type", recordClazz = JiraTicket::class.java) {

    override fun getAll(paginationToken: Int): AbstractResponse<JiraTicket> = getAll(paginationToken)

    override fun getTicketWithId(id: Long): JiraTicket = getWithId(id)

    override fun getTicketsWithQueries(queries: QueryListWrapper): AbstractResponse<JiraTicket> = getWithQuery(queries.queries, queries.paginationToken)
}
