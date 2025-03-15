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
import ufes.grad.mobile.communitylink.databinding.FragmentGoalListBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter

class GoalListFragment : Fragment(R.layout.fragment_goal_list) {

    private var _binding: FragmentGoalListBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    init {
        adapter.updateList(StaticData.goals)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentGoalListBinding.inflate(inflater, container, false)

        adapter.onItemClickListener = { position ->
            // TODO("Add navigation args")
            findNavController().navigate(R.id.manageGoalFragment)
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
