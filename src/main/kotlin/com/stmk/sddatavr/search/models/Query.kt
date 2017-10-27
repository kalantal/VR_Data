package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Krishna Chaitanya Kandula on 10/23/17.
 */
data class Query(@SerializedName("field") val field: String?,
                 @SerializedName("value") val value: Any?,
                 @SerializedName("occurance") val occurance: Occur,
                 @SerializedName("nested_query") val nestedQueries: List<Query>?)
