package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.DonationStatusEnum
import ufes.grad.mobile.communitylink.databinding.FragmentDonationListBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.view.adapter.DonationCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.DonationListVM

class DonationListFragment : Fragment(R.layout.fragment_donation_list), View.OnClickListener {

    private var _binding: FragmentDonationListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: DonationListVM

    private val args: DonationListFragmentArgs by navArgs()

    private lateinit var projectOrAction: BaseModel

    private var statusFilter: DonationStatusEnum? = null

    private var adapter: DonationCardAdapter = DonationCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DonationListVM::class.java]

        // TODO("Get project or action from BD")
        // TODO("Get data")
        if (args.isProject) {
            projectOrAction = StaticData.projects[0]
            adapter.updateList(StaticData.donationsToProject)
        } else {
            projectOrAction = StaticData.donationActions[0]
            adapter.updateList(StaticData.donationsToAction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDonationListBinding.inflate(inflater, container, false)

        setObserver()
        // TODO("Make filters work")
        binding.dateFilter.setOnClickListener(this)
        setupFilters()
        setupAdapter()

        return binding.root
    }

    fun setObserver() {
        viewModel
            .getDateFilter()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.dateFilter.text =
                        if (it.isEmpty()) getString(R.string.selecione_uma_data) else it
                }
            )
    }

    fun setupAdapter() {
        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupFilters() {
        val status =
            listOf(
                getString(R.string.status),
                getString(R.string.received),
                getString(R.string.pending)
            )
        binding.statusFilter.adapter = SpinnerAdapter(requireContext(), status)
        binding.statusFilter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    statusFilter =
                        if (position > 1) DonationStatusEnum.entries.toTypedArray()[position - 1]
                        else null
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.dateFilter.id -> {
                viewModel.showDatePicker(requireContext())
            }
        }
    }
}
