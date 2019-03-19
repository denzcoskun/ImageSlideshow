package com.denzcoskun.imageslider

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter


class ImageSlider @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    private var viewPager: ViewPager? = null
    private var pagerDots: LinearLayout? = null


    private var dots: Array<ImageView?>? = null

    init{
        LayoutInflater.from(getContext()).inflate(R.layout.image_slider, this, true)
        viewPager = findViewById(R.id.view_pager)
        pagerDots = findViewById(R.id.pager_dots)
    }

    fun setImageList(imageList: List<String>){
        var viewPagerAdapter =  ViewPagerAdapter(context, imageList);
        viewPager!!.adapter = viewPagerAdapter
        setupDots(imageList.size)
    }

    fun setupDots(size: Int) {

        dots = arrayOfNulls(size)

        for (i in 0 until size) {
            dots!![i] = ImageView(context)
            dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_unselected))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            pagerDots!!.addView(dots!![i], params)
        }
        dots!![0]!!.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_selected))

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                for (dot in dots!!) {
                    dot!!.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_unselected))
                }
                dots!![position]!!.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_selected))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}