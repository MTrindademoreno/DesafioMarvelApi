package com.example.marvel.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.marvel.FullPath
import com.example.marvel.R
import com.example.marvel.databinding.FragmentBlankBinding
import com.example.marvel.databinding.FragmentExpandBinding
import com.example.marvel.viewModel.DetailViewModel


class ExpandFragment : Fragment() {
    lateinit var binding: FragmentExpandBinding
    lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpandBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it).get(DetailViewModel::class.java)
            viewModel.comicLiveData.observe(this, Observer { hq->
                val urlExpandImg = hq.data.results.first().images.first().path.FullPath(
                    hq.data.results.first().images.first().extension,
                    "portrait_incredible"
                )
                Glide.with(it).load(urlExpandImg).into(binding.imgExpand)
            })
            binding.finishIcon.setOnClickListener {
                viewModel.backFragment(BlankFragment())
            }

        }

    }
}