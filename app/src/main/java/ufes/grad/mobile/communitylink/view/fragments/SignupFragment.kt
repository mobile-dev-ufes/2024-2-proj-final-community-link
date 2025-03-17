package ufes.grad.mobile.communitylink.view.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.util.Calendar
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.databinding.FragmentRegisterBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.LoginActivity
import ufes.grad.mobile.communitylink.view.popups.BasePopup
import ufes.grad.mobile.communitylink.viewmodel.SignupVM

class SignupFragment : Fragment(R.layout.fragment_register), OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var signupVM: SignupVM

    private var sex: String = ""
    private var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupVM = ViewModelProvider(this)[SignupVM::class.java]
        sex = getString(R.string.sex)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.buttonDate.id -> {
                val cal = Calendar.getInstance()
                val datePicker =
                    DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            {
                                date = "$day/${month + 1}/$year"
                                binding.buttonDate.setTextColor(
                                    getColor(requireContext(), R.color.black)
                                )
                            }
                        },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    )
                binding.buttonDate.text = date
                datePicker.datePicker.maxDate = cal.timeInMillis
                datePicker.show()
            }
            binding.createButton.id -> {
                val password = binding.password.editText.text.toString()
                val confirmPassword = binding.repeat.editText.text.toString()
                if (password != confirmPassword) {
                    Utilities.notify(requireContext(), getString(R.string.match_passwords))
                    binding.password.editText.setTextColor(getColor(requireContext(), R.color.red))
                    binding.repeat.editText.setTextColor(getColor(requireContext(), R.color.red))
                    return
                }

                val name = binding.name.editText.text.toString().trim()
                val email = binding.email.editText.text.toString().trim()
                val cpf = binding.cpf.editText.text.toString()
                val address = binding.address.editText.text.toString().trim()
                val dob = binding.buttonDate.text.toString()
                val phone = binding.phone.editText.text.toString()

                if (sex == getString(R.string.sex)) {
                    Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                    showMissingFields()
                } else {
                    val user =
                        UserModel(
                            name = name,
                            email = email,
                            cpf = cpf,
                            sex = sex,
                            dob = dob,
                            address = address,
                            phone = phone
                        )

                    try {
                        signupVM.registerNewUser(email, password, user).addOnSuccessListener {
                            val popup =
                                BasePopup(
                                    BasePopup.PopupType.ONE_BUTTON,
                                    R.layout.popup_new_account,
                                    false
                                )
                            popup.onConfirm = {
                                startActivity(Intent(context, LoginActivity::class.java))
                                requireActivity().finish()
                            }
                            popup.show(childFragmentManager, "")
                        }
                    } catch (_: IllegalArgumentException) {
                        Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                        showMissingFields()
                    }
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

        setupFilters()

        binding.buttonDate.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupFilters() {
        val status =
            listOf(
                getString(R.string.sex),
                getString(R.string.male),
                getString(R.string.female),
                getString(R.string.other)
            )
        binding.sex.adapter = SpinnerAdapter(requireContext(), status)
        binding.sex.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    sex = status[position]
                    val adapter = binding.sex.adapter as? SpinnerAdapter
                    adapter?.setValid(true)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun showMissingFields() {
        if (binding.email.editText.text.toString().isEmpty()) {
            binding.email.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if (binding.password.editText.text.toString().isEmpty()) {
            binding.password.editText.setHintTextColor(getColor(requireContext(), R.color.red))
        }
        if (binding.repeat.editText.text.toString().isEmpty()) {
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
        if (sex == getString(R.string.sex)) {
            val adapter = binding.sex.adapter as? SpinnerAdapter
            adapter?.setValid(false)
        }
        if (binding.buttonDate.text.toString() == getString(R.string.selecione_uma_data)) {
            binding.buttonDate.setTextColor(getColor(requireContext(), R.color.red))
        }
    }
}
