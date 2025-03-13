package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.service.autofill.DateTransformation
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.ui.input.InputMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentRegisterBinding
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.SignupVM

class SignupFragment : Fragment(R.layout.fragment_register), OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var signupVM: SignupVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupVM = ViewModelProvider(this)[SignupVM::class.java]
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.create_button -> {
                val email = binding.email.editText.text.toString()
                val password = binding.password.editText.text.toString()
                val userMap = hashMapOf(
                    "name" to binding.name.editText.text.toString(),
                    "cpf" to binding.cpf.editText.text.toString(),
                    "sex" to binding.sex.editText.text.toString(),
                    "dob" to binding.date.editText.text.toString(),
                    "address" to binding.address.editText.text.toString(),
                    "phone" to binding.phone.editText.text.toString()
                )
                try {
                    signupVM.registerNewUser(email, password, userMap).addOnSuccessListener {
                        Utilities.loadFragment(requireActivity(), LoginFragment())
                    }
                } catch (e: IllegalArgumentException) {
                    Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.createButton.setOnClickListener(this)
        binding.date.editText.inputType = InputType.TYPE_DATETIME_VARIATION_DATE
        binding.phone.editText.inputType = InputType.TYPE_CLASS_PHONE
        binding.cpf.editText.inputType = InputType.TYPE_CLASS_NUMBER
        binding.password.editText.transformationMethod = PasswordTransformationMethod()
        binding.repeat.editText.transformationMethod = PasswordTransformationMethod()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}