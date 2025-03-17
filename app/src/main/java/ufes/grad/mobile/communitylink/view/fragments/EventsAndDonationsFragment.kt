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
import ufes.grad.mobile.communitylink.databinding.FragmentEventsAndDonationsBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter

class EventsAndDonationsFragment : Fragment(R.layout.fragment_events_and_donations) {

    private var _binding: FragmentEventsAndDonationsBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: ListInfoCardAdapter = ListInfoCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO("Get content from DB")
        adapter.updateList(StaticData.eventActions + StaticData.donationActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEventsAndDonationsBinding.inflate(inflater, container, false)

        setupAdapters()

        return binding.root
    }

    fun setupAdapters() {
        adapter.onItemClickListener = { position ->
            val item = adapter.list[position]
            when (item) {
                is ActionDonationModel -> {
                    val action =
                        EventsAndDonationsFragmentDirections
                            .actionEventsAndDonationsFragmentToDonationPageFragment()
                    action.id = item.id
                    findNavController().navigate(action)
                }
                is ActionEventModel -> {
                    val action =
                        EventsAndDonationsFragmentDirections
                            .actionEventsAndDonationsFragmentToEventPageFragment()
                    action.id = item.id
                    findNavController().navigate(action)
                }
            }
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
