<img src="https://user-images.githubusercontent.com/15522554/54867096-7169d700-4d8d-11e9-8f9a-bee162a01427.png">
<a href="https://github.com/Ramotion/cardslider-android">
<img align="left" src="https://user-images.githubusercontent.com/15522554/85434507-d3b41780-b58e-11ea-9386-42741f0921ae.gif"/></a>
<p><h1 align="left">Android Image Slide</h1></p>
<p>This is an android image slider library. Just add the images you want to view. </p>
<p>You can use automatic scrolling for the time you set.  </p>
<p>You can also add the title you want to the images.</p>
<p>You can set corner radius images.</p>
<p>You can use with Java or Kotlin.</p>
</br>

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ImageSlideshow-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7587)
[![license](https://img.shields.io/github/license/denzcoskun/imageslideshow.svg?style=popout)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/denzcoskun/ImageSlideshow.svg)](https://jitpack.io/#denzcoskun/ImageSlideshow)

## Usage

-   Add ImageSlider to your **layout**

```xml
<com.denzcoskun.imageslider.ImageSlider
        android:id="@+id/image_slider"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        app:iss_auto_cycle="true"
        app:iss_period="1000"
        app:iss_delay="0"/>
```

-   You can change placeholder image.

```xml
 app:iss_placeholder="@drawable/placeholder"
```

-   You can change error image.


```xml
 app:iss_error_image="@drawable/error"
```

-   You can change indicators.

```xml
app:iss_selected_dot="@drawable/default_selected_dot"
app:iss_unselected_dot="@drawable/default_unselected_dot"
```

-   Add ImageSlider to your **Activity**

```kt
val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

imageList.add(SlideModel("https://bit.ly/2YoJ77H", "The animal population decreased by 58 percent in 42 years."))
imageList.add(SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct."))
imageList.add(SlideModel("https://bit.ly/3fLJf72", "And people do that."))

val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
imageSlider.setImageList(imageList)
```

-   You can change scaleType for all images or one image.

<img src="https://user-images.githubusercontent.com/15522554/85434498-d0209080-b58e-11ea-83cc-ce79076391c1.jpg">

```kt
import com.denzcoskun.imageslider.constants.ScaleTypes // important

// FIT, CENTER_CROP or CENTER_INSIDE

imageList.add(SlideModel("String Url" or R.drawable, ScaleTypes.FIT) // for one image
imageList.add(SlideModel("String Url" or R.drawable, "Title", ScaleTypes.FIT) // you can with title

imageSlider.setImageList(imageList, ScaleTypes.FIT) // for all images
```

-   You can change title background on xml.

<img src="https://user-images.githubusercontent.com/15522554/85441920-4b3a7480-b598-11ea-9154-338a8577552b.jpg">

```xml
 app:iss_title_background="@drawable/gradient"
 //or
 app:iss_title_background="@android:color/holo_red_light"
```

-   You can use click listener.


```kt
imageSlider.setItemClickListener(object : ItemClickListener {
    override fun onItemSelected(position: Int) {
	// You can listen here
    }
})
```

-   You can add stop and start auto sliding again.


```kt
imageSlider.startSliding(3000) // with new period
imageSlider.startSliding()
imageSlider.stopSliding()
```

## Setup


```xml
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
	implementation 'com.github.denzcoskun:ImageSlideshow:0.0.6'
}
```

## 📄 License

Copyright 2019 Deniz Coşkun

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

<a href="http://www.apache.org/licenses/LICENSE-2.0"><b>License</b></a>

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.
