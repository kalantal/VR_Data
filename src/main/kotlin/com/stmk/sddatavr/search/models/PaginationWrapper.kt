package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Mandy Cho :) on 11/29/17.
 */
data class PaginationWrapper(@SerializedName("pagination_token") val paginationToken: Int)