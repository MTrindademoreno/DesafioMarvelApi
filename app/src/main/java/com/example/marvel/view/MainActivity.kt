package com.example.marvel.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Slide
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvel.adapter.HqAdapter
import com.example.marvel.databinding.ActivityMainBinding
import com.example.marvel.viewModel.HqViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HqViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            exitTransition = Slide()


        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HqViewModel::class.java)

        viewModel.getHq()
        binding.rvHqMain.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        viewModel.hqLiveData.observe(this, Observer {
            binding.rvHqMain.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.rvHqMain.apply {
                layoutManager = GridLayoutManager(this@MainActivity, 3)
                adapter = HqAdapter(it.data.results) { position ->
                    val comic = it.data.results[position]
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("comic", comic)
                    startActivity(intent)
                }
            }

        })
    }
}