
package com.victor.player1

import android.os.Bundle
import android.view.View
import com.victor.player1.databinding.FragmentHubBinding

class HubFragment : BindingFragment<FragmentHubBinding>(FragmentHubBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWelcome.setOnClickListener {
            navigateTo(WelcomeFragment())
        }

        binding.btnLogin.setOnClickListener {
            navigateTo(LoginFragment())
        }

        binding.btnRegister.setOnClickListener {
            navigateTo(RegisterFragment())
        }

        binding.btnMissionList.setOnClickListener {
            navigateTo(MissionsFragment())
        }

        binding.btnMissionDetail.setOnClickListener {
            navigateTo(ViewMisionFragment())
        }

        binding.btnAchievements.setOnClickListener {
            navigateTo(AchievementsFragment())
        }

        binding.btnGladiador.setOnClickListener {
            navigateTo(GladiadorFragment())
        }

        binding.btnErudito.setOnClickListener {
            navigateTo(EruditoFragment())
        }

        binding.btnArtesano.setOnClickListener {
            navigateTo(ArtesanoFragment())
        }

        binding.btnExplorador.setOnClickListener {
            navigateTo(ExploradorFragment())
        }

        binding.btnGuardian.setOnClickListener {
            navigateTo(GuardianFragment())
        }

        binding.btnSettings.setOnClickListener {
            navigateTo(PreferencesFragment())
        }

        binding.btnHelp.setOnClickListener {
            navigateTo(HelpFragment())
        }
    }
}
