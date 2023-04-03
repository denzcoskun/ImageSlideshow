package com.denzcoskun.imageslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer

/**
 * Created by denzcoskun on 03,April,2023.
 * Email: denzcoskun@hotmail.com
 * Istanbul, TURKEY.
 */
class CubeIn: PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.pivotX = if (position > 0) 0f else view.width.toFloat()
        view.pivotY = 0f
        view.rotationY = -90f * position
    }

}