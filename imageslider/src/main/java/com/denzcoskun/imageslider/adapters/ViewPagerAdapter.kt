package com.denzcoskun.imageslider.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.denzcoskun.imageslider.R
import com.denzcoskun.imageslider.interfaces.UserClickCallbacks
import com.squareup.picasso.Picasso


class ViewPagerAdapter(context: Context?, imageList: List<String>, private val userClickCallbacks: UserClickCallbacks?) : PagerAdapter() {

    private var imageList: List<String>? = imageList
    private var layoutInflater: LayoutInflater? = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return imageList!!.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): View{

        val itemView = layoutInflater!!.inflate(R.layout.pager_row, container, false)

        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        Picasso.get()
            .load(imageList!![position])
            .fit()
            .error(R.drawable.deneme)
            .into(imageView)

        container.addView(itemView)


        imageView.setOnClickListener{userClickCallbacks?.onUserClick(position)}

        return itemView
    }



    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}