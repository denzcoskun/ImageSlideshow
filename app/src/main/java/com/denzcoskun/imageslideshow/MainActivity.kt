package com.denzcoskun.imageslideshow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagelist = ArrayList<SlideModel>()
        imagelist.add(SlideModel(R.drawable.deneme, "Deneme"))
        imagelist.add(SlideModel("https://i.pinimg.com/originals/fd/c9/98/fdc9984f77e922212d62e0cd330b10c6.png"))
        imagelist.add(SlideModel("https://i.pinimg.com/originals/fd/c9/98/fdc9984f77e922212d62e0cd330b10c6.png"))
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imagelist)

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {

            }
        })
    }

}
