package com.stmk.sddatavr.search

/**
 * Created by Krishna Chaitanya Kandula on 10/5/17.
 */
interface Dao<T : AbstractElasticsearchRecord> {
    fun getAll(): List<T>

    fun getWithId(id: Long): T

    fun add(data: T): Boolean

    fun deleteWithId(id: Long): Boolean
}
