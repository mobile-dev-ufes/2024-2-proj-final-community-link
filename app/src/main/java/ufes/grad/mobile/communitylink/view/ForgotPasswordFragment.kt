package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        binding.password.editText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.password.editText.transformationMethod = PasswordTransformationMethod()
        binding.confirmPassword.editText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.confirmPassword.editText.transformationMethod = PasswordTransformationMethod()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
