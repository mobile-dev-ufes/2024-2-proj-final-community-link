package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.FragmentMyProjectsBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.MyProjectsVM

class MyProjectsFragment : Fragment(R.layout.fragment_my_projects), View.OnClickListener {

    private var _binding: FragmentMyProjectsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var myProjectsVM: MyProjectsVM

    private var filter: String = ""

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

        // TODO("Make filter work")
        setupFilters()
        setupAdapter()

        binding.createButton.setOnClickListener(this)

        setObserver()
        myProjectsVM.listProjects()
        return binding.root
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val item = adapter.list[position] as ProjectModel
            // TODO("Conferir se o usuário é responsável ou membro")
            val is_responsible = myProjectsVM.isResponsible(item.id)

            if (is_responsible) {
                val project =
                    MyProjectsFragmentDirections.actionMyProjectsFragmentToEditProjectFragment()
                Log.d("My projects", item.id)
                project.id = item.id
                findNavController().navigate(project)
            } else {
                Log.d("My projects", item.id)

                val project =
                    MyProjectsFragmentDirections.actionMyProjectsFragmentToProjectPageFragment()
                project.id = item.id
                findNavController().navigate(project)
            }
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    fun setupFilters() {
        val status =
            listOf(
                getString(R.string.status),
                getString(R.string.actives),
                getString(R.string.discontinued)
            )
        binding.filter.adapter = SpinnerAdapter(requireContext(), status)
        binding.filter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    filter = if (position == 0) "" else status[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
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
