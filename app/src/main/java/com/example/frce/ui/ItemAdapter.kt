package com.example.frce.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frce.databinding.GroupHeaderBinding
import com.example.frce.databinding.SingleLineItemBinding
import com.example.frce.network.Item
import com.zhukic.sectionedrecyclerview.SectionedRecyclerAdapter

class ItemAdapter(
    private val dataset: List<Item>
) : SectionedRecyclerAdapter<ItemAdapter.GroupHeaderViewHolder, ItemAdapter.ItemViewHolder>() {

    class GroupHeaderViewHolder(private var binding: GroupHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindHeader(item: Item) {
            binding.tvGroupHeader.text = "List ID: " + item.listId.toString()
        }
    }

    class ItemViewHolder(private var binding: SingleLineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Item) {
            binding.apply {
                tvItemId.text = item.id.toString()
                tvItemName.text = item.name
            }
        }
    }

    override fun onPlaceSubheaderBetweenItems(itemPosition: Int, nextItemPosition: Int): Boolean {
        return dataset[itemPosition].listId != dataset[nextItemPosition].listId
    }

    override fun onCreateSubheaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupHeaderViewHolder {
        val view = GroupHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupHeaderViewHolder(view)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = SingleLineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindSubheaderViewHolder(holder: GroupHeaderViewHolder, position: Int) {
        val item = dataset[position]
        holder.bindHeader(item)
    }

    override fun onBindItemViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.bindItem(item)
    }

    override fun getCount(): Int = dataset.size
}