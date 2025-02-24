package ufes.grad.mobile.communitylink.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.RegisterBinding
import ufes.grad.mobile.communitylink.viewmodel.SignupVM

class SignupActivity :  AppCompatActivity(), OnClickListener {
    private lateinit var binding: RegisterBinding
    private lateinit var signupVM: SignupVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signupVM = ViewModelProvider(this)[SignupVM::class.java]
        binding.createButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.create_button -> {
                val email = binding.email.editText.text.toString()
                val password = binding.password.editText.text.toString()
                if (email.isEmpty() or password.isEmpty())
                    return
                signupVM.registerNewUser(email, password).addOnSuccessListener{
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }
}