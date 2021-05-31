package com.chandra.hotelio

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class HotelDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CITY = "extra_city"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_STAR = "extra_star"
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_PHONENUMBER = "extra_phoneNumber"

    }

    lateinit var tvName: TextView
    lateinit var tvAddress: TextView
    lateinit var tvStar: TextView
    lateinit var tvPhone: TextView
    lateinit var tvPrice: TextView
    lateinit var tvDescription: TextView
    lateinit var imgHotel: ImageView
    lateinit var btnBook: Button
    lateinit var btnBack: ImageButton
    lateinit var btnShare: ImageButton
    lateinit var btnFav: ImageButton
    var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
            print(e)
        }

        btnBack = findViewById(R.id.btn_back_detail)
        tvName = findViewById(R.id.name_hotel_detail)
        tvAddress = findViewById(R.id.address_hotel_detail)
        tvStar = findViewById(R.id.star_hotel_detail)
        tvPhone = findViewById(R.id.phone_hotel_detail)
        tvPrice = findViewById(R.id.btn_book)
        tvDescription = findViewById(R.id.description_hotel_detail)
        imgHotel = findViewById(R.id.image_hotel_detail)
        btnBook = findViewById(R.id.btn_book)
        btnShare = findViewById(R.id.btn_share)
        btnFav = findViewById(R.id.btn_fav)


        tvName.text = intent.getStringExtra(EXTRA_NAME)
        tvAddress.text = intent.getStringExtra(EXTRA_ADDRESS)
        tvPhone.text = intent.getStringExtra(EXTRA_PHONENUMBER)
        tvDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
        tvStar.text = intent.getDoubleExtra(EXTRA_STAR, 0.0).toString()
        btnBook.text = "$ ${intent.getIntExtra(EXTRA_PRICE, 0)} per night"
        Glide.with(baseContext).load(intent.getIntExtra(EXTRA_IMAGE, 0)).into(imgHotel)

        btnShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = "Get the best hotels for only \$${intent.getIntExtra(
                EXTRA_PRICE,
                0
            )} in ${intent.getStringExtra(EXTRA_NAME)}"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "The best hotel with cheap price")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

        btnFav.setOnClickListener {
            when (isFav) {
                false -> {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_fill)
                    Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
                    isFav = true

                }
                else -> {

                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    isFav = false
                }
            }
        }

        btnBook.setOnClickListener {
            Toast.makeText(this, "Booked", Toast.LENGTH_SHORT).show()
        }
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}