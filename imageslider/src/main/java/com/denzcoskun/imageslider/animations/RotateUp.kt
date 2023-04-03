package com.denzcoskun.imageslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer

/**
 * Created by denzcoskun on 03,April,2023.
 * Email: denzcoskun@hotmail.com
 * Istanbul, TURKEY.
 */
class RotateUp: PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val width = view.width
        val height = view.height
        val rotation = -15f * position * -1.25f

        view.pivotX = width * 0.5f
        view.pivotY = height.toFloat()
        view.rotation = rotation
    }

}