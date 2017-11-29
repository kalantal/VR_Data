package com.stmk.sddatavr.search

import com.stmk.sddatavr.search.models.Query

/**
 * Created by Krishna Chaitanya Kandula on 10/5/17.
 */
interface Dao<T : AbstractElasticsearchRecord> {
    fun getAll(pagToken: Int): List<T>

    fun getWithId(id: Long): T

    fun add(data: T): Boolean

    fun deleteWithId(id: Long): Boolean

    fun getWithQuery(queries: List<Query>, pagToken: Int): List<T>
}
