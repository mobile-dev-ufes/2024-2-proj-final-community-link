package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentMyActionsBinding

class MyActionsFragment : Fragment(R.layout.fragment_my_actions) {

    private var _binding: FragmentMyActionsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMyActionsBinding.inflate(inflater, container, false)
        // TODO("Add recycle view")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
