package com.atikshshah.fetch

import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface defining the API service endpoints and their respective HTTP methods.
 */
interface APIService {
    /**
     * GET request method to fetch data from the specified endpoint.
     * This method retrieves a list of DataModel objects.
     *
     * @return A Retrofit Call object containing a list of DataModel objects.
     */
    @GET("hiring.json")
    fun getData(): Call<List<DataModel>>
}
