package ru.zudov.simplechartapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mockData = mutableListOf<Double>()
        repeat(10) {
            mockData.add(Random.nextDouble(1.0, 11.0))
        }

        chartView.addAll(mockData)
    }
}