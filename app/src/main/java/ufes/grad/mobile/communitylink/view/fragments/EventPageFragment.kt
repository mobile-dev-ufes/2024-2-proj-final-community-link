package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.FragmentEventPageBinding
import ufes.grad.mobile.communitylink.view.adapter.PostAdapter
import ufes.grad.mobile.communitylink.view.popups.PostPopup
import ufes.grad.mobile.communitylink.viewmodel.EventPageVM

class EventPageFragment : Fragment(R.layout.fragment_event_page), View.OnClickListener {

    private var _binding: FragmentEventPageBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var eventVM: EventPageVM

    private val args: EventPageFragmentArgs by navArgs()

    private val adapter: PostAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventVM = ViewModelProvider(this)[EventPageVM::class.java]
        eventVM.getEventById(args.id)
        adapter.edit = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEventPageBinding.inflate(inflater, container, false)

        setObserver()
        setupAdapter()

        return binding.root
    }

    fun setupAdapter() {
//        adapter.onItemClickListener = { position ->
//            val item: PostModel? = adapter.list[position] as PostModel
//            val popup =
//                PostPopup(
//                    item,
//                    eventVM.getEvent().value!!,
//                    if (args.edit) PostPopup.PostMode.EDIT else PostPopup.PostMode.VIEW
//                )
//            popup.onConfirm = {
//                val post: PostModel? = popup.createPost()
//                if (post != null) {
//
//                    popup.dismiss()
//                }
//            }
//            popup.show(childFragmentManager, "")
//        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    fun setObserver() {

        eventVM
            .getEvent()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.nameText.text = it.name
                    binding.descriptionText.text = it.description
                    binding.datesText.text = getString(R.string.from_to, it.initDate, it.finishDate)
                    binding.placesText.text =
                        getString(R.string.places_list, it.places.joinToString("\n"))

                    binding.projectTag.setValues(it.project.currentData.name)
                    if (true) {
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
                    // eventVM.fetchPosts(it.posts)
                    adapter.updateList(StaticData.posts)
                }
            )
        eventVM.getPosts().observe(
            viewLifecycleOwner,
            Observer {
                adapter.updateList(it)
            }
        )
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
                action.eventId = eventVM.getEvent().value!!.project.id
                action.edit = false
                findNavController().navigate(action)
            }
            binding.createButton.id -> {
                val popup = PostPopup(null, eventVM.getEvent().value!!, PostPopup.PostMode.NEW)
                popup.onConfirm = {
                    val post: PostModel? = popup.createPost()
                    if (post != null) {
                        eventVM.createNewPost(post)
                        popup.dismiss()
                    }
                }
                popup.show(childFragmentManager, "")
            }
            binding.pendingButton.id -> {
                val action =
                    EventPageFragmentDirections.actionEventPageFragmentToPendingSlotsFragment()
                action.id = eventVM.getEvent().value!!.id
                findNavController().navigate(action)
            }
            binding.editButton.id -> {
                val action =
                    EventPageFragmentDirections.actionEventPageFragmentToEditActionFragment(eventVM.getEvent().value!!.id)
                action.isEvent = true
                findNavController().navigate(action)
            }
            binding.manageButton.id -> {
                val action =
                    EventPageFragmentDirections
                        .actionEventPageFragmentToEventVolunteerSlotsFragment()
                action.eventId = eventVM.getEvent().value!!.id
                action.edit = true
                findNavController().navigate(action)
            }
            binding.projectTag.id -> {
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                val responsible = eventVM.getEvent().value!!.primaryRepresentative.id == userId
                if (responsible) {
                    val action =
                        EventPageFragmentDirections.actionEventPageFragmentToEditProjectFragment()
                    action.id = eventVM.getEvent().value!!.project.id
                    findNavController().navigate(action)
                } else {
                    val action =
                        EventPageFragmentDirections.actionEventPageFragmentToProjectPageFragment()
                    action.id = eventVM.getEvent().value!!.project.id
                    findNavController().navigate(action)
                }
            }
        }
    }
}
