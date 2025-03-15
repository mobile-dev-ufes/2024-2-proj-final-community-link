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
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.FragmentExploreBinding
import ufes.grad.mobile.communitylink.view.adapter.ExploreCardAdapter

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private var _binding: FragmentExploreBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: ExploreCardAdapter = ExploreCardAdapter()

    init {
        // TODO("Get real data")
        // data includes actions close to happening and projects w/ actions close to happening
        adapter.updateList(StaticData.eventActions + StaticData.projects)
        // update this list when searching
        // update must contain actions and projects
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        // TODO("Make search work")

        adapter.onItemClickListener = { position ->
            // TODO("Add navigation args")
            when (adapter.list[position]) {
                is ProjectModel -> findNavController().navigate(R.id.projectPageFragment)
                is ActionDonationModel -> findNavController().navigate(R.id.donationPageFragment)
                is ActionEventModel -> findNavController().navigate(R.id.eventPageFragment)
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
