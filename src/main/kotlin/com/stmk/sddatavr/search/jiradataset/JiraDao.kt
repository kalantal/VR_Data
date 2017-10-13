package com.stmk.sddatavr.search.jiradataset

import com.stmk.sddatavr.search.AbstractElasticsearchRecord

/**
 * Created by Krishna Chaitanya Kandula on 10/12/17.
 */

interface JiraDao {

    fun getAll(): List<JiraTicket>
}
