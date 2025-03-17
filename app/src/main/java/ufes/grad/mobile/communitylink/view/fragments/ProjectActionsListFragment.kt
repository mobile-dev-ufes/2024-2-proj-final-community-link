package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.databinding.FragmentProjectActionsListBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter

class ProjectActionsListFragment :
    Fragment(R.layout.fragment_project_actions_list), OnClickListener {

    private var _binding: FragmentProjectActionsListBinding? = null
    private val binding
        get() = _binding!!

    private val args: ProjectPageFragmentArgs by navArgs()

    private val adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.updateList(StaticData.eventActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectActionsListBinding.inflate(inflater, container, false)

        binding.createButton.setOnClickListener(this)

        adapter.onItemClickListener = { position ->
            val action =
                ProjectActionsListFragmentDirections
                    .actionProjectActionsListFragmentToEventPageFragment()
            action.id = adapter.list[position].id
            findNavController().navigate(action)
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.create_button -> {
                val action =
                    ProjectActionsListFragmentDirections
                        .actionProjectActionsListFragmentToCreateActionFragment()
                Log.d("ARGS", args.id)
                action.id = args.id
                findNavController().navigate(action)
            }
        }
    }
}
