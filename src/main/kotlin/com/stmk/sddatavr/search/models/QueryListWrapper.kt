package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Krishna Chaitanya Kandula on 10/23/17.
 */
data class QueryListWrapper(@SerializedName("queries") val queries: List<Query>)
