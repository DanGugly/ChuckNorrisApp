package com.example.chucknorrisapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chucknorrisapp.databinding.FragmentButtonBinding
import com.example.chucknorrisapp.model.SingleJoke
import com.example.chucknorrisapp.rest.NetworkApi
import com.example.chucknorrisapp.utils.UIState
import com.example.chucknorrisapp.utils.switchFragments
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * When switching between fragment avoid creating the instances by yourself
 * you have a method called newInstance() on each fragment that is the one we can use
 */
class ButtonFragment : Fragment() {

    /**
     * This is another way to initialize the binding
     */
    private val binding: FragmentButtonBinding by lazy {
        FragmentButtonBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<JokeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.randomJokeObserver.observe(viewLifecycleOwner, ::handleResult)

        binding.checkBox.setOnClickListener{
            NetworkApi.isNonExplicit = binding.checkBox.isChecked
        }

        binding.randomJoke.setOnClickListener{
            viewModel.getRandomJoke()
        }
        binding.endlessJokes.setOnClickListener {
            switchFragments(parentFragmentManager, JokesFragment.newInstance())
        }
        binding.newHeroJoke.setOnClickListener {
            switchFragments(parentFragmentManager, NewHeroFragment.newInstance())
        }

        return binding.root
    }

    private fun handleResult(uiState: UIState) {
        when(uiState) {
            is UIState.LOADING -> { 
                // here you can display a spinner loading the data
            }
            is UIState.SUCCESS_SINGLE -> { getRandomJoke(uiState.joke) }
            is UIState.ERROR -> {
                Log.e("RandomJoke", uiState.error.localizedMessage)
                // here we need to handle the error displaying Toast or dialog
            }
        }
    }

    private fun getRandomJoke(joke : SingleJoke){
        binding.randomJokeText.text = joke.value.joke
    }

    companion object {
        @JvmStatic
        fun newInstance() = ButtonFragment()
    }
}