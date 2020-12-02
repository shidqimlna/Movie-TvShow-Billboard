package com.example.jetpackprosubmission.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.ui.favorite.FavoriteActivity
import com.example.jetpackprosubmission.ui.movie.MovieFragment
import com.example.jetpackprosubmission.ui.tvshow.TvShowFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val movieFragment: MovieFragment = MovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            activity_main_bottomnavigationbar.setItemSelected(R.id.menu_movie, true)
            movieFragment.let {
                fragmentManager.beginTransaction().replace(
                    R.id.activity_main_fragmentcontainer,
                    it
                )
                    .commit()
            }
        }
        activity_main_bottomnavigationbar.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
                when (id) {
                    R.id.menu_movie -> fragment = MovieFragment()
                    R.id.menu_tvshow -> fragment = TvShowFragment()
                }
                if (fragment != null) {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.let {
                        it.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        it.replace(R.id.activity_main_fragmentcontainer, fragment)
                        it.commit()
                    }
                }
            }
        })
        activity_main_fab_favorite.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}