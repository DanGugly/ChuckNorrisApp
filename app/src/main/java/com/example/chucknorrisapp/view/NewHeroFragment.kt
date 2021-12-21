package com.example.chucknorrisapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.databinding.FragmentNewHeroBinding
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewHeroFragment : Fragment() {

    private val viewModel by viewModel<JokeViewModel>()

    private lateinit var binding: FragmentNewHeroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewHeroBinding.inflate(inflater, container, false)
        binding.newHeroButton.setOnClickListener {
            getNewHero()
        }
        return binding.root
    }

    private fun getNewHero(){

    }

    companion object {
        @JvmStatic
        fun newInstance() = NewHeroFragment()
    }
}