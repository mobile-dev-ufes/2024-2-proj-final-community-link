package ufes.grad.mobile.communitylink.view.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.PopupUserDataBinding
import ufes.grad.mobile.communitylink.model.UserModel

class UserDataPopup(private val user: UserModel?, private val mode: UserPopupType) :
    BasePopup(PopupType.TWO_BUTTON, R.layout.popup_user_data) {

    /** Determines the layout of the popup. */
    enum class UserPopupType {
        ADD_USER_AS_MEMBER,
        ADD_MEMBER_AS_REPRESENTATIVE,
        USER_DATA_UPDATE,
        MANAGE_MEMBER,
        VIEW_DATA,
        ACCEPT_REQUEST
    }

    private var _binding: PopupUserDataBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupUserDataBinding.inflate(inflater, container, false)

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
            }
            UserPopupType.VIEW_DATA -> {
                binding.titleText.text = getString(R.string.member_data)
                type = PopupType.ONE_BUTTON
            }
            UserPopupType.ACCEPT_REQUEST -> {
                binding.titleText.text = getString(R.string.accept_request_question)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
