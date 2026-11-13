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
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.victor.player1.databinding.ActivityMainBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val baseMargin =
            (binding.bottomNavCard.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin

        val toolbarHeight = binding.toolbar.layoutParams.height
        val toolbarPaddingTop = binding.toolbar.paddingTop

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            binding.toolbar.updateLayoutParams<ViewGroup.LayoutParams> {
                height = toolbarHeight + bars.top
            }

            binding.toolbar.updatePadding(
                top = toolbarPaddingTop + bars.top
            )

            binding.bottomNavCard.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = baseMargin + bars.bottom
            }

            insets
        }

        binding.toolbar.inflateMenu(R.menu.top_menu)

        binding.toolbar.setNavigationIcon(R.drawable.arrow_back)
        binding.toolbar.setNavigationContentDescription(
            R.string.menu_superior_tornar
        )

        binding.toolbar.setNavigationOnClickListener {
            val fragmentActual =
                supportFragmentManager.findFragmentById(R.id.fragment_container)

            when (fragmentActual) {
                is HubFragment -> Unit

                is AchievementsFragment,
                is GroupsFragment,
                is MissionsFragment,
                is CreateMisionsFragment,
                is ProfileFragment -> irAlHub()

                else -> {
                    if (!supportFragmentManager.popBackStackImmediate()) {
                        irAlHub()
                    }
                }
            }
        }

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_ajuda -> {
                    navegarA(HelpFragment())
                    true
                }

                R.id.menu_preferencies -> {
                    navegarA(PreferencesFragment())
                    true
                }

                R.id.menu_hub -> {
                    irAlHub()
                    true
                }

                else -> false
            }
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

    private fun navegarA(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }

        mostrarMenu(false)
    }

    private fun irAlHub() {
        supportFragmentManager.popBackStackImmediate(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, HubFragment())
        }

        mostrarMenu(false)
    }

    fun mostrarMenu(mostrar: Boolean) {
        binding.bottomNavCard.visibility =
            if (mostrar) View.VISIBLE else View.GONE
    }

    fun seleccionarPestanya(id: Int) {
        binding.bottomNav.setOnItemSelectedListener(null)
        binding.bottomNav.selectedItemId = id

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
    }
}
