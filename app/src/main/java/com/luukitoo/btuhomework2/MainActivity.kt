package com.luukitoo.btuhomework2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.luukitoo.btuhomework2.databinding.ActivityMainBinding
import com.luukitoo.btuhomework2.screens.favorive_movies.FavoriteMoviesFragment
import com.luukitoo.btuhomework2.screens.movies_list.MoviesListFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val fragmentAdapter by lazy {
        MainPagerAdapter(
            fragments = listOf(
                MoviesListFragment.newInstance(),
                FavoriteMoviesFragment.newInstance()
            ),
            fragmentManager = supportFragmentManager,
            lifecycleOwner = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() = with(binding) {
        viewPager.adapter = fragmentAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.apply {
                    setIcon(R.drawable.ic_movie)
                    setText("Movies")
                }
                1 -> tab.apply {
                    setIcon(R.drawable.ic_star)
                    setText("Favorites")
                }
            }
        }.attach()
    }
}