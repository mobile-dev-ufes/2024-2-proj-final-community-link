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
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.FragmentMyProjectsBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.MyProjectsVM

class MyProjectsFragment : Fragment(R.layout.fragment_my_projects), View.OnClickListener {

    private var _binding: FragmentMyProjectsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var myProjectsVM: MyProjectsVM

    private val adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myProjectsVM = ViewModelProvider(this)[MyProjectsVM::class.java]
    }

    fun setObserver() {
        myProjectsVM.getProjects().observe(viewLifecycleOwner, Observer { adapter.updateList(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMyProjectsBinding.inflate(inflater, container, false)

        setupAdapter()

        binding.createButton.setOnClickListener(this)

        setObserver()
        myProjectsVM.listProjects()
        return binding.root
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val item = adapter.list[position] as ProjectModel
            val is_responsible = myProjectsVM.isResponsible(item.id)

            if (is_responsible) {
                val project =
                    MyProjectsFragmentDirections.actionMyProjectsFragmentToEditProjectFragment()
                project.id = item.id
                findNavController().navigate(project)
            } else {
                val project =
                    MyProjectsFragmentDirections.actionMyProjectsFragmentToProjectPageFragment()
                project.id = item.id
                findNavController().navigate(project)
            }
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                val project =
                    MyProjectsFragmentDirections.actionMyProjectsFragmentToCreateProjectFragment()
                findNavController().navigate(project)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
