package com.example.marvel.view

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.marvel.FullPath
import com.example.marvel.databinding.ActivityDetailBinding
import com.example.marvel.model.Result
import com.example.marvel.viewModel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val urlPathBacground =
            "http://i.annihil.us/u/prod/marvel/i/mg/8/f0/5d0a407f40b9d/landscape_amazing.jpg"
        Glide.with(applicationContext).load(urlPathBacground).into(binding.imgDetail)

        val comic = intent.getParcelableExtra<Result>("comic")


        binding.imgComicDetail.setOnClickListener {
            Toast.makeText(this, "ouuuu", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        comic?.id?.let { viewModel.getComic(it) }

        viewModel.comicLiveData.observe(this, Observer {
            it.data.results.first().apply {
                binding.tvTitle.text = title
                binding.tvDescription.text = textObjects.firstOrNull()?.text
                binding.tvPrice.text = "Price ${prices.first()?.price}"
                binding.tvDate.text= "Published ${dates.first()?.date}"
                binding.tvPages.text ="Pages ${pageCount}"


                val urlPath = this.images.first().path.FullPath(
                    this.images.first().extension,
                    "portrait_medium"
                )

                Glide.with(applicationContext).load(urlPath).into(binding.imgComicDetail)

            }


        })


    }
}