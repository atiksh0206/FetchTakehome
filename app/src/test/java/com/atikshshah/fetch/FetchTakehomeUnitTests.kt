package com.atikshshah.fetch

import org.junit.Assert.assertEquals
import org.junit.Test

class DataAdapterTest {

    @Test
    fun getItemViewType_headerTypeReturned() {
        val dataList = listOf(
            DataModel(-1, 1, ""),
            DataModel(1, 1, "Child 1"),
            DataModel(2, 1, "Child 2")
        )
        val adapter = DataAdapter(dataList)

        assertEquals(adapter.getItemViewType(0), DataAdapter.VIEW_TYPE_HEADER)
    }

    @Test
    fun getItemViewType_itemTypeReturned() {
        val dataList = listOf(
            DataModel(-1, 1, ""),
            DataModel(1, 1, "Child 1"),
            DataModel(2, 1, "Child 2")
        )
        val adapter = DataAdapter(dataList)

        assertEquals(adapter.getItemViewType(1), DataAdapter.VIEW_TYPE_ITEM)
    }
}
