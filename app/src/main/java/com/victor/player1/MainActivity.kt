package com.victor.player1

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.victor.player1.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tabs: Map<Int, () -> Fragment> = mapOf(
        R.id.nav_achievements to { AchievementsFragment() },
        R.id.nav_groups to { GroupsFragment() },
        R.id.nav_missions to { MissionsFragment() },
        R.id.nav_create to { CreateMisionsFragment() },
        R.id.nav_profile to { ProfileFragment() }
    )

    private val tabOf: Map<KClass<out Fragment>, Int> = mapOf(
        AchievementsFragment::class to R.id.nav_achievements,
        GroupsFragment::class to R.id.nav_groups,
        MissionsFragment::class to R.id.nav_missions,
        CreateMisionsFragment::class to R.id.nav_create,
        ProfileFragment::class to R.id.nav_profile
    )

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

            val destination = tabs[item.itemId]?.invoke()
                ?: return@setOnItemSelectedListener false

            val current = supportFragmentManager.findFragmentById(
                R.id.fragment_container
            )

            if (current == null || current::class != destination::class) {

                supportFragmentManager.popBackStackImmediate(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )

                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        destination
                    )
                }
            }

            true
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {

                override fun onFragmentResumed(
                    fm: FragmentManager,
                    f: Fragment
                ) {
                    val tabId = tabOf[f::class]

                    binding.bottomNavCard.isVisible = tabId != null

                    if (tabId != null &&
                        binding.bottomNav.selectedItemId != tabId
                    ) {
                        binding.bottomNav.selectedItemId = tabId
                    }
                }

            },
            false
        )

        if (savedInstanceState == null) {

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    HubFragment()
                )
            }
        }
    }
}