package com.example.coffeshop.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeshop.Model.CategoryModel
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private lateinit var context: Context
    private var selectedPostion=-1
    private var lastSelectedPosition=-1

    inner class Viewholder(val binding:ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item=items[position]
        holder.binding.titleCat.text=item.title
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPostion
            selectedPostion=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPostion)
        }
        if(selectedPostion==position)
        {
            holder.binding.titleCat.setBackgroundResource(R.drawable.orange_bg)

        }
        else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.edittext_bg)

        }

    }

    override fun getItemCount(): Int=items
        .size
}