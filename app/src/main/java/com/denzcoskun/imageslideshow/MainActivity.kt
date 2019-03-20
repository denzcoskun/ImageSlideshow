package com.denzcoskun.imageslideshow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.UserClickCallbacks

class MainActivity : AppCompatActivity(), UserClickCallbacks {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagelist = ArrayList<String>()
        imagelist.add("https://images4.alphacoders.com/740/thumb-1920-740289.png")
        imagelist.add("https://i.pinimg.com/originals/fd/c9/98/fdc9984f77e922212d62e0cd330b10c6.png")
        imagelist.add("https://d37x086vserhlm.cloudfront.net/wp-content/uploads/2016/11/24001621/your-name.-film2-e1480003095479.jpeg")
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imagelist, this)
    }

    override fun onUserClick(position: Int) {
        println(position)
    }
}
