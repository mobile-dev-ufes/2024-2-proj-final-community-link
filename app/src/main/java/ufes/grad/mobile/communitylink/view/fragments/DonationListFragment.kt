package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentDonationListBinding
import ufes.grad.mobile.communitylink.view.adapter.DonationCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.DonationListVM
import ufes.grad.mobile.communitylink.viewmodel.ProjectPageVM

class DonationListFragment : Fragment(R.layout.fragment_donation_list) {

    private var _binding: FragmentDonationListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var donationVM: DonationListVM
    private lateinit var projectVM: ProjectPageVM

    private val args: DonationListFragmentArgs by navArgs()

    private var adapter: DonationCardAdapter = DonationCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        donationVM = ViewModelProvider(this)[DonationListVM::class.java]
        projectVM = ViewModelProvider(this)[ProjectPageVM::class.java]
        projectVM.getProjectById(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDonationListBinding.inflate(inflater, container, false)

        setObserver()
        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    fun setObserver() {
        projectVM
            .getProject()
            .observe(
                viewLifecycleOwner,
                Observer { donationVM.fetchDonations(it.donations.map { it.id }) }
            )
        donationVM.getDonations().observe(viewLifecycleOwner, Observer { adapter.updateList(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
