package com.luukitoo.btuhomework2.screens.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.luukitoo.btuhomework2.databinding.FragmentMoviesListBinding
import com.luukitoo.btuhomework2.screens.MovieAdapter
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment() {

    private val viewModel by viewModels<MoviesListViewModel>()

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val movieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setListeners()
        setObservers()
    }

    private fun initRecyclerView() {
        binding.rvMovies.adapter = movieAdapter
    }

    private fun setListeners() {
        movieAdapter.setOnFavoriteClickListener { movie ->
            viewModel.toggleFavorite(movie)
        }
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collect { movies ->
                    movieAdapter.submitList(movies)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}
