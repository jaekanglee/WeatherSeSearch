package com.example.idusproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.idusproject.R
import com.example.idusproject.databinding.ActivityMainBinding
import com.example.idusproject.viewmodel.MainActViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewmodel : MainActViewModel by viewModel()
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this


        viewmodel.getSearchWordApiResult()



    }





}