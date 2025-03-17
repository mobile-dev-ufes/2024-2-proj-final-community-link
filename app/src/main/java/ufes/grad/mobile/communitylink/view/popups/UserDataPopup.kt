package ufes.grad.mobile.communitylink.view.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.PopupUserDataBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.viewmodel.UserDataVM

class UserDataPopup(
    private val userId: String,
    private val isMember: Boolean = false,
    private val mode: UserPopupType
) : DialogFragment(R.layout.popup_user_data), View.OnClickListener {

    /** Determines the layout of the popup. */
    enum class UserPopupType {
        ADD_USER_AS_MEMBER,
        MANAGE_MEMBER,
        VIEW_DATA,
    }

    private var _binding: PopupUserDataBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var userVM: UserDataVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userVM = ViewModelProvider(this)[UserDataVM::class.java]
        if (isMember) userVM.getMemberData(userId) else userVM.getUserData(userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupUserDataBinding.inflate(inflater, container, false)

        setObserver()
        setupLayout()

        return root
    }

    fun setupLayout() {
        when (mode) {
            UserPopupType.ADD_USER_AS_MEMBER -> {
                binding.titleText.text = getString(R.string.add_member_question)
                binding.closeButton.visibility = View.GONE
            }
            UserPopupType.MANAGE_MEMBER -> {
                binding.titleText.text = getString(R.string.manage_member)
                setupDropdown()
                binding.closeButton.visibility = View.GONE
            }
            UserPopupType.VIEW_DATA -> {
                binding.titleText.text = getString(R.string.member_data)
                binding.cancelButton.visibility = View.GONE
                binding.confirmButton.visibility = View.GONE
            }
        }
    }

    fun setObserver() {
        if (isMember) {
            userVM
                .getMember()
                .observe(
                    viewLifecycleOwner,
                    Observer {
                        binding.nameText.text = it.user.name
                        binding.emailText.text = it.user.email
                        binding.cpfText.text = it.user.cpf
                        binding.phoneText.text = it.user.phone
                        binding.sexText.text = it.user.sex
                        binding.dateText.text = it.user.dob
                        binding.addressText.text = it.user.address
                    }
                )
        } else
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
        val status = listOf(getString(R.string.active), getString(R.string.responsible_singular))
        binding.statusDropdown.adapter = SpinnerAdapter(requireContext(), status)
        binding.statusDropdown.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    userVM.updateMemberStatus(position == 1)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var onConfirm: () -> Any? = {}

    override fun onClick(v: View) {
        when (v.id) {
            binding.closeButton.id,
            binding.cancelButton.id -> {
                dismiss()
            }
            binding.confirmButton.id -> {
                onConfirm()
                dismiss()
            }
        }
    }
}
