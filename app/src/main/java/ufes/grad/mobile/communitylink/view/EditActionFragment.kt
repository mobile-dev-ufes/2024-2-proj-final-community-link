package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentEditActionBinding
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup.UserPopupType

class EditActionFragment : Fragment(R.layout.fragment_edit_action), View.OnClickListener {

    private var _binding: FragmentEditActionBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditActionBinding.inflate(inflater, container, false)
        //        TODO("Get action model")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                TODO("onClick 'Atualizar dados'")
            }
            binding.primaryRepresentative.id -> {
                val popup = UserDataPopup(null, UserPopupType.USER_DATA_UPDATE)
                //                TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
            binding.secondaryRepresentative.id -> {
                val popup = UserDataPopup(null, UserPopupType.USER_DATA_UPDATE)
                //                TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
        }
    }
}
