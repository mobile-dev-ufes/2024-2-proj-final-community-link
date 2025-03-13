package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentDonationPageBinding
import ufes.grad.mobile.communitylink.view.popups.MakeDonationPopup
import ufes.grad.mobile.communitylink.view.popups.PostPopup

class DonationPageFragment : Fragment(R.layout.fragment_donation_page), View.OnClickListener {

    private var _binding: FragmentDonationPageBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDonationPageBinding.inflate(inflater, container, false)
        //        TODO("Get project model")
        binding.donationButton.setOnClickListener(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.donationButton.id -> {
                val popup = MakeDonationPopup()
                //                TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
            binding.createPostButton.id -> {
                val popup = PostPopup(PostPopup.PostMode.NEW)
                //                TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
        }
        //        TODO("Add recycle view of posts")
    }
}
