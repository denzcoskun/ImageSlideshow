<!--lint disable-->
<a href="https://github.com/denzcoskun/ImageSlideshow">  
<img src="https://user-images.githubusercontent.com/15522554/54867096-7169d700-4d8d-11e9-8f9a-bee162a01427.png">  
</a>  
<a href="https://github.com/denzcoskun/ImageSlideshow">  
<img align="left" src="https://user-images.githubusercontent.com/15522554/229944463-5957a2b5-81e2-4cf7-9efa-ab23313e9a39.gif"/>  
</a>  
<h1 align="center">Android Image Slider</h1>  
<p align="center">Just add the images you want to view. </p>  
<li>Easy to use. </li>
<li>Automatic scrolling for the time you set. </li>
<li>Add any caption to the images.</li>
<li>14 different slide animations.</li>
<li>Adjust corner radius images.</li>
<li>Use with Java or Kotlin.</p>
</br>

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ImageSlideshow-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7587)
[![license](https://img.shields.io/github/license/denzcoskun/imageslideshow.svg?style=popout)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/denzcoskun/ImageSlideshow.svg)](https://jitpack.io/#denzcoskun/ImageSlideshow)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/db19e5bd13bb4396a4adcbc1954325cc)](https://www.codacy.com/manual/denzcoskun/ImageSlideshow?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=denzcoskun/ImageSlideshow&amp;utm_campaign=Badge_Grade)

## Usage
- Add ImageSlider to your **layout**
```xml  
<com.denzcoskun.imageslider.ImageSlider    
     android:id="@+id/image_slider"    
     android:layout_width="wrap_content"    
     android:layout_height="300dp"    
     app:iss_auto_cycle="true"    
     app:iss_period="1000"    
     app:iss_delay="1000"    
     app:iss_text_align="CENTER"/>  
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
 app:iss_title_background="@drawable/gradient" //or app:iss_title_background="@android:color/holo_red_light"  
```  
-   Also change text color on xml. It is default white.
```xml  
 app:iss_text_color="#FFA0A0"   
```  
-   Indicators can be remove.
```xml  
 app:iss_no_dots="true"   
```  
-   You can use click listener or double click listener.
```kt  
imageSlider.setItemClickListener(object : ItemClickListener {    
    override fun onItemSelected(position: Int) {    
        // You can listen here.  
 }      
    override fun doubleClick(position: Int) {    
       // Do not use onItemSelected if you are using a double click listener at the same time.    
       // Its just added for specific cases.   
       // Listen for clicks under 250 milliseconds.  
 } })  
```  
-   You can add animation like that, <b>14 Animations added</b>. You can check in <a href="https://github.com/denzcoskun/ImageSlideshow/blob/master/imageslider/src/main/java/com/denzcoskun/imageslider/constants/AnimationTypes.kt"> Animation List </a>
```kt  
imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)  
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
	implementation 'com.github.denzcoskun:ImageSlideshow:0.1.2'
}
```  

## 📄 License

Copyright 2019 Deniz Coşkun

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

<a href="http://www.apache.org/licenses/LICENSE-2.0"><b>License</b></a>

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and limitations under the License.
<!--lint enable-->
