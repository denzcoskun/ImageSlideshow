package com.denzcoskun.imageslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer

/**
 * Created by denzcoskun on 03,April,2023.
 * Email: denzcoskun@hotmail.com
 * Istanbul, TURKEY.
 */
class RotateDown: PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val width = view.width
        val rotation = -15f * position

        view.pivotX = width * 0.5f
        view.pivotY = 0f
        view.translationX = 0f
        view.rotation = rotation
    }

}