package ufes.grad.mobile.communitylink.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LoginBinding
import ufes.grad.mobile.communitylink.utils.Utilities
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

        if (loginVM.userLogedIn() or false) { TODO("MUDAR AQUII ESSE FALSE PARA LOGAR DIRETO")
            startActivity(Intent(this, FragmentControllerActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.login_button -> {
                val email = binding.email.editText.text.toString()
                val password = binding.password.editText.text.toString()
                try {
                    loginVM.loginUser(email, password).addOnSuccessListener {
                        startActivity(Intent(this, FragmentControllerActivity::class.java))
                        finish()
                    }
                } catch(e: Exception) {
                    Utilities.notify(application, getString(R.string.preencha_todos_os_campos))
                }
            }
            R.id.register_button -> {
                startActivity(Intent(this, SignupActivity::class.java))
            }
        }
    }
}