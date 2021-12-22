package com.example.chucknorrisapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chucknorrisapp.databinding.FragmentNewHeroBinding
import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.utils.UIState
import com.example.chucknorrisapp.utils.validateHero
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewHeroFragment : Fragment() {

    private val viewModel by viewModel<JokeViewModel>()

    private lateinit var binding: FragmentNewHeroBinding

    private var explicit : Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            explicit = it.getBoolean("ExplicitVal")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.heroJokeObserver.observe(viewLifecycleOwner, ::handleResult)
        binding = FragmentNewHeroBinding.inflate(inflater, container, false)
        binding.newHeroButton.setOnClickListener {
            val hero : MutableList<String>? = validateHero(binding.newHero.text.toString())
            if(!hero.isNullOrEmpty()){
                getNewHero(hero[0], hero[1])
            } else{
                Toast.makeText(
                    requireContext(),
                    "Please enter in format of Firstname Lastname..\n" +
                            "No special characters allowed!",
                    Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun getNewHero(first: String, last: String){
        explicit?.let { viewModel.getNewHeroJoke(first, last, it) }
    }

    private fun handleResult(uiState: UIState) {
        when(uiState) {
            is UIState.LOADING -> {  }
            is UIState.SUCCESS_SINGLE -> { getHeroJoke(uiState.joke) }
            is UIState.ERROR -> {  }
        }
    }

    private fun getHeroJoke(joke : Jokes){
        binding.newHeroJoke.text = joke.value[0].joke
    }

    companion object {
        @JvmStatic
        fun newInstance(nonExplicit : Boolean) = NewHeroFragment()
    }
}