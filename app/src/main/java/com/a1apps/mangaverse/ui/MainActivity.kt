package com.a1apps.mangaverse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.a1apps.mangaverse.utils.MyApplication
import com.a1apps.mangaverse.R
import com.a1apps.mangaverse.databinding.ActivityMainBinding
import com.a1apps.mangaverse.viewmodel.MangaVMFactory
import com.a1apps.mangaverse.viewmodel.MangaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mangaViewModel: MangaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as MyApplication).mangaRepository

        mangaViewModel =
            ViewModelProvider(this, MangaVMFactory(repository))[MangaViewModel::class.java]

        replaceFragment(SplashFragment())

        binding.bottomBar.setOnItemSelectedListener {
            when (it) {
                0 -> {
                    replaceFragment(HomeFragment())
                }

                1 -> {
                    replaceFragment(SearchFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }

}