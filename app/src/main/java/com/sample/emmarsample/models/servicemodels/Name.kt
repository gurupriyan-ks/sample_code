package com.sample.emmarsample.models.servicemodels

import com.google.gson.annotations.SerializedName


data class Name (
  @SerializedName("title" ) var title : String? = null,
  @SerializedName("first" ) var first : String? = null,
  @SerializedName("last"  ) var last  : String? = null
){
  override fun toString(): String {
    return "$first $last"
  }
}