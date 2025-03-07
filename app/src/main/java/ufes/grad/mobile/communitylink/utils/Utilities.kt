package ufes.grad.mobile.communitylink.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import ufes.grad.mobile.communitylink.R

class Utilities {
    companion object {
        fun notify(context: Context?, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun loadFragment(fragmentActivity: FragmentActivity, fragment: Fragment) {
            fragmentActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment, null)
                .addToBackStack(null).commit()
        }
    }
}