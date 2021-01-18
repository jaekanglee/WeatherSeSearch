package com.example.idusproject.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.idusproject.R
import com.example.idusproject.databinding.ActivityMainBinding
import com.example.idusproject.view.adapter.WeatherAdapter
import com.example.idusproject.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModel()
    private val adapter: WeatherAdapter by lazy {
        WeatherAdapter(
            requestManager,
            viewmodel
        )
    }
    private val requestManager: RequestManager by lazy {
        Glide.with(this)
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bindView()
        setObserver()
        viewmodel.getSearchWordApiResult()
    }


    fun bindView() {
        binding.viewmodel = viewmodel
        binding.adapter = adapter
        binding.lifecycleOwner = this

    }

    fun setObserver() {
        viewmodel.lAdapterNotify.observe(this, Observer {
            if (it) {
                adapter.notifyDataSetChanged()
            }
        })
        viewmodel._error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}