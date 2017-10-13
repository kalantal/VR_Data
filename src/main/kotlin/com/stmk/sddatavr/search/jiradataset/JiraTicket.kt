package com.stmk.sddatavr.search.jiradataset

import com.google.gson.annotations.SerializedName
import com.stmk.sddatavr.search.AbstractElasticsearchRecord

/**
 * Created by Krishna Chaitanya Kandula on 10/12/17.
 */
data class JiraTicket(val jiraId: Long,
                      val summary: String,
                      @SerializedName("issuetype_id") val issueTypeId: Long,
                      @SerializedName("issuetype_description") val issueTypeDescription: String,
                      @SerializedName("issuetype_name") val issueTypeName: String,
                      @SerializedName("issuetype_subtask") val issueTypeSubtask: Boolean,
                      @SerializedName("assignee_name") val assigneeName: String) : AbstractElasticsearchRecord(jiraId)
