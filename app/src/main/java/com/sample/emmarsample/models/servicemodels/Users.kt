package com.sample.emmarsample.models.servicemodels

import com.google.gson.annotations.SerializedName
import com.sample.emmarsample.models.servicemodels.Info
import com.sample.emmarsample.models.servicemodels.Results


data class Users (

  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
  @SerializedName("info"    ) var info    : Info?              = Info()

)