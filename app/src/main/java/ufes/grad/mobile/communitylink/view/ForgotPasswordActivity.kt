package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.ActivityForgotPasswordBinding
import ufes.grad.mobile.communitylink.view.fragments.ForgotPasswordFragment

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, ForgotPasswordFragment(), null)
            .addToBackStack(null)
            .commit()
    }
}
