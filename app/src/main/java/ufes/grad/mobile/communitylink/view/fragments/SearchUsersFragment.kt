package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.databinding.FragmentSearchUsersBinding
import ufes.grad.mobile.communitylink.view.adapter.ListCommonCardAdapter
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup

class SearchUsersFragment : Fragment(R.layout.fragment_search_users), View.OnClickListener {

    private var _binding: FragmentSearchUsersBinding? = null
    private val binding
        get() = _binding!!

    private val args: SearchUsersFragmentArgs by navArgs()

    private lateinit var project: BaseModel

    private var adapter: ListCommonCardAdapter = ListCommonCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO("Get real project or action")
        project = StaticData.projects[0]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSearchUsersBinding.inflate(inflater, container, false)

        adapter.updateList(StaticData.users)

        adapter.onItemClickListener = { position ->
            var popup =
                UserDataPopup(
                    if (args.findUsers) UserDataPopup.UserPopupType.ADD_USER_AS_MEMBER
                    else UserDataPopup.UserPopupType.ADD_MEMBER_AS_REPRESENTATIVE
                )
            popup.setUser(adapter.list[position] as UserModel)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.searchButton.id -> {
                val name = binding.nameForms.editText.text.toString().trim()
                val cpf = binding.cpfForms.editText.text.toString().trim()
                val email = binding.emailForms.editText.text.toString().trim()

                val list: List<BaseModel> = listOf()
                if (args.findUsers) {
                    // TODO("Search users with filters")
                } else {
                    // TODO("Search members with filters")
                }
                adapter.updateList(list)
            }
        }
    }
}
