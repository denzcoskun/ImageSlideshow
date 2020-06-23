package com.denzcoskun.imageslider.transformation

import android.graphics.*
import com.squareup.picasso.Transformation

/**
 * Created by Name name on 6/23/2020.
 * denzcoskun@hotmail.com
 * İstanbul
 */
class RoundedTransformation @JvmOverloads constructor(radius:Int, margin:Int, cornerType:CornerType = CornerType.ALL):
    Transformation {
    private var mRadius:Float = 0f
    private var mDiameter:Float = 0f
    private var mMargin:Float = 0f
    private var mCornerType:CornerType

    /**
     * Enum for corner types.
     */
    enum class CornerType {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
        OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT, DIAGONAL_FROM_TOP_RIGHT
    }
    init{
        mRadius = radius.toFloat()
        mDiameter = (radius * 2).toFloat()
        mMargin = margin.toFloat()
        mCornerType = cornerType
    }

    override fun transform(source:Bitmap): Bitmap {
        val width = source.getWidth()
        val height = source.getHeight()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.setAntiAlias(true)
        paint.setShader(BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP))
        drawRoundRect(canvas, paint, width.toFloat(), height.toFloat())
        source.recycle()
        return bitmap
    }
    private fun drawRoundRect(canvas:Canvas, paint:Paint, width:Float, height:Float) {
        val right = width - mMargin
        val bottom = height - mMargin
        when (mCornerType) {
            RoundedTransformation.CornerType.ALL -> canvas.drawRoundRect(RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint)
            RoundedTransformation.CornerType.TOP_LEFT -> drawTopLeftRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.TOP_RIGHT -> drawTopRightRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.BOTTOM_LEFT -> drawBottomLeftRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.BOTTOM_RIGHT -> drawBottomRightRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.TOP -> drawTopRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.BOTTOM -> drawBottomRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.LEFT -> drawLeftRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.RIGHT -> drawRightRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.OTHER_TOP_LEFT -> drawOtherTopLeftRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.OTHER_TOP_RIGHT -> drawOtherTopRightRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.OTHER_BOTTOM_LEFT -> drawOtherBottomLeftRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.OTHER_BOTTOM_RIGHT -> drawOtherBottomRightRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.DIAGONAL_FROM_TOP_LEFT -> drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom)
            RoundedTransformation.CornerType.DIAGONAL_FROM_TOP_RIGHT -> drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom)
            else -> canvas.drawRoundRect(RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint)
        }
    }
    private fun drawTopLeftRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, mMargin + mDiameter, mMargin + mDiameter),
            mRadius, mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin + mRadius, mMargin + mRadius, bottom), paint)
        canvas.drawRect(RectF(mMargin + mRadius, mMargin, right, bottom), paint)
    }
    private fun drawTopRightRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, mMargin + mDiameter), mRadius,
            mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom), paint)
        canvas.drawRect(RectF(right - mRadius, mMargin + mRadius, right, bottom), paint)
    }
    private fun drawBottomLeftRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, mMargin + mDiameter, bottom),
            mRadius, mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, mMargin + mDiameter, bottom - mRadius), paint)
        canvas.drawRect(RectF(mMargin + mRadius, mMargin, right, bottom), paint)
    }
    private fun drawBottomRightRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, bottom - mDiameter, right, bottom), mRadius,
            mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom), paint)
        canvas.drawRect(RectF(right - mRadius, mMargin, right, bottom - mRadius), paint)
    }
    private fun drawTopRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, right, mMargin + mDiameter), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin, mMargin + mRadius, right, bottom), paint)
    }
    private fun drawBottomRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin, mMargin, right, bottom - mRadius), paint)
    }
    private fun drawLeftRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, mMargin + mDiameter, bottom), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin + mRadius, mMargin, right, bottom), paint)
    }
    private fun drawRightRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom), paint)
    }
    private fun drawOtherTopLeftRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
            paint)
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom - mRadius), paint)
    }
    private fun drawOtherTopRightRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, mMargin + mDiameter, bottom), mRadius, mRadius,
            paint)
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin + mRadius, mMargin, right, bottom - mRadius), paint)
    }
    private fun drawOtherBottomLeftRoundRect(canvas:Canvas, paint:Paint, right:Float, bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, right, mMargin + mDiameter), mRadius, mRadius,
            paint)
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin, mMargin + mRadius, right - mRadius, bottom), paint)
    }
    private fun drawOtherBottomRightRoundRect(canvas:Canvas, paint:Paint, right:Float,
                                              bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, right, mMargin + mDiameter), mRadius, mRadius,
            paint)
        canvas.drawRoundRect(RectF(mMargin, mMargin, mMargin + mDiameter, bottom), mRadius, mRadius,
            paint)
        canvas.drawRect(RectF(mMargin + mRadius, mMargin + mRadius, right, bottom), paint)
    }
    private fun drawDiagonalFromTopLeftRoundRect(canvas:Canvas, paint:Paint, right:Float,
                                                 bottom:Float) {
        canvas.drawRoundRect(RectF(mMargin, mMargin, mMargin + mDiameter, mMargin + mDiameter),
            mRadius, mRadius, paint)
        canvas.drawRoundRect(RectF(right - mDiameter, bottom - mDiameter, right, bottom), mRadius,
            mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin + mRadius, right - mDiameter, bottom), paint)
        canvas.drawRect(RectF(mMargin + mDiameter, mMargin, right, bottom - mRadius), paint)
    }
    private fun drawDiagonalFromTopRightRoundRect(canvas:Canvas, paint:Paint, right:Float,
                                                  bottom:Float) {
        canvas.drawRoundRect(RectF(right - mDiameter, mMargin, right, mMargin + mDiameter), mRadius,
            mRadius, paint)
        canvas.drawRoundRect(RectF(mMargin, bottom - mDiameter, mMargin + mDiameter, bottom),
            mRadius, mRadius, paint)
        canvas.drawRect(RectF(mMargin, mMargin, right - mRadius, bottom - mRadius), paint)
        canvas.drawRect(RectF(mMargin + mRadius, mMargin + mRadius, right, bottom), paint)
    }
    override fun key():String {
        return ("RoundedTransformation(radius=" + mRadius + ", margin=" + mMargin + ", diameter="
                + mDiameter + ", cornerType=" + mCornerType.name + ")")
    }
}