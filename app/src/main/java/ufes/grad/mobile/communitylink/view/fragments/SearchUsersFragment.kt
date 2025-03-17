package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.databinding.FragmentSearchUsersBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup
import ufes.grad.mobile.communitylink.viewmodel.SearchUsersVM

class SearchUsersFragment : Fragment(R.layout.fragment_search_users), View.OnClickListener {

    private var _binding: FragmentSearchUsersBinding? = null
    private val binding
        get() = _binding!!

    private val args: SearchUsersFragmentArgs by navArgs()

    private lateinit var searchVM: SearchUsersVM

    private var adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchVM = ViewModelProvider(this)[SearchUsersVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSearchUsersBinding.inflate(inflater, container, false)

        adapter.onItemClickListener = { position ->
            var popup =
                UserDataPopup(
                    adapter.list[position].id,
                    false,
                    UserDataPopup.UserPopupType.ADD_USER_AS_MEMBER
                )
            popup.onConfirm = {
                findNavController()
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("user", adapter.list[position] as? UserModel)
                " "
            }
            popup.show(childFragmentManager, "")
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    fun setObserver() {
        searchVM.getList().observe(viewLifecycleOwner, Observer { adapter.updateList(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.searchButton.id -> {
                val name = binding.nameForms.editText.text.toString().trim()
                searchVM.search(name)
            }
        }
    }
}
