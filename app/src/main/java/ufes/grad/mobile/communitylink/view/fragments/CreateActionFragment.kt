package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.databinding.FragmentCreateActionBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.CreateActionVM


class CreateActionFragment : Fragment(R.layout.fragment_create_action), View.OnClickListener {

    enum class ActionType {
        NONE,
        EVENT,
        PROJECT
    }

    private var _binding: FragmentCreateActionBinding? = null
    private val binding
        get() = _binding!!

    private val args: CreateActionFragmentArgs by navArgs()

    private lateinit var createActionVM: CreateActionVM

    private var actionType: ActionType = ActionType.NONE
    private var clickedOnPrimary: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createActionVM = ViewModelProvider(this)[CreateActionVM::class.java]
        createActionVM.getProjectById(args.projectId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCreateActionBinding.inflate(inflater, container, false)

        setupDropdown()
        setObserver()

        val previousBackStackEntry =
            findNavController().getBackStackEntry(R.id.createActionFragment)
        previousBackStackEntry.savedStateHandle.getLiveData<UserModel>("user").observe(
            viewLifecycleOwner
        ) { result ->
            val result_ = result as UserModel
            if (clickedOnPrimary) createActionVM.changePrimaryRepresentative(result_)
            else createActionVM.changeSecondaryRepresentative(result_)
        }
        binding.createButton.setOnClickListener(this)
        return binding.root
    }

    fun setObserver() {
        createActionVM
            .getPrimaryRepresentative()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.firstRepresentative.setValues(
                        if (it == null) getString(R.string.primary_representative) else it.name
                    )
                }
            )
        createActionVM
            .getSecondaryRepresentative()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.secondRepresentative.setValues(
                        if (it == null) getString(R.string.secondary_representative) else it.name
                    )
                }
            )
        createActionVM.getProject().observe(
            viewLifecycleOwner,
            Observer {
            }
        )
    }

    fun setupDropdown() {
        val status =
            listOf(
                getString(R.string.action_type),
                getString(R.string.event),
                getString(R.string.donation)
            )
        binding.dropdown.adapter = SpinnerAdapter(requireContext(), status)
        binding.dropdown.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    actionType = ActionType.entries.toTypedArray()[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                val name = binding.nameForms.editText.text.toString().trim()
                val description = binding.descriptionForms.editText.text.toString().trim()
                val tags = binding.tagsForms.editText.text.toString().split(",")
                //val primaryRepresentative = createActionVM.getPrimaryRepresentative().value
                //val secondaryRepresentative = createActionVM.getSecondaryRepresentative().value

                if (
                    name.isEmpty() ||
                        description.isEmpty() ||
                        tags.isEmpty() ||
                        actionType == ActionType.NONE
                ) {
                    Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                    return
                }

                var action: ActionModel? = null
                if (actionType == ActionType.EVENT) {
                    action =
                        ActionEventModel(
                            name = name,
                            description = description,
                            tags = tags.toMutableList(),
                            status = false,
                            //primaryRepresentative = primaryRepresentative!!,
                           // secondaryRepresentative = secondaryRepresentative!!
                        )
                } else {
                    action =
                        ActionDonationModel(
                            name = name,
                            description = description,
                            tags = tags.toMutableList(),
                            status = false,
                            //primaryRepresentative = primaryRepresentative!!,
                           // secondaryRepresentative = secondaryRepresentative
                        )
                }
                createActionVM.createNewAction(action)
            }
            binding.firstRepresentative.id -> {
                createActionVM.changePrimaryRepresentative(null)
                clickedOnPrimary = true
                val search =
                    CreateActionFragmentDirections.actionCreateActionFragmentToSearchUsersFragment()
                search.findUsers = false
                findNavController().navigate(search)
            }
            binding.secondRepresentative.id -> {
                createActionVM.changePrimaryRepresentative(null)
                clickedOnPrimary = false
                val search =
                    CreateActionFragmentDirections.actionCreateActionFragmentToSearchUsersFragment()
                search.findUsers = false
                findNavController().navigate(search)
            }
        }
    }
}
