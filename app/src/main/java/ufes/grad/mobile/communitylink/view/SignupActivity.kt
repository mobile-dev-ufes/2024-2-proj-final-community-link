package ufes.grad.mobile.communitylink.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.type.DateTime
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.RegisterBinding
import ufes.grad.mobile.communitylink.model.User
import ufes.grad.mobile.communitylink.utils.Utilities
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
                val name = binding.name.editText.text.toString()
                val cpf = binding.cpf.editText.text.toString()
                val address = binding.address.editText.text.toString()
                val phone = binding.phone.editText.text.toString()
                val sex = binding.sex.editText.text.toString()
                val dob = binding.date.editText.text.toString()
                try {
                    val user = User(name, cpf, sex, dob, address, phone)
                    signupVM.registerNewUser(email, password, user).addOnSuccessListener{
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                } catch (e: IllegalArgumentException){
                    Utilities.notify(application, getString(R.string.preencha_todos_os_campos))
                }
            }
        }
    }
}