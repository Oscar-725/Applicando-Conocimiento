package com.example.applicandoconocimiento.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.applicandoconocimiento.R
import com.example.applicandoconocimiento.databinding.ActivityMainBinding
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}