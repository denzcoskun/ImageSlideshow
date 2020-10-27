package com.denzcoskun.imageslider

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter
import com.denzcoskun.imageslider.constants.ActionTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.interfaces.TouchListener
import com.denzcoskun.imageslider.models.SlideModel
import java.util.*

/**
 * Created by Name name on 6/23/2020.
 * denzcoskun@hotmail.com
 * İstanbul
 */
class ImageSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
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
    private var titleBackground = 0
    private var textAlign = "LEFT"
    private var indicatorAlign = "CENTER"
    private var swipeTimer = Timer()

    private var itemChangeListener: ItemChangeListener? = null
    private var touchListener: TouchListener? = null

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.image_slider, this, true)
        viewPager = findViewById(R.id.view_pager)
        pagerDots = findViewById(R.id.pager_dots)

        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ImageSlider,
            defStyleAttr,
            defStyleAttr
        )

        cornerRadius = typedArray.getInt(R.styleable.ImageSlider_iss_corner_radius, 1)
        period = typedArray.getInt(R.styleable.ImageSlider_iss_period, 1000).toLong()
        delay = typedArray.getInt(R.styleable.ImageSlider_iss_delay, 1000).toLong()
        autoCycle = typedArray.getBoolean(R.styleable.ImageSlider_iss_auto_cycle, false)
        placeholder = typedArray.getResourceId(R.styleable.ImageSlider_iss_placeholder, R.drawable.loading)
        errorImage = typedArray.getResourceId(R.styleable.ImageSlider_iss_error_image, R.drawable.error)
        selectedDot = typedArray.getResourceId(R.styleable.ImageSlider_iss_selected_dot, R.drawable.default_selected_dot)
        unselectedDot = typedArray.getResourceId(R.styleable.ImageSlider_iss_unselected_dot, R.drawable.default_unselected_dot)
        titleBackground = typedArray.getResourceId(R.styleable.ImageSlider_iss_title_background, R.drawable.gradient)

        if (typedArray.getString(R.styleable.ImageSlider_iss_text_align) != null){
            textAlign = typedArray.getString(R.styleable.ImageSlider_iss_text_align)
        }

        if (typedArray.getString(R.styleable.ImageSlider_iss_indicator_align) != null){
            indicatorAlign = typedArray.getString(R.styleable.ImageSlider_iss_indicator_align)
        }

        if (touchListener != null){
            viewPager!!.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> touchListener!!.onTouched(ActionTypes.MOVE)
                    MotionEvent.ACTION_DOWN -> touchListener!!.onTouched(ActionTypes.DOWN)
                    MotionEvent.ACTION_UP -> touchListener!!.onTouched(ActionTypes.UP)
                }
                false
            }
        }

    }

    /**
     * Set image list to adapter.
     *
     * @param  imageList  the image list by user
     */
    fun setImageList(imageList: List<SlideModel>) {
        viewPagerAdapter = ViewPagerAdapter( context, imageList, cornerRadius, errorImage, placeholder, titleBackground, textAlign)
        viewPager!!.adapter = viewPagerAdapter
        imageCount = imageList.size
        if (imageList.isNotEmpty()){
            setupDots(imageList.size)
            if (autoCycle) {
                stopSliding()
                startSliding()
            }
        }
    }

    /**
     * Set image list to adapter.
     *
     * @param  imageList  the image list by user
     * @param  scaleType  scale type for all image
     */
    fun setImageList(imageList: List<SlideModel>, scaleType: ScaleTypes? = null) {
        viewPagerAdapter = ViewPagerAdapter( context, imageList, cornerRadius, errorImage, placeholder, titleBackground, scaleType, textAlign)
        viewPager!!.adapter = viewPagerAdapter
        imageCount = imageList.size
        if (imageList.isNotEmpty()){
            setupDots(imageList.size)
            if (autoCycle) {
                startSliding()
            }
        }
    }

    private fun setupDots(size: Int) {
        println(indicatorAlign)
        pagerDots!!.gravity = getGravityFromAlign(indicatorAlign)
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

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                for (dot in dots!!) {
                    dot!!.setImageDrawable(ContextCompat.getDrawable(context, unselectedDot))
                }
                dots!![position]!!.setImageDrawable(ContextCompat.getDrawable(context, selectedDot))
                if (itemChangeListener != null) itemChangeListener!!.onItemChanged(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    /**
     * Start image sliding.
     *
     * @param  changeablePeriod  optional period value
     */
    fun startSliding(changeablePeriod: Long = period) {
        stopSliding()
        scheduleTimer(changeablePeriod)
    }

    /**
     * Stop image sliding.
     *
     */
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

    /**
     * Set item click listener for listen to image click
     *
     * @param  itemClickListener  interface callback
     */
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        viewPagerAdapter?.setItemClickListener(itemClickListener)
    }

    /**
     * Set item change listener for listen to image click
     *
     * @param  itemChangeListener  interface callback
     */
    fun setItemChangeListener(itemChangeListener: ItemChangeListener) {
        this.itemChangeListener = itemChangeListener
    }

    /**
     * Set touch listener for listen to image touch
     *
     * @param  touchListener  interface callback
     */
    fun setTouchListener(touchListener: TouchListener) {
        this.touchListener = touchListener
        this.viewPagerAdapter!!.setTouchListener(touchListener)
    }

    /**
     * Get layout gravity value from indicatorAlign variable
     *
     * @param  indicatorAlign  indicator align by user
     */
    fun getGravityFromAlign(textAlign: String): Int {
        return when (textAlign) {
            "RIGHT" -> {
                Gravity.RIGHT
            }
            "LEFT" -> {
                Gravity.LEFT
            }
            else -> {
                Gravity.CENTER
            }
        }
    }

}



