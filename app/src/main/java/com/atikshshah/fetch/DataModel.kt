package com.atikshshah.fetch

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a model for the data retrieved from the API.
 * It encapsulates the properties of the data model.
 *
 */
data class DataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("listId") val listId: Int,
    @SerializedName("name") val name: String?,
)
