package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.ActivitySignupBinding
import ufes.grad.mobile.communitylink.view.fragments.SignupFragment

class SignUpActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, SignupFragment(), null)
            .addToBackStack(null)
            .commit()
    }

}