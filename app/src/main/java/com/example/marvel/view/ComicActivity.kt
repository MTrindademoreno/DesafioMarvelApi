package com.example.marvel.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.R
import com.example.marvel.model.Result
import com.example.marvel.utils.Constants.Comic.COMIC
import com.example.marvel.viewModel.DetailViewModel

class ComicActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)
        supportActionBar?.hide()
        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)


        val comic = intent.getParcelableExtra<Result>(COMIC)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        comic?.id?.let { viewModel.getComic(it) }

        viewModel.expandGo.observe(this, Observer {
            loadFragment(ExpandFragment())
        })

        viewModel.finishLiveData.observe(this, Observer {
            finish()
        })
        viewModel.fragmentLiveData.observe(this, Observer {
            loadFragment(it)
        })

      loadFragment(DetailFragment())
    }

    private fun loadFragment(fragment:Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.box,fragment)
        ft.commit()
    }
}