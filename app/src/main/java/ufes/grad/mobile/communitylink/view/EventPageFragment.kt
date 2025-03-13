package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentEventPageBinding
import ufes.grad.mobile.communitylink.view.popups.PostPopup

class EventPageFragment : Fragment(R.layout.fragment_event_page), View.OnClickListener {

    private var _binding: FragmentEventPageBinding? = null
    private val binding
        get() = _binding!!

    private var edit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edit = requireArguments().getBoolean("EDIT")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEventPageBinding.inflate(inflater, container, false)

        if (edit) {
            binding.pendingButton.setOnClickListener(this)
            binding.manageButton.setOnClickListener(this)
            binding.editButton.setOnClickListener(this)
            binding.dateDropdown.setOnClickListener(this)
            binding.createButton.setOnClickListener(this)
            binding.volunteersButton.visibility = View.GONE
        } else {
            binding.volunteersButton.setOnClickListener(this)
            binding.pendingButton.visibility = View.GONE
            binding.manageButton.visibility = View.GONE
            binding.editButton.visibility = View.GONE
            binding.dateDropdown.visibility = View.GONE
            binding.createButton.visibility = View.GONE
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.volunteersButton.id -> {
                TODO("Add navigation")
            }
            binding.createButton.id -> {
                val popup = PostPopup(PostPopup.PostMode.NEW)
                //                TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
            binding.pendingButton.id -> {
                TODO("Add navigation")
            }
            binding.editButton.id -> {
                TODO("Add navigation")
            }
            binding.manageButton.id -> {
                TODO("Add navigation")
            }
        }
    }
}
