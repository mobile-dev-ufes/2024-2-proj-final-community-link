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
import ufes.grad.mobile.communitylink.databinding.FragmentProjectActionsListBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter

class ProjectActionsListFragment : Fragment(R.layout.fragment_project_actions_list) {

    private var _binding: FragmentProjectActionsListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    init {
        // TODO("Get real data")
        adapter.updateList(StaticData.eventActions + StaticData.donationActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectActionsListBinding.inflate(inflater, container, false)

        adapter.onItemClickListener = { position ->
            // TODO("Add navigation args")
            val model = adapter.list[position]
            when (model) {
                is ActionEventModel -> findNavController().navigate(R.id.eventPageFragment)
                is ActionDonationModel -> findNavController().navigate(R.id.donationPageFragment)
            }
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
