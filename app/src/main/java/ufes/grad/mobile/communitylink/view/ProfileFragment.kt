package ufes.grad.mobile.communitylink.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.Calendar
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProfileBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.popups.BasePopup
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup.UserPopupType
import ufes.grad.mobile.communitylink.viewmodel.ProfileVM

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var profileVM: ProfileVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.projectsButton.setOnClickListener(this)
        binding.actionsButton.setOnClickListener(this)
        binding.eventsButton.setOnClickListener(this)
        binding.pendingButton.setOnClickListener(this)
        binding.logoutButton.setOnClickListener(this)
        binding.confirmAlterations.setOnClickListener(this)
        binding.excludeAccount.setOnClickListener(this)
        profileVM = ViewModelProvider(this)[ProfileVM::class.java]
        setObserver()
        binding.cpf.editText.inputType = InputType.TYPE_CLASS_NUMBER
        binding.phone.editText.inputType = InputType.TYPE_CLASS_PHONE

        binding.buttonDate.setOnClickListener {
            val listener =
                object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(dp: DatePicker?, year: Int, month: Int, day: Int) {
                        binding.buttonDate.text = buildString {
                            append(day)
                            append("/")
                            append(month + 1)
                            append("/")
                            append(year)
                        }
                        binding.buttonDate.setTextColor(getColor(requireContext(), R.color.black))
                    }
                }
            val cal = Calendar.getInstance()
            DatePickerDialog(
                    requireContext(),
                    listener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                )
                .show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileVM.getUserData()
    }

    private fun setObserver() {
        profileVM
            .userData()
            .observe(
                viewLifecycleOwner,
                Observer { it ->
                    binding.name.editText.setText(it.get("name"))
                    binding.email.editText.setText(it.get("email"))
                    binding.email.editText.isEnabled = false
                    binding.email.editText.keyListener = null
                    binding.email.editText.setTextColor(requireContext().getColor(R.color.gray_600))
                    binding.cpf.editText.setText(it.get("cpf"))
                    binding.buttonDate.text = (it.get("dob"))
                    binding.buttonDate.setTextColor(requireContext().getColor(R.color.black))
                    binding.addressForm.editText.setText(it.get("address"))
                    binding.phone.editText.setText(it.get("phone"))
                    binding.sex.editText.setText(it.get("sex"))
                }
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.projectsButton.id -> {
                findNavController().navigate(R.id.myProjectsFragment)
            }
            binding.actionsButton.id -> {
                findNavController().navigate(R.id.myActionsFragment)
            }
            binding.eventsButton.id -> {
                findNavController().navigate(R.id.eventsAndDonationsFragment)
            }
            binding.pendingButton.id -> {
                findNavController().navigate(R.id.pendingActionsFragment)
            }
            binding.logoutButton.id -> {
                profileVM.logout()
                startActivity(Intent(context, LoginActivity::class.java))
                requireActivity().finish()
            }
            binding.excludeAccount.id -> {
                requireActivity().finish()
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_user)
                // TODO("Make popup functional")
                popup.onConfirm = { startActivity(Intent(context, LoginActivity::class.java)) }
                popup.onCancel = { Utilities.notify(context, "Não deletou") }
                popup.show(childFragmentManager, "")
            }
            binding.confirmAlterations.id -> {
                val popup = UserDataPopup(null, UserPopupType.USER_DATA_UPDATE)
                // TODO("Make popup functional")
                popup.show(childFragmentManager, "")
                profileVM.changeUserData(
                    hashMapOf(
                        "name" to binding.name.editText.text.toString(),
                        "cpf" to binding.cpf.editText.text.toString(),
                        "sex" to binding.sex.editText.text.toString(),
                        "dob" to binding.buttonDate.text.toString(),
                        "address" to binding.addressForm.editText.text.toString(),
                        "phone" to binding.phone.editText.text.toString()
                    )
                )
            }
        }
    }
}
