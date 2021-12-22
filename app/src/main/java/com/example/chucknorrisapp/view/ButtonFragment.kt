package com.example.chucknorrisapp.view

import android.os.Bundle
import android.util.Log
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
import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.utils.UIState
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ButtonFragment : Fragment() {

    private var jokesFragment = JokesFragment()

    private var heroFragment = NewHeroFragment()

    private lateinit var binding: FragmentButtonBinding

    private val viewModel by viewModel<JokeViewModel>()

    private var explicit : Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.randomJokeObserver.observe(viewLifecycleOwner, ::handleResult)

        binding = FragmentButtonBinding.inflate(inflater, container, false)

        binding.checkBox.setOnClickListener{
            explicit = binding.checkBox.isChecked
        }

        binding.randomJoke.setOnClickListener{
            viewModel.getRandomJoke(explicit)
        }
        binding.endlessJokes.setOnClickListener {
            jokesFragment.apply {
                arguments = Bundle().apply {
                    putBoolean("ExplicitVal", explicit)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, jokesFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.newHeroJoke.setOnClickListener {
            heroFragment.apply {
                arguments = Bundle().apply {
                    putBoolean("ExplicitVal", explicit)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, heroFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

    private fun handleResult(uiState: UIState) {
        when(uiState) {
            is UIState.LOADING -> {  }
            is UIState.SUCCESS_SINGLE -> { getRandomJoke(uiState.joke) }
            is UIState.ERROR -> {  }
        }
    }

    private fun getRandomJoke(joke : Jokes){
        binding.randomJokeText.text = joke.value[0].joke
    }

    companion object {
        @JvmStatic
        fun newInstance() = ButtonFragment()
    }
}