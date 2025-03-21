package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.ActivityLoginBinding
import ufes.grad.mobile.communitylink.view.fragments.LoginFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, LoginFragment(), null)
            .addToBackStack(null)
            .commit()
    }
}
