package ufes.grad.mobile.communitylink.view

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

    /** Determines if the goal exists or is going to be created. */
    private var exists: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exists = requireArguments().getBoolean("EXISTS")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentManageGoalBinding.inflate(inflater, container, false)

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
                    TODO("Atualizar meta")
                } else {
                    TODO("Criar meta")
                }
            }
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_goal)
                // TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
        }
    }
}
