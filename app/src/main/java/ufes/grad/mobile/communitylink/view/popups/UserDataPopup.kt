package ufes.grad.mobile.communitylink.view.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.PopupUserDataBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.viewmodel.UserDataVM

class UserDataPopup(private val userId: String, private val mode: UserPopupType) :
    BasePopup(PopupType.TWO_BUTTON, R.layout.popup_user_data) {

    /** Determines the layout of the popup. */
    enum class UserPopupType {
        ADD_USER_AS_MEMBER,
        ADD_MEMBER_AS_REPRESENTATIVE,
        USER_DATA_UPDATE,
        MANAGE_MEMBER,
        VIEW_DATA,
        SLOT_REQUEST
    }

    private var _binding: PopupUserDataBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var userVM: UserDataVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userVM = ViewModelProvider(this)[UserDataVM::class.java]
        userVM.getUserData(userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupUserDataBinding.inflate(inflater, container, false)

        setObserver()

        when (mode) {
            UserPopupType.ADD_USER_AS_MEMBER -> {
                binding.titleText.text = getString(R.string.add_member_question)
            }
            UserPopupType.ADD_MEMBER_AS_REPRESENTATIVE -> {
                binding.titleText.text = getString(R.string.add_representative_question)
            }
            UserPopupType.USER_DATA_UPDATE -> {
                binding.titleText.text = getString(R.string.confirm_changes)
            }
            UserPopupType.MANAGE_MEMBER -> {
                binding.titleText.text = getString(R.string.manage_member)
                setupDropdown()
            }
            UserPopupType.VIEW_DATA -> {
                binding.titleText.text = getString(R.string.member_data)
                type = PopupType.ONE_BUTTON
            }
            UserPopupType.SLOT_REQUEST -> {
                binding.titleText.text = getString(R.string.accept_request_question)
            }
        }

        return root
    }

    fun setObserver() {
        userVM
            .getUser()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.nameText.text = it.name
                    binding.emailText.text = it.email
                    binding.cpfText.text = it.cpf
                    binding.phoneText.text = it.phone
                    binding.sexText.text = it.sex
                    binding.dateText.text = it.dob
                    binding.addressText.text = it.address
                }
            )
    }

    fun setupDropdown() {
        val status =
            listOf(
                getString(R.string.active),
                getString(R.string.responsible_singular),
                getString(R.string.former)
            )
        binding.statusDropdown.adapter = SpinnerAdapter(requireContext(), status)
        binding.statusDropdown.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // TODO("Update user status")
                    // status[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
