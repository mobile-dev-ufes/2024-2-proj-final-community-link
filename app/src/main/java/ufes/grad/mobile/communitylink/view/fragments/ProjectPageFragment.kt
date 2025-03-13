package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProjectPageBinding

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
                // TODO("Add navigation")
            }
            binding.actionsButton.id -> {
                // TODO("Add navigation")
            }
            binding.donationsButton.id -> {
                // TODO("Add navigation")
            }
            binding.donationButton.id -> {
                // TODO("Add navigation")
            }
        }
    }
}
