package com.example.chucknorrisapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.adapter.JokesRecyclerViewAdapter
import com.example.chucknorrisapp.databinding.FragmentButtonBinding
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ButtonFragment : Fragment() {

    private var jokesFragment = JokesFragment()

    private lateinit var binding: FragmentButtonBinding

    private val viewModel by viewModel<JokeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentButtonBinding.inflate(inflater, container, false)

        binding.randomJoke.setOnClickListener{
            getRandomJoke()
        }
        binding.endlessJokes.setOnClickListener {
            parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, jokesFragment)
                .addToBackStack(null)
            .commit()
        }
        binding.newHeroJoke.setOnClickListener {
            newHeroJoke()
        }
        return binding.root
    }

    private fun getRandomJoke(){
        viewModel.getRandomJoke()
    }

    private fun newHeroJoke(){
        viewModel.getNewHeroJoke("eg test")
    }

    companion object {
        @JvmStatic
        fun newInstance() = ButtonFragment()
    }
}