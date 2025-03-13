package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProjectMembersBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter

class ProjectMembersFragment : Fragment(R.layout.fragment_project_members), View.OnClickListener {

    private var _binding: FragmentProjectMembersBinding? = null
    private val binding
        get() = _binding!!

    private var edit: Boolean = false

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

        // TODO("Add recycle view")

        binding.recyclerListResponsible.layoutManager = LinearLayoutManager(context)
        binding.recyclerListResponsible.adapter = ListCommonCardAdapter()

        binding.recyclerListMembers.layoutManager = LinearLayoutManager(context)
        binding.recyclerListMembers.adapter = ListCommonCardAdapter()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edit = requireArguments().getBoolean("EDIT")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.addMemberButton.id -> {
                // TODO("Add navigation")
            }
        }
    }
}
