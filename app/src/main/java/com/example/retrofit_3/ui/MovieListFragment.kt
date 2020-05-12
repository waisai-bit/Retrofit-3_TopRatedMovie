package com.example.retrofit_3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.VERTICAL
import com.example.retrofit_3.R
import com.example.retrofit_3.viewmodel.MovieViewModel
import com.goldengoals.retrofit_3.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class  MovieListFragment : Fragment() {
    private lateinit var movieListAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerMovie.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        movieListAdapter = MovieAdapter()
        recyclerMovie.adapter = movieListAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        movieViewModel = ViewModelProvider(this)
            .get(MovieViewModel::class.java)
        movieViewModel.loadMovie()
        movieViewModel.topRated.observe(
            viewLifecycleOwner, Observer {
                recyclerMovie.visibility = View.VISIBLE
                movieListAdapter.updateResultList(it)
            })

    }
}
