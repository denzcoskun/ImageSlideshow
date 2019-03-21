# ImageSlideshow

![imageslideshow](https://user-images.githubusercontent.com/15522554/54722650-009ca200-4b76-11e9-8478-c4ad0aa64aab.jpg)

### Android Image Slider

![myslide](https://user-images.githubusercontent.com/15522554/54739898-b09bfa80-4bca-11e9-9248-86161b587116.gif)
## Usage
- Add ImageSlider to your **layout**
```xml
<com.denzcoskun.imageslider.ImageSlider
        android:id="@+id/image_slider"
        android:layout_width="wrap_content"
        android:layout_height="300dp"
        app:auto_play="true"
        app:period="1000"
        app:delay="0"/>
```
- You can change indicators.
```xml
app:selected_dot="@drawable/default_selected_dot"
app:unselected_dot="@drawable/default_unselected_dot"
```
- Add ImageSlider to your **Activity**
```kt
 var imagelist = ArrayList<String>()
 imagelist.add("https://images4.alphacoders.com/740/thumb-1920-740289.png")
 imagelist.add("https://i.pinimg.com/originals/fd/c9/98/fdc9984f77e922212d62e0cd330b10c6.png")
 imagelist.add("https://d37x086vserhlm.cloudfront.net/wp-content/uploads/2016/11/24001621/your-name.-film2-e1480003095479.jpeg")
 val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
 imageSlider.setImageList(imagelist)
```
- If you add `UserClickCallbaks` to your **Activity**, you can use click listener. 
```kt
class MainActivity : AppCompatActivity(), UserClickCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagelist = ArrayList<String>()
        imagelist.add("https://images4.alphacoders.com/740/thumb-1920-740289.png")
        imagelist.add("https://i.pinimg.com/originals/fd/c9/98/fdc9984f77e922212d62e0cd330b10c6.png")
        imagelist.add("https://d37x086vserhlm.cloudfront.net/wp-content/uploads/2016/11/24001621/your-name.-film2-e1480003095479.jpeg")
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imagelist, this)
    }

    override fun onUserClick(position: Int) {
        // You can use here
    }
}
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
	implementation 'com.github.denzcoskun:ImageSlideshow:0.0.1'
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
