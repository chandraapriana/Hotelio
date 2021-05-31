package com.chandra.hotelio

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvHotel: RecyclerView
    private var list: ArrayList<Hotel> = arrayListOf()
    private lateinit var btnAbout:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvHotel = findViewById(R.id.rv_hotel)
        rvHotel.setHasFixedSize(true)
        list.addAll(HotelData.listData)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            print(e)
        }

        btnAbout = findViewById(R.id.btn_about)
        btnAbout.setOnClickListener {
            val intent = Intent(baseContext, About::class.java)
            startActivity(intent)
        }

        showHotel()
    }

    private fun showHotel() {
        rvHotel.layoutManager = LinearLayoutManager(this)
        val listHotelAdapter = ListHotelAdapter(this, list)
        rvHotel.adapter = listHotelAdapter

        listHotelAdapter.setOnItemClickCallback(object : ListHotelAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hotel) {

                val intent = Intent(baseContext, HotelDetail::class.java)
                intent.putExtra(HotelDetail.EXTRA_NAME, data.name)
                intent.putExtra(HotelDetail.EXTRA_CITY, data.city)
                intent.putExtra(HotelDetail.EXTRA_PRICE, data.price)
                intent.putExtra(HotelDetail.EXTRA_STAR, data.star)
                intent.putExtra(HotelDetail.EXTRA_IMAGE, data.image)
                intent.putExtra(HotelDetail.EXTRA_DESCRIPTION, data.description)
                intent.putExtra(HotelDetail.EXTRA_ADDRESS, data.address)
                intent.putExtra(HotelDetail.EXTRA_PHONENUMBER, data.phoneNumber)


                startActivity(intent)
            }

        })
    }
}