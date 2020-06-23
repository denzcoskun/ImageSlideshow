package com.denzcoskun.imageslider.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.denzcoskun.imageslider.R
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.transformation.RoundedTransformation
import com.squareup.picasso.Picasso

/**
 * Created by Deniz Coşkun on 6/23/2020.
 * denzcoskun@hotmail.com
 * İstanbul
 */
class ViewPagerAdapter(context: Context?,
                       imageList: List<SlideModel>,
                       private var radius: Int,
                       private var errorImage: Int,
                       private var placeholder: Int,
                       private var titleBackground: Int,
                       private var scaleType: ScaleTypes?,
                       private var textAlign: String) : PagerAdapter() {

    private var imageList: List<SlideModel>? = imageList
    private var layoutInflater: LayoutInflater? = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    private var itemClickListener: ItemClickListener? = null

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return imageList!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View{
        val itemView = layoutInflater!!.inflate(R.layout.pager_row, container, false)

        val imageView = itemView.findViewById<ImageView>(R.id.image_view)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.linear_layout)
        val textView = itemView.findViewById<TextView>(R.id.text_view)

        if (imageList!![position].title != null){
            textView.text = imageList!![position].title
            linearLayout.setBackgroundResource(titleBackground)
            textView.gravity = getGravityFromAlign(textAlign)
            linearLayout.gravity = getGravityFromAlign(textAlign)
        }else{
            linearLayout.visibility = View.INVISIBLE
        }

        // Image from url or local path check.
        val loader = if (imageList!![position].imageUrl == null){
            Picasso.get().load(imageList!![position].imagePath!!)
        }else{
            Picasso.get().load(imageList!![position].imageUrl!!)
        }

        // set Picasso options.
        if ((scaleType != null && scaleType == ScaleTypes.CENTER_CROP) || imageList!![position].scaleType == ScaleTypes.CENTER_CROP){
            loader.fit().centerCrop()
        } else if((scaleType != null && scaleType == ScaleTypes.CENTER_INSIDE) || imageList!![position].scaleType == ScaleTypes.CENTER_INSIDE){
            loader.fit().centerInside()
        }else if((scaleType != null && scaleType == ScaleTypes.FIT) || imageList!![position].scaleType == ScaleTypes.FIT){
            loader.fit()
        }

        loader.transform(RoundedTransformation(radius, 0))
            .placeholder(placeholder)
            .error(errorImage)
            .into(imageView)

        container.addView(itemView)

        imageView.setOnClickListener{itemClickListener?.onItemSelected(position)}

        return itemView
    }

    /**
     * Get layout gravity value from textAlign variable
     *
     * @param  textAlign  text align by user
     */
    fun getGravityFromAlign(textAlign: String): Int {
        return when (textAlign) {
            "RIGHT" -> {
                Gravity.RIGHT
            }
            "CENTER" -> {
                Gravity.CENTER
            }
            else -> {
                Gravity.LEFT
            }
        }
    }

    /**
     * Set item click listener
     *
     * @param  itemClickListener  callback by user
     */
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}