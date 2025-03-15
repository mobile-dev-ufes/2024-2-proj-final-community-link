package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentEditProjectBinding
import ufes.grad.mobile.communitylink.view.popups.BasePopup

class EditProjectFragment : Fragment(R.layout.fragment_edit_project), View.OnClickListener {

    private var _binding: FragmentEditProjectBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditProjectBinding.inflate(inflater, container, false)

        // TODO("Get project model")

        val discontinued = false

        if (discontinued) binding.deactivateButton.text = getString(R.string.continue_project)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_project)
                popup.onConfirm =
                    {
                        // TODO("Delete project")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.requestButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_project_revision)
                popup.onConfirm =
                    {
                        // TODO("Send project to revision")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.deactivateButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_discontinue_project)
                popup.onConfirm =
                    {
                        // TODO("Toggle discontinue project")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.actionsButton.id -> {
                TODO("Add navigation args")
                findNavController().navigate(R.id.projectActionsListFragment)
            }
            binding.membersButton.id -> {
                TODO("Add navigation args")
                findNavController().navigate(R.id.projectMembersFragment)
            }
            binding.donationsButton.id -> {
                TODO("Add navigation args")
                findNavController().navigate(R.id.donationListFragment)
            }
        }
    }
}
