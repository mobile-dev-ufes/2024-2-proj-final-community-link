package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.ActivityDashboardBinding

class FragmentControllerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomDrawer()
    }

    fun setBottomDrawer() {
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFrag.navController

        binding.bottomNavMenu.setupWithNavController(navController)
    }
}