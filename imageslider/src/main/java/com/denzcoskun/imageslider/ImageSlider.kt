package com.denzcoskun.imageslider

import android.content.Context
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import java.util.*


class ImageSlider @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    private var viewPager: ViewPager? = null
    private var pagerDots: LinearLayout? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

    private var dots: Array<ImageView?>? = null

    private var currentPage = 0
    private var imageCount = 0

    private var cornerRadius: Int = 0
    private var period: Long = 0
    private var delay: Long = 0
    private var autoCycle = false

    private var selectedDot = 0
    private var unselectedDot = 0
    private var errorImage = 0
    private var placeholder = 0
    private var swipeTimer = Timer()

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.image_slider, this, true)
        viewPager = findViewById(R.id.view_pager)
        pagerDots = findViewById(R.id.pager_dots)

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ImageSlider, defStyleAttr, defStyleAttr)

        cornerRadius = typedArray.getInt(R.styleable.ImageSlider_corner_radius, 0)
        period = typedArray.getInt(R.styleable.ImageSlider_period, 1000).toLong()
        delay = typedArray.getInt(R.styleable.ImageSlider_delay, 1000).toLong()
        autoCycle = typedArray.getBoolean(R.styleable.ImageSlider_auto_cycle, false)
        placeholder = typedArray.getResourceId(R.styleable.ImageSlider_placeholder, R.drawable.placeholder)
        errorImage = typedArray.getResourceId(R.styleable.ImageSlider_error_image, R.drawable.error)
        selectedDot = typedArray.getResourceId(R.styleable.ImageSlider_selected_dot, R.drawable.default_selected_dot)
        unselectedDot = typedArray.getResourceId(R.styleable.ImageSlider_unselected_dot, R.drawable.default_unselected_dot)

    }

    fun setImageList(imageList: List<SlideModel>, centerCrop: Boolean = false) {
        viewPagerAdapter = ViewPagerAdapter(context, imageList, cornerRadius, errorImage, placeholder, centerCrop)
        viewPager!!.adapter = viewPagerAdapter
        imageCount = imageList.size
        if (imageList.size > 1){
            setupDots(imageList.size)
            if (autoCycle) {
                startSliding()
            }
        }
    }

    private fun setupDots(size: Int) {
        pagerDots!!.removeAllViews()
        dots = arrayOfNulls(size)

        for (i in 0 until size) {
            dots!![i] = ImageView(context)
            dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(context, unselectedDot))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            pagerDots!!.addView(dots!![i], params)
        }
        dots!![0]!!.setImageDrawable(ContextCompat.getDrawable(context, selectedDot))

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentPage = position
                for (dot in dots!!) {
                    dot!!.setImageDrawable(ContextCompat.getDrawable(context, unselectedDot))
                }
                dots!![position]!!.setImageDrawable(ContextCompat.getDrawable(context, selectedDot))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun startSliding(changeablePeriod: Long = period) {
        scheduleTimer(changeablePeriod)
    }

    fun stopSliding(){
        swipeTimer.cancel()
        swipeTimer.purge()
    }

    private fun scheduleTimer(period: Long) {
        val handler = Handler()
        val update = Runnable {
            if (currentPage == imageCount) {
                currentPage = 0
            }
            viewPager!!.setCurrentItem(currentPage++, true)
        }
        swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, delay, period)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        viewPagerAdapter?.setItemClickListener(itemClickListener)
    }

}



