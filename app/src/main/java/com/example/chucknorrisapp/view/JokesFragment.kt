package com.example.chucknorrisapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chucknorrisapp.adapter.JokesRecyclerViewAdapter
import com.example.chucknorrisapp.databinding.FragmentJokeListBinding
import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.utils.UIState
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesFragment : Fragment() {

    private val viewModel by viewModel<JokeViewModel>()

    private var jokesRecyclerViewAdapter = JokesRecyclerViewAdapter()

    private lateinit var binding: FragmentJokeListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.allJokesObserver.observe(viewLifecycleOwner, ::handleResult)
        binding = FragmentJokeListBinding.inflate(inflater, container, false)
        binding.jokeRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokesRecyclerViewAdapter
        }
        binding.swipe.setOnRefreshListener{
            Toast.makeText(requireContext(),"Refreshing..", Toast.LENGTH_SHORT).show()
            viewModel.getRandomJokes()
            binding.swipe.isRefreshing = false
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRandomJokes()
    }

    private fun handleResult(result: UIState) {
        when(result) {
            is UIState.LOADING -> {  }
            is UIState.SUCCESS -> { newJokes(result.jokes) }
            is UIState.ERROR -> {  }
        }
    }

    private fun newJokes(jokes: List<Jokes>){
        jokesRecyclerViewAdapter.loadJokes(jokes)
    }

    companion object {
        @JvmStatic
        fun newInstance() = JokesFragment()
    }
}