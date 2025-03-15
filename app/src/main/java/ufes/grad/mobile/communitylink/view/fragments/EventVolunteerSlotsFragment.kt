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
import ufes.grad.mobile.communitylink.databinding.FragmentEventVolunteerSlotsBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter
import ufes.grad.mobile.communitylink.view.popups.BasePopup

class EventVolunteerSlotsFragment :
    Fragment(R.layout.fragment_event_volunteer_slots), View.OnClickListener {

    private var _binding: FragmentEventVolunteerSlotsBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: ListInfoCardAdapter =
        ListInfoCardAdapter(ListInfoCardAdapter.InfoCardContent.CANCELLABLE)

    init {
        adapter.updateList(StaticData.slots)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEventVolunteerSlotsBinding.inflate(inflater, container, false)

        val edit = false

        adapter.onItemClickListener = { position ->
            if (edit) {
                TODO("Add navigation args")
                // reforçar que é para editar
                findNavController().navigate(R.id.editSlotFragment)
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

        // TODO("Get real volunteer slots")
        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                TODO("Add navigation args")
                // adicionar que está indo para criar, e não para editar
                findNavController().navigate(R.id.editSlotFragment)
            }
        }
    }
}
