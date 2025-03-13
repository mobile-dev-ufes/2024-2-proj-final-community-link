package ufes.grad.mobile.communitylink.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentRegisterBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.SignupVM
import java.util.Calendar

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
                val confirmPasswod = binding.repeat.editText.text.toString()
                if(password != confirmPasswod){
                    Utilities.notify(requireContext(), getString(R.string.match_passwords))
                    binding.password.editText.setTextColor(getColor(requireContext(), R.color.red))
                    binding.repeat.editText.setTextColor(getColor(requireContext(), R.color.red))
                    return
                }
                val userMap = hashMapOf(
                    "name" to binding.name.editText.text.toString(),
                    "cpf" to binding.cpf.editText.text.toString(),
                    "sex" to binding.sex.editText.text.toString(),
                    "dob" to binding.buttonDate.text.toString(),
                    "address" to binding.address.editText.text.toString(),
                    "phone" to binding.phone.editText.text.toString()
                )
                try {
                    signupVM.registerNewUser(email, password, userMap).addOnSuccessListener {
                        Utilities.loadFragment(requireActivity(), LoginFragment())
                    }
                } catch (e: IllegalArgumentException) {
                    Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                    showMissingFields()
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
        binding.phone.editText.inputType = InputType.TYPE_CLASS_PHONE
        binding.cpf.editText.inputType = InputType.TYPE_CLASS_NUMBER
        binding.password.editText.transformationMethod = PasswordTransformationMethod()
        binding.repeat.editText.transformationMethod = PasswordTransformationMethod()

        binding.buttonDate.setOnClickListener {
            val listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(dp: DatePicker?, year: Int, month: Int, day: Int) {
                    binding.buttonDate.text = buildString {
                                                append(day)
                                                append("/")
                                                append(month+1)
                                                append("/")
                                                append(year)
                                            }
                    binding.buttonDate.setTextColor(getColor(requireContext(), R.color.black))
                }
            }
            val cal = Calendar.getInstance()
            DatePickerDialog(requireContext(), listener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showMissingFields() {
        if (binding.email.editText.text.toString().isEmpty()) {
            binding.email.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if  (binding.password.editText.text.toString().isEmpty()){
            binding.password.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if  (binding.repeat.editText.text.toString().isEmpty()){
            binding.repeat.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if (binding.name.editText.text.toString().isEmpty()) {
            binding.name.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if (binding.cpf.editText.text.toString().isEmpty()) {
            binding.cpf.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if (binding.phone.editText.text.toString().isEmpty()) {
            binding.phone.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if (binding.address.editText.text.toString().isEmpty()) {
            binding.address.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if (binding.sex.editText.text.toString().isEmpty()) {
            binding.sex.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        Utilities.notify(requireContext(), binding.buttonDate.text.toString())
        if (binding.buttonDate.text.toString() == getString(R.string.selecione_uma_data)) {
            binding.buttonDate.setTextColor(getColor(requireContext(), R.color.red))
        }
    }
}