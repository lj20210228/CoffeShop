package com.example.coffeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeshop.Helper.ChangeNumberItemsListener
import com.example.coffeshop.Model.ItemsModel
import com.example.coffeshop.databinding.ActivityCartBinding
import com.example.coffeshop.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ManagmentCart

class CartAdapter(private val listItemSelected:ArrayList<ItemsModel>
,context:Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null):RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(val binding: ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)
    private val managmentCart=ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=listItemSelected[position]
        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachItem.text="$${item.price}"
        holder.binding.totaleachItem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text=item.numberInCart.toString()
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply ( RequestOptions().transform(CenterCrop()) )
            .into(holder.binding.picCart)
        holder.binding.plusCartBtn.setOnClickListener{
            managmentCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }
        holder.binding.minusCartBtn.setOnClickListener{
            managmentCart.minusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int =listItemSelected.size




}