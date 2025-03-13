package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ufes.grad.mobile.communitylink.databinding.ActivityLoginBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.fragments.LoginFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Utilities.loadFragment(this, LoginFragment())
    }
}
