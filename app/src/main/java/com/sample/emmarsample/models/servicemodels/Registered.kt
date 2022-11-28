package com.sample.emmarsample.models.servicemodels

import com.google.gson.annotations.SerializedName


data class Registered(

    @SerializedName("date") var date: String? = null,
    @SerializedName("age") var age: Int? = null

) {
    override fun toString(): String {
        return "$age days ago"
    }
}