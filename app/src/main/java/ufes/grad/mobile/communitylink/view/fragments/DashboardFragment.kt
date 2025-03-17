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
import com.google.firebase.auth.FirebaseAuth
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.FragmentDashboardBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.DashboardVM

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: ListInfoCardAdapter = ListInfoCardAdapter()
    private lateinit var viewModel: DashboardVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DashboardVM::class.java]
        viewModel.setListActions()

        // TODO("Get from BD")
        adapter.updateList(StaticData.eventActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        setObserver()
        setupAdapter()

        return binding.root
    }

    private fun setObserver() {
        viewModel
            .getListActions()
            .observe(viewLifecycleOwner, Observer { adapter.updateList(it as List<BaseModel>) })
        // adapter.updateList(StaticData.eventActions + StaticData.donationActions)
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val item = adapter.list[position]
            val user = FirebaseAuth.getInstance().currentUser?.uid

            when (item) {
                is ActionEventModel -> {
                    val action =
                        DashboardFragmentDirections.actionDashboardFragmentToEventPageFragment()
                    action.id = item.id
                    action.edit = item.primaryRepresentative.id == user
                    findNavController().navigate(action)
                }
                is PostModel -> {
                    if (item.action is ActionEventModel) {
                        val action =
                            DashboardFragmentDirections.actionDashboardFragmentToEventPageFragment()
                        action.id = item.id
                        action.edit = item.action.primaryRepresentative.id == user
                        findNavController().navigate(action)
                    } else {
                        val donation =
                            DashboardFragmentDirections
                                .actionDashboardFragmentToDonationPageFragment()
                        donation.id = item.id
                        donation.edit = item.action.primaryRepresentative.id == user
                        findNavController().navigate(donation)
                    }
                }
            }
        }

        adapter.onProjectClickListener = { position ->
            val item = adapter.list[position]
            val project = DashboardFragmentDirections.actionDashboardFragmentToProjectPageFragment()

            when (item) {
                is ActionEventModel -> {
                    project.id = item.project.id
                }
                is PostModel -> {
                    project.id = item.action.project.id
                }
            }
            findNavController().navigate(project)
        }

        binding.recyclerListDashboard.layoutManager = LinearLayoutManager(context)
        binding.recyclerListDashboard.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
