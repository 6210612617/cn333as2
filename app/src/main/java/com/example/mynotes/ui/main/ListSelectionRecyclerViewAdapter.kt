package com.example.mynotes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ListSelectionViewHolderBinding
import com.example.mynotes.models.TaskList

class ListSelectionRecyclerViewAdapter(private val lists: MutableList<TaskList>,
    val clickListener: ListSelectionRecyclerViewClickListener) : RecyclerView.Adapter<ListSelectionViewHolder>() {

    interface ListSelectionRecyclerViewClickListener{
        fun ListItemClicked(list: TaskList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position +1).toString()
        holder.binding.itemName.text = lists[position].name
        holder.itemView.setOnClickListener{
            clickListener.ListItemClicked(lists[position])
        }
    }


    override fun getItemCount() = lists.size
    fun listsUpdated(){
        notifyItemInserted(lists.size -1)
    }

}
