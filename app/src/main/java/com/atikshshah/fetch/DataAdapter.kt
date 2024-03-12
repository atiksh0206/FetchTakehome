package com.atikshshah.fetch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/**
 * Adapter class responsible for managing the data and creating views for each item in the RecyclerView.
 *
 */
class DataAdapter(private val dataList: List<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /**
     * Creates and initializes the ViewHolder and its View
     *
     * @param parent The parent view group.
     * @param viewType The view type of the new View.
     * @return A new RecyclerView.ViewHolder that holds the inflated view.
     * @throws IllegalArgumentException if an invalid view type is encountered.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            // Inflates the header view
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }
            // Inflates the item view
            VIEW_TYPE_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_data, parent, false)
                ItemViewHolder(view)
            }
            // Throws an IllegalArgumentExcetpion if it isnt either
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
    /**
     * Binds the data to the views in each RecyclerView item.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the data list.
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Gets the data for each item
        val dataItem = dataList[position]

        when (holder) {
            // Concatenates ListID and the actual listId
            is HeaderViewHolder -> {
                holder.headerTextView.text = "ListID: " + dataItem.listId.toString()
            }
            is ItemViewHolder -> {
                holder.nameTextView.text = dataItem.name
            }
        }
    }
    /**
     * Returns the total number of items in the data list.
     *
     * @return The total number of items in the data list.
     */
    override fun getItemCount(): Int {
        return dataList.size
    }
    /**
     * Determines the view type of the item at the specified position.
     *
     * @param position The position of the item in the data list.
     * @return The view type of the item.
     */
    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].id == -1) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }
    /**
     * ViewHolder class for regular item views.
     *
     * @param itemView The inflated item view.
     */
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }
    /**
     * ViewHolder class for header item views.
     *
     * @param itemView The inflated header view.
     */
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTextView: TextView = itemView.findViewById(R.id.headerTextView)
    }

    companion object {
        public const val VIEW_TYPE_HEADER = 0
        public const val VIEW_TYPE_ITEM = 1
    }
}
