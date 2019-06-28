package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName

/**
 * The query class that the frontend will create
 *
 * @param field the name of the attribute
 * @param value the value of the attribute to build the condition off of
 * @param occurance the ocurrence relationship between this query and others
 * @param nestedQueries any nested queries
 *
 * @see Occur
 *
 * @author Krishna Chaitanya Kandula
 */
data class Query(@SerializedName("field") val field: String?,
                 @SerializedName("value") val value: Any?,
                 @SerializedName("occurance") val occurance: Occur,
                 @SerializedName("nested_query") val nestedQueries: List<Query>?)
