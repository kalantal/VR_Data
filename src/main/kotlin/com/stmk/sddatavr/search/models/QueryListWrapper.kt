package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName
import javafx.scene.control.Pagination

/**
 * Created by Krishna Chaitanya Kandula on 10/23/17.
 */
data class QueryListWrapper(@SerializedName("queries") val queries: List<Query>,
                            @SerializedName("pagination_token") val paginationToken: Int)
