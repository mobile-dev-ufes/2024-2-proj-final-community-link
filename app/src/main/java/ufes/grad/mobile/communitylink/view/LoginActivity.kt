package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import ufes.grad.mobile.communitylink.databinding.LoginBinding
import ufes.grad.mobile.communitylink.viewmodel.LoginVM

class LoginActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var binding: LoginBinding
    private lateinit var viewModel: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onClick(view: View) {

    }
}