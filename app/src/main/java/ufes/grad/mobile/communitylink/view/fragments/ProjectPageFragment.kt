package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProjectPageBinding
import ufes.grad.mobile.communitylink.view.popups.MakeDonationPopup

class ProjectPageFragment : Fragment(R.layout.fragment_project_page), View.OnClickListener {

    private var _binding: FragmentProjectPageBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectPageBinding.inflate(inflater, container, false)

        // TODO("Get project model")

        binding.actionsButton.setOnClickListener(this)
        binding.membersButton.setOnClickListener(this)
        binding.donationsButton.setOnClickListener(this)
        binding.donationButton.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.membersButton.id -> {
                findNavController().navigate(R.id.projectMembersFragment)
                // TODO("Add arguments to navigation")
            }
            binding.actionsButton.id -> {
                findNavController().navigate(R.id.projectActionsListFragment)
                // TODO("Add arguments to navigation")
            }
            binding.donationsButton.id -> {
                findNavController().navigate(R.id.donationListFragment)
                // TODO("Add arguments to navigation")
            }
            binding.donationButton.id -> {
                val popup = MakeDonationPopup()
                popup.onConfirm =
                    {
                        // TODO("Create donation")
                    }
                popup.show(childFragmentManager, "")
            }
        }
    }
}
