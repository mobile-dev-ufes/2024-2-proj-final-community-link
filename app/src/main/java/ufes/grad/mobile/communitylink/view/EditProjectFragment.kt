package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentEditProjectBinding
import ufes.grad.mobile.communitylink.databinding.FragmentEventPageBinding
import ufes.grad.mobile.communitylink.utils.Utilities

class EditProjectFragment : Fragment(R.layout.fragment_edit_project) {

    private var _binding: FragmentEditProjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}