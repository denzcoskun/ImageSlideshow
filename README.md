<img src="https://user-images.githubusercontent.com/15522554/54867096-7169d700-4d8d-11e9-8f9a-bee162a01427.png">
<a href="https://github.com/Ramotion/cardslider-android">
<img align="left" src="https://user-images.githubusercontent.com/15522554/54868874-58b7ec00-4da2-11e9-819f-b4b9d3ba227b.gif"/></a>
<p><h1 align="left">Android Image Slider</h1></p>
<p>This is an android image slider library. Just add the images you want to view. </p>
<p>You can use automatic scrolling for the time you set.  </p>
<p>You can also add the title you want to the images.</p>
<p>You can set corner radius images.</p>
</br>

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ImageSlideshow-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7587)
[![license](https://img.shields.io/github/license/denzcoskun/imageslideshow.svg?style=popout)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/denzcoskun/ImageSlideshow.svg)](https://jitpack.io/#denzcoskun/ImageSlideshow)

## Usage
- Add ImageSlider to your **layout**
```xml
<com.denzcoskun.imageslider.ImageSlider
        android:id="@+id/image_slider"
        android:layout_width="wrap_content"
        android:layout_height="300dp"
        app:auto_cycle="true"
        app:corner_radius="20"
        app:period="1000"
        app:delay="0"/>
```
- You can change placeholder image.
```xml
 app:placeholder="@drawable/placeholder"
```
- You can change error image.
```xml
 app:error_image="@drawable/error"
```
- You can change indicators.
```xml
app:selected_dot="@drawable/default_selected_dot"
app:unselected_dot="@drawable/default_unselected_dot"
```
- Add ImageSlider to your **Activity**
```kt
val imageList = ArrayList<SlideModel>()
// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title
// imageList.add(SlideModel("String Url" or R.drawable, "title", true) Also you can add centerCrop scaleType for this image
imageList.add(SlideModel("https://1.bp.blogspot.com/-GUZsgr8my50/XJUWOhyHyaI/AAAAAAAABUo/bljp3LCS3SUtj-judzlntiETt7G294WcgCLcBGAs/s1600/fox.jpg", "Foxes live wild in the city.", true))
imageList.add(SlideModel("https://2.bp.blogspot.com/-CyLH9NnPoAo/XJUWK2UHiMI/AAAAAAAABUk/D8XMUIGhDbwEhC29dQb-7gfYb16GysaQgCLcBGAs/s1600/tiger.jpg"))
imageList.add(SlideModel("https://3.bp.blogspot.com/-uJtCbNrBzEc/XJUWQPOSrfI/AAAAAAAABUs/ZlReSwpfI3Ack60629Rv0N8hSrPFHb3TACLcBGAs/s1600/elephant.jpg", "The population of elephants is decreasing in the world."))
val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
imageSlider.setImageList(imageList)
```
- You can add centerCrop scaleType for all images. 
```kt
imageSlider.setImageList(imageList, true) // centerCrop for all images
```
- You can use click listener. 
```kt
imageSlider.setItemClickListener(object : ItemClickListener {
    override fun onItemSelected(position: Int) {
	// You can listen here
    }
})
```
- You can add stop and start auto sliding again. 
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
## License
```
Copyright 2019 Deniz Coşkun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
