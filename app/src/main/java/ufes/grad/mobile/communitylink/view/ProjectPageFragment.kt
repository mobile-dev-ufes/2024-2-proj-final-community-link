package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProjectPageBinding
import ufes.grad.mobile.communitylink.utils.Utilities

class ProjectPageFragment : Fragment(R.layout.fragment_project_page), View.OnClickListener {

    private var _binding: FragmentProjectPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectPageBinding.inflate(inflater, container, false)
        binding.actionsButton.setOnClickListener(this)
        binding.membersButton.setOnClickListener(this)
        binding.donationsButton.setOnClickListener(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.membersButton.id -> {
                Utilities.loadFragment(requireActivity(), ProjectMembersFragment())
            }

            binding.actionsButton.id -> {
                Utilities.loadFragment(requireActivity(), ProjectActionsListFragment())
            }

            binding.donationsButton.id -> {
                Utilities.loadFragment(requireActivity(), ProjectDonationListFragment())
            }
        }
    }
}