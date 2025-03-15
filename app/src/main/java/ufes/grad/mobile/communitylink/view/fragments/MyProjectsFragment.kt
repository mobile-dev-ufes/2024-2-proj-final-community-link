package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.databinding.FragmentMyProjectsBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter

class MyProjectsFragment : Fragment(R.layout.fragment_my_projects), View.OnClickListener {

    private var _binding: FragmentMyProjectsBinding? = null
    private val binding
        get() = _binding!!

    private var filter: String? = null

    private val adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    init {
        // TODO("Get real data")
        adapter.updateList(StaticData.eventActions + StaticData.donationActions)
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

        adapter.onItemClickListener = { position ->
            // TODO("Add navigation args")
            findNavController().navigate(R.id.editProjectFragment)
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        binding.createButton.setOnClickListener(this)

        return binding.root
    }

    fun setupFilters() {
        val status =
            listOf(
                getString(R.string.status),
                getString(R.string.actives),
                getString(R.string.im_a_member),
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
                    filter = if (position == 0) null else status[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                // TODO("Add navigation args")
                findNavController().navigate(R.id.createProjectFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
