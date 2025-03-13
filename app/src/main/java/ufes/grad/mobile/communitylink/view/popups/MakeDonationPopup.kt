package ufes.grad.mobile.communitylink.view.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.PopupMakeDonationBinding

class MakeDonationPopup :
    BasePopup(PopupType.TWO_BUTTON, R.layout.popup_make_donation), View.OnClickListener {

    private var _binding: PopupMakeDonationBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupMakeDonationBinding.inflate(inflater, container, false)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        super.onClick(v)
    }
}
