package com.example.chucknorrisapp.view

import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.RecyclerView

class JokesFragment : Fragment() {

    private val viewModel by viewModel<JokeViewModel>()

    private var jokesRecyclerViewAdapter = JokesRecyclerViewAdapter()

    private lateinit var binding: FragmentJokeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.allJokesObserver.observe(viewLifecycleOwner, ::handleResult)

        binding = FragmentJokeListBinding.inflate(inflater, container, false)

        binding.jokeRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokesRecyclerViewAdapter

            /**
             * Adding a scroll listener you can create a never ending list, once you are in last item visible
             * you load more items and so on
             */
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val endHasBeenReached = lastVisible + 5 >= totalItemCount

                    if (totalItemCount > 0 && endHasBeenReached) {
                        viewModel.getRandomJokes()
                    }
                }
            })
        }

        // try to avoid libraries that are not manage by google
        // there are nice libraries out there but it can be risky to use them
        // for refresh we have the SwipeRefresh layout
        // now for a recycler view you can take the position of the latest item and then load more data

//        binding.swipe.setOnRefreshListener{
//            Toast.makeText(requireContext(),"Refreshing..", Toast.LENGTH_SHORT).show()
//            viewModel.getRandomJokes()
//            binding.swipe.isRefreshing = false
//        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRandomJokes()
    }

    private fun handleResult(result: UIState) {
        when(result) {
            is UIState.LOADING -> {
                // lets add something here
            }
            is UIState.SUCCESS -> { newJokes(result.jokes) }
            is UIState.ERROR -> {
                Log.e("JokesFragment", result.error.localizedMessage)
                // lets display an error here
            }
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