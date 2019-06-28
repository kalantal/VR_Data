package com.stmk.sddatavr.search

/**
 *
 * @author Mandy Cho
 * @since 11-29-2017
 */
data class AbstractResponse<out T : AbstractElasticsearchRecord>(val records: List<T>,
                                                                 val paginationToken: Int)