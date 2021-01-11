package com.example.marvel.view

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannedString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.underline
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.marvel.FullPath
import com.example.marvel.databinding.FragmentBlankBinding
import com.example.marvel.viewModel.DetailViewModel

class BlankFragment : Fragment() {
    lateinit var binding: FragmentBlankBinding
    lateinit var viewModel: DetailViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root


    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it).get(DetailViewModel::class.java)
            val urlPathBacground =
                "http://i.annihil.us/u/prod/marvel/i/mg/8/f0/5d0a407f40b9d/landscape_amazing.jpg"
            Glide.with(requireActivity()).load(urlPathBacground).into(binding.imgDetail)
            viewModel.comicLiveData.observe(this, Observer {
                binding.apply {
                    it.data.results.first().apply {
                        tvTitle.text = this.title

                        tvDescription.text = this.description
                        tvPrice.text = "Price ${this.prices.first().price}"
                        tvDate.text = "Published ${this.dates.first().date}"
                        tvPages.text = "Pages ${this.pageCount}"
                        val urlPath = this.images.first().path.FullPath(
                            this.images.first().extension,
                            "portrait_medium"
                        )

                        Glide.with(requireActivity()).load(urlPath).into(binding.imgComicDetail)
                    }


                }


            })
            binding.imgComicDetail.setOnClickListener {
                viewModel.goExpand()

            }
            binding.backArrow.setOnClickListener {

                viewModel.finish()
            }
        }


    }


}