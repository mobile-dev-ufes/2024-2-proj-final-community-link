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
import ufes.grad.mobile.communitylink.databinding.FragmentProjectMembersBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup

class ProjectMembersFragment : Fragment(R.layout.fragment_project_members), View.OnClickListener {

    private var _binding: FragmentProjectMembersBinding? = null
    private val binding
        get() = _binding!!

    private var edit: Boolean = false

    private val members_adapter: ListCommonCardAdapter = ListCommonCardAdapter()
    private val responsible_adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    init {
        responsible_adapter.updateList(StaticData.users)
        members_adapter.updateList(StaticData.users)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectMembersBinding.inflate(inflater, container, false)

        if (!edit) {
            binding.addMemberButton.setOnClickListener(this)
            binding.addMemberButton.visibility = View.GONE
        }

        val popup_type =
            if (edit) UserDataPopup.UserPopupType.ADD_USER_AS_MEMBER
            else UserDataPopup.UserPopupType.VIEW_DATA

        responsible_adapter.onItemClickListener = {
            val popup = UserDataPopup(null, popup_type)
            popup.onConfirm =
                {
                    // TODO("Remove member from project")
                }
            popup.show(childFragmentManager, "")
        }

        binding.recyclerListResponsible.layoutManager = LinearLayoutManager(context)
        binding.recyclerListResponsible.adapter = responsible_adapter

        members_adapter.onItemClickListener = {
            val popup = UserDataPopup(null, popup_type)
            popup.onConfirm =
                {
                    // TODO("Remove member from project")
                }
            popup.show(childFragmentManager, "")
        }

        binding.recyclerListMembers.layoutManager = LinearLayoutManager(context)
        binding.recyclerListMembers.adapter = members_adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.addMemberButton.id -> {
                findNavController().navigate(R.id.searchUsersFragment)
            }
        }
    }
}
