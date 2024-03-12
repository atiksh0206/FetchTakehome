package com.atikshshah.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * MainActivity class responsible for the main functionality of the application.
 * It retrieves data from an API, sorts, filters, and groups it, and displays it in a RecyclerView.
 */
class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView and its layout manager
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Retrofit for API communication
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val call = service.getData()

        // Fetch data from the API
        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                if (response.isSuccessful) {
                    val dataList = response.body() ?: emptyList()
                    // Sorts and filters the JSON data
                    val modDataList = sortFilterAndGroup(dataList)
                    // Sets the data up to be displayed
                    adapter = DataAdapter(modDataList)
                    recyclerView.adapter = adapter
                } else {
                    println("Error")
                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                print("failed")
            }
        })


    }

    /**
     * Sorts, filters, and groups the inputted jsonData for display in RecyclerView.
     *
     * @param jsonData Inputted jsonData in List<DataModel> format.
     * @return A sorted, filtered, and grouped List<DataModel> for RecyclerView.
     */
    private fun sortFilterAndGroup(jsonData: List<DataModel>): List<DataModel> {
        // Filter out all the null and blank names
        val filteredDataList = jsonData.filter { !it.name.isNullOrBlank() }

        // Group by 'listId' property
        val groupedDataMap = filteredDataList.groupBy { it.listId }
        // Sort groups by keys (listId)
        val sortedGroupedDataMap = groupedDataMap.toSortedMap()

        // Flatten the grouped data for recyclerview adapter
        val simplifiedDataList = mutableListOf<DataModel>()
        sortedGroupedDataMap.values.forEach { groupList ->
            // Sort items within each group by name
            val sortedGroup = groupList.sortedWith(compareBy { extractNumber(it.name) })
            // Add group header
            simplifiedDataList.add(DataModel(-1, groupList.first().listId, ""))
            // Add sorted group items to flattened list
            simplifiedDataList.addAll(sortedGroup)
        }
        return simplifiedDataList
    }
    /**
     * Extracts a number from a string.
     * Used for sorting purposes.
     *
     * @param str The input string.
     * @return The extracted number as an integer.
     */
    private fun extractNumber(str: String?): Int {
        // Split the string by whitespace and take the last part
        val numberPart = str?.split(" ")?.last()

        // Convert the last part to an integer
        return numberPart?.toIntOrNull() ?: 0
    }

}