package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentRegisterBinding
import ufes.grad.mobile.communitylink.model.UserModel
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
                val name = binding.name.editText.text.toString()
                val cpf = binding.cpf.editText.text.toString()
                val address = binding.address.editText.text.toString()
                val phone = binding.phone.editText.text.toString()
                val sex = binding.sex.editText.text.toString()
                val dob = binding.date.editText.text.toString()
                try {
                    val user = UserModel(name, cpf, sex, dob, address, phone)
                    signupVM.registerNewUser(email, password, user).addOnSuccessListener {
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
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}