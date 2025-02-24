package ufes.grad.mobile.communitylink.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LoginBinding
import ufes.grad.mobile.communitylink.viewmodel.LoginVM

class LoginActivity :  AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: LoginBinding
    private lateinit var loginVM: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginVM = ViewModelProvider(this)[LoginVM::class.java]
        binding.loginButton.setOnClickListener(this)
        binding.registerButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.login_button -> {
                val email = binding.email.editText.text.toString()
                val password = binding.password.editText.text.toString()
                if (email.isEmpty() or password.isEmpty())
                    return
                loginVM.loginUser(email, password).addOnSuccessListener{
                    startActivity(Intent(this, FragmentControllerActivity::class.java))
                    finish()
                }
            }
            R.id.register_button -> {
                startActivity(Intent(this, SignupActivity::class.java))
            }
        }
    }
}