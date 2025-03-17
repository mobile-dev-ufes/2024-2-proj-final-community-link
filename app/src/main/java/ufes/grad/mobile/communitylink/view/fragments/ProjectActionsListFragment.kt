package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.databinding.FragmentProjectActionsListBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.ProjectActionsVM

class ProjectActionsListFragment :
    Fragment(R.layout.fragment_project_actions_list), OnClickListener {

    private var _binding: FragmentProjectActionsListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var projectVM: ProjectActionsVM

    private val auth = FirebaseAuth.getInstance()

    private val args: ProjectPageFragmentArgs by navArgs()

    private val adapter: ListInfoCardAdapter =
        ListInfoCardAdapter(ListInfoCardAdapter.InfoCardContent.MY_ACTIONS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        projectVM = ViewModelProvider(this)[ProjectActionsVM::class.java]
        projectVM.setListActions(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectActionsListBinding.inflate(inflater, container, false)

        binding.createButton.setOnClickListener(this)

        setupAdapter()
        setObserver()

        return binding.root
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val item = (adapter.list[position] as ActionEventModel)
            val action =
                ProjectActionsListFragmentDirections
                    .actionProjectActionsListFragmentToEventPageFragment()
            action.id = item.id
            action.edit = item.primaryRepresentative.id == auth.currentUser!!.uid
            findNavController().navigate(action)
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    fun setObserver() {
        projectVM.listActions().observe(viewLifecycleOwner, Observer { adapter.updateList(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                val action =
                    ProjectActionsListFragmentDirections
                        .actionProjectActionsListFragmentToCreateActionFragment()
                action.id = args.id
                findNavController().navigate(action)
            }
        }
    }
}
