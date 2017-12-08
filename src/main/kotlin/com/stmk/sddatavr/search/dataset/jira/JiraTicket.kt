package com.stmk.sddatavr.search.dataset.jira

import com.google.gson.annotations.SerializedName
import com.stmk.sddatavr.search.AbstractElasticsearchRecord

/**
 * @author Krishna Chaitanya Kandula
 */
data class JiraTicket(@SerializedName("jira_id") val jiraId: Long,
                      @SerializedName("summary") val summary: String,
                      @SerializedName("issuetype_id") val issueTypeId: Long,
                      @SerializedName("issuetype_description") val issueTypeDescription: String,
                      @SerializedName("issuetype_name") val issueTypeName: String,
                      @SerializedName("issuetype_subtask") val issueTypeSubtask: Long,
                      @SerializedName("assignee_name") val assigneeName: String) : AbstractElasticsearchRecord(jiraId)
