package ufes.grad.mobile.communitylink.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProfileBinding
import ufes.grad.mobile.communitylink.utils.Utilities

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.projectsButton.setOnClickListener(this)
        binding.actionsButton.setOnClickListener(this)
        binding.eventsButton.setOnClickListener(this)
        binding.pendingButton.setOnClickListener(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.projectsButton.id -> {
                Utilities.loadFragment(requireActivity(), MyProjectsFragment())
            }

            binding.actionsButton.id -> {
                Utilities.loadFragment(requireActivity(), MyActionsFragment())
            }

            binding.eventsButton.id -> {
                Utilities.loadFragment(requireActivity(), PastActionsFragment())
            }

            binding.pendingButton.id -> {
                Utilities.loadFragment(requireActivity(), PendingActionsFragment())
            }
        }
    }
}