package com.stmk.sddatavr.search.sampledataset

import com.stmk.sddatavr.search.AbstractElasticsearchRecord
import java.util.*

/**
 * Created by Krishna Chaitanya Kandula on 10/4/17.
 */
data class Person(val personId: Long,
                  val name: String,
                  val age: Int,
                  val dateOfBirth: Date?) : AbstractElasticsearchRecord(personId)
