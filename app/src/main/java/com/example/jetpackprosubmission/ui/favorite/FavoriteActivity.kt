package com.example.jetpackprosubmission.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.ui.favorite.favmovie.FavMovieFragment
import com.example.jetpackprosubmission.ui.favorite.favtvshow.FavTvShowFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_favorite.*


class FavoriteActivity : AppCompatActivity() {
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val favMovieFragment: FavMovieFragment = FavMovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        if (savedInstanceState == null) {
            activity_favorite_bottomnavigationbar.setItemSelected(R.id.menu_movie, true)
            favMovieFragment.let {
                fragmentManager.beginTransaction().replace(
                    R.id.activity_favorite_fragmentcontainer,
                    it
                )
                    .commit()
            }
        }
        activity_favorite_bottomnavigationbar.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
                when (id) {
                    R.id.menu_movie -> fragment = FavMovieFragment()
                    R.id.menu_tvshow -> fragment = FavTvShowFragment()
                }
                if (fragment != null) {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.let {
                        it.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        it.replace(R.id.activity_favorite_fragmentcontainer, fragment)
                        it.commit()
                    }
                }
            }
        })
    }
}