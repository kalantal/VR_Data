package com.stmk.sddatavr.search.jiradataset

import com.google.gson.Gson
import com.stmk.sddatavr.search.AbstractDao
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
                gson: Gson) : JiraDao, AbstractDao<JiraTicket>(searchClient, gson, index = "jiratickets", indexType = "Jira_Ticket_Type", recordClazz = JiraTicket::class.java)
