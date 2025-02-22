package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.fragments.DashboardFragment

class FragmentControllerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment_controller)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, DashboardFragment())
        }
    }

    fun setBotoomDrawer(){
//        val navHostFrag = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFrag.navController
//        binding.navigationDrawer.setupWithNavController(navController)
//        binding.bottomNavMenu.setupWithNavController(navController)
//        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
//        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}