package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.databinding.FragmentPendingSlotsBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup

class PendingSlotsFragment : Fragment(R.layout.fragment_pending_slots) {

    private var _binding: FragmentPendingSlotsBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: ListInfoCardAdapter =
        ListInfoCardAdapter(ListInfoCardAdapter.InfoCardContent.CANCELLABLE)

    init {
        adapter.updateList(StaticData.eventActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentPendingSlotsBinding.inflate(inflater, container, false)

        adapter.onButtonClickListener = { position ->
            // TODO("Reject volunteer")
        }
        adapter.onItemClickListener = { position ->
            val popup = UserDataPopup(UserDataPopup.UserPopupType.SLOT_REQUEST)
            popup.onConfirm =
                {
                    // TODO("Accept volunteer in slot")
                }
            popup.show(childFragmentManager, "")
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
}
