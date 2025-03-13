package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentDashboardBinding
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    val adapter: ListInfoCardAdapter = ListInfoCardAdapter()

    init {
        adapter.updateInfoCardList(StaticData.eventActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.recyclerListDashboard.layoutManager = LinearLayoutManager(context)
        binding.recyclerListDashboard.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
