package com.example.marvel.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvel.adapter.HqAdapter
import com.example.marvel.databinding.ActivityMainBinding
import com.example.marvel.utils.Constants.Comic.COMIC
import com.example.marvel.viewModel.HqViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HqViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HqViewModel::class.java)

        viewModel.getHq()
        binding.rvHqMain.visibility = View.GONE
        binding.progressCircular.visibility = View.VISIBLE
        viewModel.hqLiveData.observe(this, {
            binding.rvHqMain.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.GONE
            binding.rvHqMain.apply {
                layoutManager = GridLayoutManager(this@MainActivity, 3)
                adapter = HqAdapter(it.data.results) { position ->
                    val comic = it.data.results[position]
                    val intent = Intent(this@MainActivity, ComicActivity::class.java)
                    intent.putExtra(COMIC, comic)
                    startActivity(intent)
                }
            }

        })
    }
}