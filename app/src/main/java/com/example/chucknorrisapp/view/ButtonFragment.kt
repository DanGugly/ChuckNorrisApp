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
import com.example.chucknorrisapp.databinding.FragmentButtonBinding
import com.example.chucknorrisapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ButtonFragment : Fragment() {

    private lateinit var binding: FragmentButtonBinding

    private val viewModel by viewModel<JokeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentButtonBinding.inflate(inflater, container, false)

        binding.randomJoke.setOnClickListener{
            getRandomJoke()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    private fun getRandomJoke(){
        viewModel.getRandomJoke()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ButtonFragment()
    }
}