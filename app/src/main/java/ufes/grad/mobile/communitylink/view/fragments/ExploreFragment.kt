package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.FragmentExploreBinding
import ufes.grad.mobile.communitylink.view.adapter.ExploreCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.ExploreVM

class ExploreFragment : Fragment(R.layout.fragment_explore), View.OnClickListener {

    private var _binding: FragmentExploreBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: ExploreCardAdapter = ExploreCardAdapter()
    private lateinit var viewModel: ExploreVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ExploreVM::class.java]
        adapter.updateList(StaticData.eventActions + StaticData.projects)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.searchBar.clearButton.setOnClickListener(this)

        setupAdapter()
        setObserver()
        return binding.root
    }

    private fun setObserver() {
        viewModel.getList().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val item = adapter.list[position]
            when (item) {
                is ProjectModel -> {
                    val project =
                        ExploreFragmentDirections.actionExploreFragmentToProjectPageFragment()
                    project.id = item.id
                    findNavController().navigate(project)
                }
                is ActionEventModel -> {
                    val event = ExploreFragmentDirections.actionExploreFragmentToEventPageFragment()
                    event.id = item.id
                    findNavController().navigate(event)
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

    override fun onClick(v: View) {
        when (v.id) {
            binding.searchBar.clearButton.id -> {
                if (binding.searchBar.editText.text.isNotBlank()){
                    viewModel.search(binding.searchBar.editText.text.toString())
                }
            }
        }
    }
}
