package com.a1apps.mangaverse.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.a1apps.mangaverse.R
import com.a1apps.mangaverse.databinding.ActivityMainBinding
import com.a1apps.mangaverse.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {


    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide bottom Bar view
        requireActivity().findViewById<View>(R.id.bottomBar).visibility = View.GONE

        val handler = Handler()
        var progress = 0
        handler.postDelayed(object : Runnable {
            override fun run() {
                progress++
                binding.progressBar.progress = progress

                if (progress < 100) {
                    handler.postDelayed(this, 10)
                } else {

                    // Load HomeFragment after 1 second
                    handler.postDelayed({
                        replaceFragment(HomeFragment())
                    }, 200)
                }
            }
        }, 10)
     }

    fun replaceFragment( fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }

}