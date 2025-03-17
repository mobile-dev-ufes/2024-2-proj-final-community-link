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
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProjectMembersBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup
import ufes.grad.mobile.communitylink.viewmodel.ProjectMembersVM
import ufes.grad.mobile.communitylink.viewmodel.ProjectPageVM

class ProjectMembersFragment : Fragment(R.layout.fragment_project_members), View.OnClickListener {

    private var _binding: FragmentProjectMembersBinding? = null
    private val binding
        get() = _binding!!

    private val args: ProjectMembersFragmentArgs by navArgs()

    private lateinit var projectVM: ProjectPageVM
    private lateinit var membersVM: ProjectMembersVM

    private val membersAdapter: ListCommonCardAdapter = ListCommonCardAdapter()
    private val responsibleAdapter: ListCommonCardAdapter = ListCommonCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        projectVM = ViewModelProvider(this)[ProjectPageVM::class.java]
        membersVM = ViewModelProvider(this)[ProjectMembersVM::class.java]
        projectVM.getProjectById(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectMembersBinding.inflate(inflater, container, false)

        if (!args.edit) {
            binding.addMemberButton.setOnClickListener(this)
            binding.addMemberButton.visibility = View.GONE
        }

        setupAdapters()
        setObserver()

        return binding.root
    }

    fun setObserver() {
        projectVM
            .getProject()
            .observe(
                viewLifecycleOwner,
                Observer {
                    membersVM.fetchCommonMembers(it.members.map { it.id })
                    membersVM.fetchResponsibleMembers(it.members.map { it.id })
                }
            )
        membersVM
            .getCommon()
            .observe(viewLifecycleOwner, Observer { membersAdapter.updateList(it) })
        membersVM
            .getResponsible()
            .observe(viewLifecycleOwner, Observer { responsibleAdapter.updateList(it) })
    }

    fun setupAdapters() {
        val popup_type =
            if (args.edit) UserDataPopup.UserPopupType.ADD_USER_AS_MEMBER
            else UserDataPopup.UserPopupType.VIEW_DATA

        responsibleAdapter.onItemClickListener = { position ->
            val popup = UserDataPopup(popup_type)
            popup.onConfirm =
                {
                    // TODO("Remove member from project")
                }
            popup.show(childFragmentManager, "")
        }

        binding.recyclerListResponsible.layoutManager = LinearLayoutManager(context)
        binding.recyclerListResponsible.adapter = responsibleAdapter

        membersAdapter.onItemClickListener = { position ->
            val popup = UserDataPopup(popup_type)
            popup.onConfirm =
                {
                    // TODO("Remove member from project")
                }
            popup.show(childFragmentManager, "")
        }

        binding.recyclerListMembers.layoutManager = LinearLayoutManager(context)
        binding.recyclerListMembers.adapter = membersAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.addMemberButton.id -> {
                val search =
                    ProjectMembersFragmentDirections
                        .actionProjectMembersFragmentToSearchUsersFragment()
                search.id = projectVM.getProject().value!!.id
                findNavController().navigate(R.id.searchUsersFragment)
            }
        }
    }
}
