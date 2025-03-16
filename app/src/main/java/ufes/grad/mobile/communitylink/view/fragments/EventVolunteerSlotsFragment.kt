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
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.databinding.FragmentEventVolunteerSlotsBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter
import ufes.grad.mobile.communitylink.view.popups.BasePopup

class EventVolunteerSlotsFragment :
    Fragment(R.layout.fragment_event_volunteer_slots), View.OnClickListener {

    private var _binding: FragmentEventVolunteerSlotsBinding? = null
    private val binding
        get() = _binding!!

    private val args: EventVolunteerSlotsFragmentArgs by navArgs()

    private lateinit var event: ActionEventModel

    private val adapter: ListInfoCardAdapter =
        ListInfoCardAdapter(ListInfoCardAdapter.InfoCardContent.CANCELLABLE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO("Get event model")
        event = StaticData.eventActions[0]
        adapter.updateList(StaticData.slots)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEventVolunteerSlotsBinding.inflate(inflater, container, false)

        setupAdapter()

        return binding.root
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            if (args.edit) {
                val action =
                    EventVolunteerSlotsFragmentDirections
                        .actionEventVolunteerSlotsFragmentToEditSlotFragment()
                action.id = adapter.list[position].id
                action.newSlot = false
                findNavController().navigate(action)
            } else {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_volunteer_for_slot)
                popup.onConfirm =
                    {
                        // TODO("Make new request")
                    }
                popup.show(childFragmentManager, "")
            }
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                val action =
                    EventVolunteerSlotsFragmentDirections
                        .actionEventVolunteerSlotsFragmentToEditSlotFragment()
                action.id = event.id
                action.newSlot = true
                findNavController().navigate(action)
            }
        }
    }
}
