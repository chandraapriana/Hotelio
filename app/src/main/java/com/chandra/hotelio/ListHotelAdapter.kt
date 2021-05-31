package com.chandra.hotelio

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ListHotelAdapter(private val context: Context, private val listHotel: ArrayList<Hotel>) :
    RecyclerView.Adapter<ListHotelAdapter.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.title_hotel)
        var tvCity: TextView = itemView.findViewById(R.id.city_hotel)
        var tvPrice: TextView = itemView.findViewById(R.id.price_hotel)
        var tvStar: TextView = itemView.findViewById(R.id.star_hotel)
        var imgPhoto: ImageView = itemView.findViewById(R.id.image_hotel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHotelAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)

        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHotel.size
    }

    override fun onBindViewHolder(holder: ListHotelAdapter.ListViewHolder, position: Int) {
        val hotel = listHotel[position]
        holder.tvName.text = hotel.name
        holder.tvCity.text = hotel.city
        holder.tvStar.text = hotel.star.toString()
        holder.tvPrice.text = "$" + hotel.price.toString()
        Glide.with(holder.itemView.context).load(hotel.image).into(holder.imgPhoto)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listHotel[holder.adapterPosition])

        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hotel)
    }


}