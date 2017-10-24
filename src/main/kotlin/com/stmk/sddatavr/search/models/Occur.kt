package com.stmk.sddatavr.search.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Krishna Chaitanya Kandula on 10/23/17.
 */
enum class Occur(val occurance: String) {
    @SerializedName("must") MUST("must"),
    @SerializedName("must_not") MUST_NOT("must_not"),
    @SerializedName("should") SHOULD("should")
}
