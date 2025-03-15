package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.databinding.FragmentMyActionsBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter

class MyActionsFragment : Fragment(R.layout.fragment_my_actions) {

    private var _binding: FragmentMyActionsBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: ListInfoCardAdapter =
        ListInfoCardAdapter(ListInfoCardAdapter.InfoCardContent.MY_ACTIONS)

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
        _binding = FragmentMyActionsBinding.inflate(inflater, container, false)

        adapter.onItemClickListener = { position ->
            val model = adapter.list[position]
            // TODO("Add navigation args")
            findNavController().navigate(R.id.editActionFragment)
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
