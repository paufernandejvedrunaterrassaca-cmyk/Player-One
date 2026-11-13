package com.victor.player1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.victor.player1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val baseMargin =
            (binding.bottomNavCard.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            binding.bottomNavCard.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = baseMargin + bars.bottom
            }

            insets
        }

        binding.bottomNav.setOnItemSelectedListener { item ->

            supportFragmentManager.popBackStackImmediate(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )

            supportFragmentManager.commit {
                setReorderingAllowed(true)

                when (item.itemId) {
                    R.id.nav_achievements ->
                        replace(R.id.fragment_container, AchievementsFragment())

                    R.id.nav_groups ->
                        replace(R.id.fragment_container, GroupsFragment())

                    R.id.nav_missions ->
                        replace(R.id.fragment_container, MissionsFragment())

                    R.id.nav_create ->
                        replace(R.id.fragment_container, CreateMisionsFragment())

                    R.id.nav_profile ->
                        replace(R.id.fragment_container, ProfileFragment())
                }
            }

            mostrarMenu(true)

            true
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, HubFragment())
            }
        }
    }

    fun mostrarMenu(mostrar: Boolean) {
        binding.bottomNavCard.visibility =
            if (mostrar) View.VISIBLE else View.GONE
    }
}
