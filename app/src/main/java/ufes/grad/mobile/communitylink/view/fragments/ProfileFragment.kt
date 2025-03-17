package ufes.grad.mobile.communitylink.view.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.Calendar
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.databinding.FragmentProfileBinding
import ufes.grad.mobile.communitylink.utils.Utilities.Companion.setLocale
import ufes.grad.mobile.communitylink.view.LoginActivity
import ufes.grad.mobile.communitylink.view.popups.BasePopup
import ufes.grad.mobile.communitylink.viewmodel.ProfileVM

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var profileVM: ProfileVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileVM = ViewModelProvider(this)[ProfileVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setObserver()
        setupLayout()

        return binding.root
    }

    fun setupLayout() {
        binding.buttonDate.setOnClickListener(this)
        binding.projectsButton.setOnClickListener(this)
        binding.actionsButton.setOnClickListener(this)
        binding.eventsButton.setOnClickListener(this)
        binding.pendingButton.setOnClickListener(this)
        binding.logoutButton.setOnClickListener(this)
        binding.confirmAlterations.setOnClickListener(this)
        binding.excludeAccount.setOnClickListener(this)
        binding.changeLanguage.setOnClickListener(this)
        binding.cpf.editText.inputType = InputType.TYPE_CLASS_NUMBER
        binding.phone.editText.inputType = InputType.TYPE_CLASS_PHONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileVM.getUserData()
    }

    private fun setObserver() {
        profileVM
            .user()
            .observe(
                viewLifecycleOwner,
                Observer { it ->
                    binding.name.editText.setText(it!!.name)
                    binding.email.editText.setText(it.email)
                    binding.email.editText.isEnabled = false
                    binding.email.editText.keyListener = null
                    binding.email.editText.setTextColor(requireContext().getColor(R.color.gray_600))
                    binding.cpf.editText.setText(it.cpf)
                    binding.buttonDate.text = (it.dob)
                    binding.buttonDate.setTextColor(requireContext().getColor(R.color.black))
                    binding.addressForm.editText.setText(it.address)
                    binding.phone.editText.setText(it.phone)
                    binding.sex.editText.setText(it.sex)
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
                val actions =
                    ProfileFragmentDirections.actionProfileFragmentToMyProjectsFragment(
                        profileVM.user().value?.id!!
                    )
                findNavController().navigate(actions)
            }
            binding.actionsButton.id -> {
                val actions = ProfileFragmentDirections.actionProfileFragmentToMyActionsFragment()
                findNavController().navigate(actions)
            }
            binding.eventsButton.id -> {
                val actions =
                    ProfileFragmentDirections.actionProfileFragmentToEventsAndDonationsFragment()
                findNavController().navigate(actions)
            }
            binding.pendingButton.id -> {
                val actions =
                    ProfileFragmentDirections.actionProfileFragmentToPendingActionsFragment()
                findNavController().navigate(actions)
            }
            binding.logoutButton.id -> {
                profileVM.logout()
                startActivity(Intent(context, LoginActivity::class.java))
                requireActivity().finish()
            }
            binding.excludeAccount.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_user)
                popup.onConfirm = {
                    // TODO("Delete user from DB")
                    startActivity(Intent(context, LoginActivity::class.java))
                }
                popup.show(childFragmentManager, "")
                requireActivity().finish()
            }
            binding.confirmAlterations.id -> {
                profileVM.changeUserData(
                    UserModel(
                        name = binding.name.editText.text.toString(),
                        cpf = binding.cpf.editText.text.toString(),
                        sex = binding.sex.editText.text.toString(),
                        dob = binding.buttonDate.text.toString(),
                        address = binding.addressForm.editText.text.toString(),
                        phone = binding.phone.editText.text.toString(),
                        email = binding.email.editText.text.toString(),
                    )
                )
            }
            binding.buttonDate.id -> {
                val cal = Calendar.getInstance()
                val datePicker =
                    DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            {
                                binding.buttonDate.text = "$day/${month + 1}/$year"
                                binding.buttonDate.setTextColor(
                                    getColor(requireContext(), R.color.black)
                                )
                            }
                        },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    )
                datePicker.datePicker.maxDate = cal.timeInMillis
                datePicker.show()
            }
            binding.changeLanguage.id -> {
                toggleLanguage()
            }
        }
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
