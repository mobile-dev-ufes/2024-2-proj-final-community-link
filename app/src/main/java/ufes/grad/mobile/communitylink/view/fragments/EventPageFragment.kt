package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.FragmentEventPageBinding
import ufes.grad.mobile.communitylink.view.adapter.PostAdapter
import ufes.grad.mobile.communitylink.view.popups.PostPopup

class EventPageFragment : Fragment(R.layout.fragment_event_page), View.OnClickListener {

    private var _binding: FragmentEventPageBinding? = null
    private val binding
        get() = _binding!!

    private val args: EventPageFragmentArgs by navArgs()

    private lateinit var event: ActionEventModel

    private val adapter: PostAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO("Get event from DB")
        event = StaticData.eventActions[1]

        // TODO("Get posts from event")
        adapter.updateList(StaticData.posts)
        adapter.edit = args.edit
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEventPageBinding.inflate(inflater, container, false)

        setupAdapter()
        setupData()

        return binding.root
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val item: PostModel? = adapter.list[position] as PostModel
            val popup =
                PostPopup(
                    item,
                    event,
                    if (args.edit) PostPopup.PostMode.EDIT else PostPopup.PostMode.VIEW
                )
            popup.onConfirm = {
                val post: PostModel? = popup.createPost()
                if (post != null) {
                    // TODO("Create or update post")
                    popup.dismiss()
                }
            }
            popup.show(childFragmentManager, "")
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    fun setupData() {
        binding.nameText.text = event.name
        binding.descriptionText.text = event.description
        binding.datesText.text = getString(R.string.from_to, event.initDate, event.finishDate)
        binding.placesText.text = getString(R.string.places_list, event.places.joinToString("\n"))

        // TODO("Set project tag")
        // binding.projectTag.setValues(project.currentData.name, project.currentData.logo)

        if (args.edit) {
            binding.pendingButton.setOnClickListener(this)
            binding.manageButton.setOnClickListener(this)
            binding.editButton.setOnClickListener(this)
            binding.createButton.setOnClickListener(this)
            binding.volunteersButton.visibility = View.GONE
        } else {
            binding.volunteersButton.setOnClickListener(this)
            binding.pendingButton.visibility = View.GONE
            binding.manageButton.visibility = View.GONE
            binding.editButton.visibility = View.GONE
            binding.createButton.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.volunteersButton.id -> {
                val action =
                    EventPageFragmentDirections
                        .actionEventPageFragmentToEventVolunteerSlotsFragment()
                action.eventId = event.project.id
                action.edit = false
                findNavController().navigate(action)
            }
            binding.createButton.id -> {
                val popup = PostPopup(null, event, PostPopup.PostMode.NEW)
                popup.onConfirm = {
                    val post: PostModel? = popup.createPost()
                    if (post != null) {
                        // TODO("Create new post")
                        popup.dismiss()
                    }
                }
                popup.show(childFragmentManager, "")
            }
            binding.pendingButton.id -> {
                val action =
                    EventPageFragmentDirections.actionEventPageFragmentToPendingSlotsFragment()
                action.id = event.project.id
                findNavController().navigate(action)
            }
            binding.editButton.id -> {
                val action =
                    EventPageFragmentDirections.actionEventPageFragmentToEditActionFragment()
                action.id = event.project.id
                action.isEvent = true
                findNavController().navigate(action)
            }
            binding.manageButton.id -> {
                val action =
                    EventPageFragmentDirections
                        .actionEventPageFragmentToEventVolunteerSlotsFragment()
                action.eventId = event.project.id
                action.edit = true
                findNavController().navigate(action)
            }
            binding.projectTag.id -> {
                // TODO("Check if user is responsible for the project")
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                val responsible = false

                if (responsible) {
                    val action =
                        EventPageFragmentDirections.actionEventPageFragmentToEditProjectFragment()
                    action.id = event.project.id
                    findNavController().navigate(action)
                } else {
                    val action =
                        EventPageFragmentDirections.actionEventPageFragmentToProjectPageFragment()
                    action.id = event.project.id
                    findNavController().navigate(action)
                }
            }
        }
    }
}
