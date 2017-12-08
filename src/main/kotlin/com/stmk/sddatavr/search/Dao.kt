package com.stmk.sddatavr.search

import com.stmk.sddatavr.search.models.Query

/**
 * The interface for an Elasticsearch Dao. See AbstractDao for an implementation
 *
 * @see AbstractDao
 * @author Krishna C Kandula
 * @since 12-07-2017
 */
interface Dao<T : AbstractElasticsearchRecord> {
    fun getAll(paginationToken: Int): AbstractResponse<T>

    fun getWithId(id: Long): T

    fun add(data: T): Boolean

    fun deleteWithId(id: Long): Boolean

    fun getWithQuery(queries: List<Query>, paginationToken: Int): AbstractResponse<T>
}
