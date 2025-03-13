package ufes.grad.mobile.communitylink.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentLoginBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.LoginVM

class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var loginVM: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginVM = ViewModelProvider(this)[LoginVM::class.java]

        if (loginVM.userLogedIn() or true) {
            startActivity(Intent(context, FragmentControllerActivity::class.java))
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.loginButton.id -> {
                val email = binding.email.editText.text.toString()
                val password = binding.password.editText.text.toString()
                try {
                    loginVM.loginUser(email, password).addOnSuccessListener {
                        startActivity(Intent(context, FragmentControllerActivity::class.java))
                    }
                } catch (e: Exception) {
                    Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                }
            }
            binding.registerButton.id -> {
                Utilities.loadFragment(requireActivity(), SignupFragment())
            }
            binding.passwordButton.id -> {
                Utilities.loadFragment(requireActivity(), ForgotPasswordFragment())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener(this)
        binding.registerButton.setOnClickListener(this)
        binding.passwordButton.setOnClickListener(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
