# SimpleChartApp
[![](https://jitpack.io/v/IllidanStormrage1/SimpleChartApp.svg)](https://jitpack.io/#IllidanStormrage1/SimpleChartApp)

To get a Git project into your build:

### Step 1. Add the JitPack repository to your build file
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
### Step 2.   Add the dependency
```
dependencies {
	...
	implementation 'com.github.IllidanStormrage1:SimpleChartApp:v1.0'
}
```
### Step 3. How to use
Add in XML:
```
<ru.zudov.simplechartview.ChartView
	android:id="@+id/chartView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        app:axisColor="#614FC3F7"
        app:axisColorXY="#0277BD"
        app:axisXYWidthStroke="6dp"
        app:columnColor="#81D4FA"
        app:columnCornerRadius="6dp"
        app:columnSpacing="4dp" />
```
Add data in chart:
```
// Or chartView.add(1.0)
chartView.addAll(listOf(1.0,2.0)) 
```
* app:axisColor="#614FC3F7" - stripe color back

* pp:columnColor="#81D4FA" - column color

* app:axisColorXY="#0277BD" - color of two main axes

* app:axisXYWidthStroke="6dp" - width of two main axes

* app:columnCornerRadius="6dp" - column radius

* app:columnSpacing="4dp" - space between columns

### Step 4. Result
<img src="https://github.com/IllidanStormrage1/SimpleChartApp/blob/master/Screenshots/1.jpg" width="287"/>
<img src="https://github.com/IllidanStormrage1/SimpleChartApp/blob/master/Screenshots/2.jpg" width="287"/> 
