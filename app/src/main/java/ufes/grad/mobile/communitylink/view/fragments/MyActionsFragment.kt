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
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.databinding.FragmentMyActionsBinding
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter
import ufes.grad.mobile.communitylink.viewmodel.MyActionsVM

class MyActionsFragment : Fragment(R.layout.fragment_my_actions) {

    private var _binding: FragmentMyActionsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var actionVM: MyActionsVM

    private val adapter: ListInfoCardAdapter =
        ListInfoCardAdapter(ListInfoCardAdapter.InfoCardContent.PAST)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionVM = ViewModelProvider(this)[MyActionsVM::class.java]
        actionVM.listEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMyActionsBinding.inflate(inflater, container, false)

        setupAdapter()
        setObserver()

        return binding.root
    }

    fun setObserver() {
        actionVM.getEvents().observe(viewLifecycleOwner, Observer { adapter.updateList(it) })
    }

    fun setupAdapter() {
        adapter.onItemClickListener = { position ->
            val event = MyActionsFragmentDirections.actionMyActionsFragmentToEventPageFragment()
            event.id = (adapter.list[position] as ActionEventModel).id
            event.edit = true
            findNavController().navigate(event)
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
