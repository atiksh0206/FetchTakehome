package com.atikshshah.fetch

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("listId") val listId: Int,
    @SerializedName("name") val name: String,
    // Add other properties as needed
)
