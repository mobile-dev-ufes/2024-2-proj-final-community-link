package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.FragmentDonationPageBinding
import ufes.grad.mobile.communitylink.view.adapter.DonationPostAdapter
import ufes.grad.mobile.communitylink.view.popups.MakeDonationPopup
import ufes.grad.mobile.communitylink.view.popups.PostPopup

class DonationPageFragment : Fragment(R.layout.fragment_donation_page), View.OnClickListener {

    private var _binding: FragmentDonationPageBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: DonationPostAdapter = DonationPostAdapter()

    init {
        // TODO("Get real data")
        adapter.updateList(StaticData.posts + StaticData.goalPosts)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDonationPageBinding.inflate(inflater, container, false)

        // TODO("Pass arguments")
        // can the user edit this page? (are they one of the representatives?)
        val edit = false

        adapter.edit = edit
        adapter.onItemClickListener = { position ->
            val item = adapter.list[position]
            when (item) {
                is PostModel -> {
                    val popup =
                        PostPopup(if (edit) PostPopup.PostMode.EDIT else PostPopup.PostMode.VIEW)
                    if (edit)
                        popup.onConfirm =
                            {
                                // TODO("Update post")
                            }
                    popup.show(childFragmentManager, "")
                }
            }
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        // TODO("Get project model")

        // TODO("Set project tag")
        // binding.projectTag.setValues(project.currentData.name, project.currentData.logo)

        if (edit) {
            binding.createPostButton.setOnClickListener(this)
            binding.createGoalButton.setOnClickListener(this)
            binding.manageButton.setOnClickListener(this)
            binding.goalsButton.setOnClickListener(this)
            binding.editButton.setOnClickListener(this)
            binding.donationButton.visibility = View.GONE
        } else {
            binding.createPostButton.visibility = View.GONE
            binding.createGoalButton.visibility = View.GONE
            binding.manageButton.visibility = View.GONE
            binding.editButton.visibility = View.GONE
            binding.donationButton.setOnClickListener(this)
            binding.goalsButton.setOnClickListener(this)
        }

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
                popup.onConfirm =
                    {
                        // TODO("Make donation")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.manageButton.id -> {
                // TODO("Add navigation args")
                findNavController().navigate(R.id.donationListFragment)
            }
            binding.goalsButton.id -> {
                // TODO("Add navigation args")
                findNavController().navigate(R.id.donationListFragment)
            }
            binding.editButton.id -> {
                // TODO("Add navigation args")
                findNavController().navigate(R.id.editActionFragment)
            }
            binding.createPostButton.id -> {
                val popup = PostPopup(PostPopup.PostMode.NEW)
                popup.onConfirm =
                    {
                        // TODO("Create post")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.createGoalButton.id -> {
                // TODO("Add navigation args")
                findNavController().navigate(R.id.manageGoalFragment)
            }
            binding.projectTag.id -> {
                // TODO("Add navigation args")
                findNavController().navigate(R.id.projectPageFragment)
            }
        }
    }
}
