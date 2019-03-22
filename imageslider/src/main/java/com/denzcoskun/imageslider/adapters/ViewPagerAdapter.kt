package com.denzcoskun.imageslider.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.denzcoskun.imageslider.R
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.squareup.picasso.Picasso


class ViewPagerAdapter(context: Context?, imageList: List<SlideModel>) : PagerAdapter() {

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

        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        if(imageList!![position].imageUrl == null){
            Picasso.get()
                .load(imageList!![position].imagePath!!) // Int
                .fit()
                .error(R.drawable.deneme)
                .into(imageView)
        }else{
            Picasso.get()
                .load(imageList!![position].imageUrl!!) // String
                .fit()
                .error(R.drawable.deneme)
                .into(imageView)
        }

        container.addView(itemView)

        imageView.setOnClickListener{itemClickListener?.onItemSelected(position)}

        return itemView
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}