package ufes.grad.mobile.communitylink.view.fragments

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentLoginBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.utils.Utilities.Companion.setLocale
import ufes.grad.mobile.communitylink.view.FragmentControllerActivity
import ufes.grad.mobile.communitylink.view.SignUpActivity
import ufes.grad.mobile.communitylink.viewmodel.LoginVM

class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var loginVM: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginVM = ViewModelProvider(this)[LoginVM::class.java]

        if (loginVM.userLogedIn()) {
            startActivity(Intent(context, FragmentControllerActivity::class.java))
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.loginButton.id -> {
                val email = binding.email.editText.text.toString().trim()
                val password = binding.password.editText.text.toString()
                try {
                    loginVM.loginUser(email, password).addOnSuccessListener {
                        startActivity(Intent(context, FragmentControllerActivity::class.java))
                    }
                } catch (e: Exception) {
                    Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                    if (binding.email.editText.text.toString().isBlank()) {
                        binding.email.editText.setHintTextColor(
                            requireContext().getColor(R.color.red)
                        )
                    }
                    if (binding.email.editText.text.toString().isBlank()) {
                        binding.password.editText.setHintTextColor(
                            requireContext().getColor(R.color.red)
                        )
                    }
                }
            }
            binding.registerButton.id -> {
                startActivity(Intent(context, SignUpActivity::class.java))
            }
            binding.forgotPasswordButton.id -> {
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                findNavController().navigate(action)
            }

            binding.changeLanguage.id -> {
                toggleLanguage()
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
        binding.forgotPasswordButton.setOnClickListener(this)
        binding.email.editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        binding.password.editText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.password.editText.transformationMethod = PasswordTransformationMethod()
        binding.changeLanguage.setOnClickListener(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCurrentLanguage(): String {
        return resources.configuration.locales[0].language
    }

    private fun toggleLanguage() {
        val newLang = if (getCurrentLanguage() == "en") "pt" else "en"
        setLocale(requireContext(), newLang)
        requireActivity().recreate() // Restart activity to apply changes
    }
}
