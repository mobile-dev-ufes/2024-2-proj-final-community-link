package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.databinding.FragmentSearchUsersBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup

class SearchUsersFragment : Fragment(R.layout.fragment_search_users), View.OnClickListener {

    private var _binding: FragmentSearchUsersBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    init {
        adapter.updateList(StaticData.donationActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSearchUsersBinding.inflate(inflater, container, false)

        // TODO("Make search")

        adapter.onItemClickListener = { position ->
            // se procurando por membros
            val popup =
                UserDataPopup(
                    adapter.list[position] as? UserModel,
                    UserDataPopup.UserPopupType.ADD_USER_AS_MEMBER
                )
            popup.onConfirm =
                {
                    // TODO("Add user as member")
                }
            popup.show(childFragmentManager, "")

            // se procurando por representantes
            //            val popup = UserDataPopup(adapter.list[position],
            // UserDataPopup.UserPopupType.ADD_MEMBER_AS_REPRESENTATIVE)
            //            popup.onConfirm ={
            //                // TODO("Add member as representative")
            //            }
            //            popup.show(childFragmentManager, "")
        }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.searchButton.id -> {
                TODO("Make functional")
            }
        }
    }
}
