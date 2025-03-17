package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.data.model.GoalPostModel
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.FragmentDonationPageBinding
import ufes.grad.mobile.communitylink.view.adapter.DonationPostAdapter
import ufes.grad.mobile.communitylink.view.popups.MakeDonationPopup
import ufes.grad.mobile.communitylink.view.popups.PostPopup

class DonationPageFragment : Fragment(R.layout.fragment_donation_page), View.OnClickListener {

    private var _binding: FragmentDonationPageBinding? = null
    private val binding
        get() = _binding!!

    private val args: EventPageFragmentArgs by navArgs()

    private lateinit var donation: ActionDonationModel

    private val adapter: DonationPostAdapter = DonationPostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO("Get data from BD")
        donation = StaticData.donationActions[0]
        adapter.updateList(StaticData.posts + StaticData.goalPosts)

        adapter.edit = args.edit
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDonationPageBinding.inflate(inflater, container, false)

        setupAdapter()
        setupLayout()

        return binding.root
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val item = adapter.list[position]
            when (item) {
                is PostModel -> {
                    val popup =
                        PostPopup(
                            item,
                            donation,
                            if (args.edit) PostPopup.PostMode.EDIT else PostPopup.PostMode.VIEW
                        )
                    if (args.edit)
                        popup.onConfirm = {
                            val post = popup.createPost()
                            // TODO("Update post")
                        }
                    popup.show(childFragmentManager, "")
                }
                is GoalPostModel -> {
                    val action =
                        DonationPageFragmentDirections
                            .actionDonationPageFragmentToManageGoalFragment()
                    action.id = adapter.list[position].id
                    action.isDonation = false
                    findNavController().navigate(action)
                }
            }
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    fun setupLayout() {
        binding.nameText.text = donation.name
        binding.descriptionText.text = donation.description
        binding.datesText.text = getString(R.string.from_to, donation.initDate, donation.finishDate)

        // TODO("Set project tag")
        // binding.projectTag.setValues(project.currentData.name, project.currentData.logo)

        binding.donationsButton.setOnClickListener(this)

        if (args.edit) {
            binding.createPostButton.setOnClickListener(this)
            binding.createGoalButton.setOnClickListener(this)
            binding.goalsButton.setOnClickListener(this)
            binding.editButton.setOnClickListener(this)
            binding.donationsButton.text.text = getString(R.string.manage_donations)
            binding.donationButton.visibility = View.GONE
        } else {
            binding.createPostButton.visibility = View.GONE
            binding.createGoalButton.visibility = View.GONE
            binding.goalsButton.setOnClickListener(this)
            binding.editButton.visibility = View.GONE
            binding.donationsButton.text.text = getString(R.string.view_donations)
            binding.donationButton.setOnClickListener(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.donationButton.id -> {
                val popup = MakeDonationPopup()
                popup.onConfirmDismiss = false
                popup.onConfirm = {
                    val donation: DonationModel? = popup.makeDonation()
                    if (donation != null) {
                        // TODO("Register donation")
                        popup.dismiss()
                    }
                }
                popup.show(childFragmentManager, "")
            }
            binding.donationsButton.id -> {
                val action =
                    DonationPageFragmentDirections
                        .actionDonationPageFragmentToDonationListFragment()
                action.id = donation.project.id
                action.isProject = false
                action.edit = args.edit
                findNavController().navigate(action)
            }
            binding.goalsButton.id -> {
                val action =
                    DonationPageFragmentDirections.actionDonationPageFragmentToGoalListFragment()
                action.id = donation.project.id
                findNavController().navigate(action)
            }
            binding.editButton.id -> {
                val action =
                    DonationPageFragmentDirections.actionDonationPageFragmentToEditProjectFragment()
                action.id = donation.project.id
                findNavController().navigate(action)
            }
            binding.createPostButton.id -> {
                val popup = PostPopup(null, donation, PostPopup.PostMode.NEW)
                popup.onConfirm = {
                    val post: PostModel? = popup.createPost()
                    if (post != null) {
                        // TODO("Create new post")
                        popup.dismiss()
                    }
                }
                popup.show(childFragmentManager, "")
            }
            binding.createGoalButton.id -> {
                val action =
                    DonationPageFragmentDirections.actionDonationPageFragmentToManageGoalFragment()
                action.id = donation.project.id
                action.isDonation = true
                findNavController().navigate(action)
            }
            binding.projectTag.id -> {
                val action =
                    DonationPageFragmentDirections.actionDonationPageFragmentToProjectPageFragment()
                action.id = donation.project.id
                findNavController().navigate(action)
            }
        }
    }
}
