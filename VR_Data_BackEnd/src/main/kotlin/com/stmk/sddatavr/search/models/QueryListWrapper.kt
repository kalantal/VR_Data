package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName

/**
 * A wrapper class that contains a list of queries and a pagination token
 *
 * @see Query
 * @author Krishna Chaitanya Kandula
 * @since 12-07-2017
 */
data class QueryListWrapper(@SerializedName("queries") val queries: List<Query>,
                            @SerializedName("pagination_token") val paginationToken: Int)
