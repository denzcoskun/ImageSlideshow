package com.denzcoskun.imageslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer

/**
 * Created by denzcoskun on 03,April,2023.
 * Email: denzcoskun@hotmail.com
 * Istanbul, TURKEY.
 */
class CubeOut: PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.pivotX = if (position < 0f) view.width.toFloat() else 0f
        view.pivotY = view.height * 0.5f
        view.rotationY = 90f * position
    }
}