package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentForgotPasswordBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.ForgotPasswordVM

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password), OnClickListener {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var forgotPasswordVM: ForgotPasswordVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        forgotPasswordVM = ViewModelProvider(this)[ForgotPasswordVM::class.java]
        binding.emailButton.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.email_button -> {
                if (binding.email.editText.text.toString().isBlank()) {
                    binding.email.editText.setHintTextColor(requireContext().getColor(R.color.red))
                    Utilities.notify(requireContext(), getString(R.string.preencha_todos_os_campos))
                    return
                }
                forgotPasswordVM.sendEmailCode(binding.email.editText.text.toString())
            }
        }
    }
}
