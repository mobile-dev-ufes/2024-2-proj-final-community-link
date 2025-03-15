package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentManageGoalBinding
import ufes.grad.mobile.communitylink.view.popups.BasePopup

class ManageGoalFragment : Fragment(R.layout.fragment_manage_goal), View.OnClickListener {

    private var _binding: FragmentManageGoalBinding? = null
    private val binding
        get() = _binding!!

    private var exists: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentManageGoalBinding.inflate(inflater, container, false)

        // TODO("Add args")
        // nesse caso, não precisa ser um booleano
        // mandar um id já é o suficiente, aí se ele for nulo, quer dizer que devemos criar

        if (exists) {
            binding.confirmButton.text = getString(R.string.create_new_goal)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                if (exists) {
                    // TODO("Update goal")
                } else {
                    // TODO("Create goal")
                }
            }
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_goal)
                popup.onConfirm =
                    {
                        // TODO("Delete goal")
                    }
                popup.show(childFragmentManager, "")
            }
        }
    }
}
