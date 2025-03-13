package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.ActivityDashboardBinding

class FragmentControllerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbarAndBottomDrawer()
    }

    fun setToolbarAndBottomDrawer() {
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFrag.navController
        binding.bottomNavMenu.setupWithNavController(navController)

        // Define fragments principais
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.exploreFragment, R.id.dashboardFragment, R.id.profileFragment)
        )

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        // muda visibilidade dinamicamente
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.visibility =
                if (destination.id in
                    setOf(R.id.dashboardFragment, R.id.exploreFragment, R.id.profileFragment))
                View.GONE else View.VISIBLE
        }
    }
}