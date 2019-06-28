package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName

/**
 * Determines the conditions of when an attribute or query should occur. See the Elasticsearch documentation for more info.
 *
 * @author Krishna Chaitanya Kandula
 */
enum class Occur(val occurance: String) {
    @SerializedName("must") MUST("must"),
    @SerializedName("must_not") MUST_NOT("must_not"),
    @SerializedName("should") SHOULD("should")
}
