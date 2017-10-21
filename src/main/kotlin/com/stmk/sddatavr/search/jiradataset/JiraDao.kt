package com.stmk.sddatavr.search.jiradataset

/**
 * Created by Krishna Chaitanya Kandula on 10/12/17.
 */

interface JiraDao {

    fun getAll(): List<JiraTicket>

    fun getTicketWithId(id: Long): JiraTicket

    
}
