package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentExploreBinding
import ufes.grad.mobile.communitylink.view.popups.PostPopup
import ufes.grad.mobile.communitylink.view.popups.PostPopup.PostMode

class ExploreFragment : Fragment(R.layout.fragment_explore), View.OnClickListener {

    private var _binding: FragmentExploreBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        // TODO("Add recycle view")
        binding.test.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.test.id -> {
                val popup = PostPopup(PostMode.EDIT)
                // TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
        }
    }
}
