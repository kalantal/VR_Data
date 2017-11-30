package com.stmk.sddatavr.search

/**
 * Created by Mandy Cho :) on 11/29/17.
 */
data class AbstractResponse<out T : AbstractElasticsearchRecord>(val records: List<T>,
                                                                 val paginationToken: Int)