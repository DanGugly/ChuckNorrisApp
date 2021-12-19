package com.example.chucknorrisapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chucknorrisapp.adapter.JokesRecyclerViewAdapter
import com.example.chucknorrisapp.databinding.FragmentJokeListBinding
import com.example.chucknorrisapp.model.Jokes
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesFragment : Fragment(), Contract {

    private val viewModel by viewModel<JokeViewModel>()

    private var jokesRecyclerViewAdapter = JokesRecyclerViewAdapter()

    private lateinit var binding: FragmentJokeListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initContract(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokeListBinding.inflate(inflater, container, false)
        binding.jokeRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokesRecyclerViewAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRandomJokes()
    }

    override fun newJokes(jokes: List<Jokes>){
        jokesRecyclerViewAdapter.loadJokes(jokes)
    }

    companion object {
        @JvmStatic
        fun newInstance() = JokesFragment()
    }
}

interface Contract{
    fun newJokes(jokes: List<Jokes>)
}