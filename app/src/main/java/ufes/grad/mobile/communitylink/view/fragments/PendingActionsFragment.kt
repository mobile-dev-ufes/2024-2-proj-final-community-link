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
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.databinding.FragmentPendingActionsBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter
import ufes.grad.mobile.communitylink.view.popups.BasePopup

class PendingActionsFragment : Fragment(R.layout.fragment_pending_actions) {

    private var _binding: FragmentPendingActionsBinding? = null
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
        _binding = FragmentPendingActionsBinding.inflate(inflater, container, false)

        adapter.onButtonClickListener = { position ->
            val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_cancel_slot)
            popup.onConfirm =
                {
                    // TODO("Cancel slot")
                }
            popup.show(childFragmentManager, "")
        }
        adapter.onItemClickListener = { position ->
            // TODO("Add navigation args")
            if (adapter.list[position] is ActionEventModel)
                findNavController().navigate(R.id.eventPageFragment)
            else if (adapter.list[position] is ActionDonationModel)
                findNavController().navigate(R.id.donationPageFragment)
            else Utilities.notify(requireContext(), "Unknown card type")
        }

        // TODO("Get real user pending actions")

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
