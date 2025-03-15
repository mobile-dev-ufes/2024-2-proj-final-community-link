package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.databinding.FragmentEventPageBinding
import ufes.grad.mobile.communitylink.view.adapter.PostAdapter
import ufes.grad.mobile.communitylink.view.popups.PostPopup

class EventPageFragment : Fragment(R.layout.fragment_event_page), View.OnClickListener {

    private var _binding: FragmentEventPageBinding? = null
    private val binding
        get() = _binding!!

    private val args: EventPageFragmentArgs by navArgs()

    private val adapter: PostAdapter = PostAdapter()

    init {
        // TODO("Get real data")
        adapter.updateList(StaticData.posts)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEventPageBinding.inflate(inflater, container, false)

        // TODO("Add navigation args")
        val edit = false

        adapter.edit = edit
        adapter.onItemClickListener = { position ->
            val popup = PostPopup(if (edit) PostPopup.PostMode.EDIT else PostPopup.PostMode.VIEW)
            if (edit)
                popup.onConfirm =
                    {
                        // TODO("Update post")
                    }
            popup.show(childFragmentManager, "")
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        if (args.edit) {
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
                //                TODO("Add navigation args")
                findNavController().navigate(R.id.eventVolunteerSlotsFragment)
            }
            binding.createButton.id -> {
                val popup = PostPopup(PostPopup.PostMode.NEW)
                popup.onConfirm =
                    {
                        // TODO("Create new post")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.pendingButton.id -> {
                //                TODO("Add navigation args")
                findNavController().navigate(R.id.pendingSlotsFragment)
            }
            binding.editButton.id -> {
                //                TODO("Add navigation args")
                findNavController().navigate(R.id.editActionFragment)
            }
            binding.manageButton.id -> {
                //                TODO("Add navigation args")
                findNavController().navigate(R.id.eventVolunteerSlotsFragment)
            }
        }
    }
}
