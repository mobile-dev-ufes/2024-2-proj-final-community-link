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
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectStatusEnum
import ufes.grad.mobile.communitylink.databinding.FragmentEditProjectBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.popups.BasePopup
import ufes.grad.mobile.communitylink.viewmodel.EditProjectVM

class EditProjectFragment : Fragment(R.layout.fragment_edit_project), View.OnClickListener {

    private var _binding: FragmentEditProjectBinding? = null
    private val binding
        get() = _binding!!

    private val args: EditProjectFragmentArgs by navArgs()

    private lateinit var editVM: EditProjectVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editVM = ViewModelProvider(this)[EditProjectVM::class.java]
        editVM.getProjectById(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditProjectBinding.inflate(inflater, container, false)

        if (editVM.getProject().value?.status == ProjectStatusEnum.DEPRECATED)
            binding.deactivateButton.text = getString(R.string.continue_project)

        binding.membersButton.setOnClickListener(this)
        binding.donationsButton.setOnClickListener(this)
        binding.deactivateButton.setOnClickListener(this)
        binding.requestButton.setOnClickListener(this)
        binding.actionsButton.setOnClickListener(this)

        setObserver()

        return binding.root
    }

    fun setObserver() {
        editVM
            .getProject()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.nameForms.editText.setText(if (it == null) "" else it.currentData.name)
                    binding.descriptionForms.editText.setText(
                        if (it == null) "" else it.currentData.description
                    )
                    binding.tagsForms.editText.setText(
                        if (it == null) "" else it.currentData.tags.joinToString(", ")
                    )
                    binding.addressForms.editText.setText(
                        if (it == null) "" else it.currentData.address
                    )
                    binding.cnpjForms.editText.setText(if (it == null) "" else it.currentData.CNPJ)
                    binding.pixForms.editText.setText(if (it == null) "" else it.currentData.pixKey)
                }
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.requestButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_project_revision)
                popup.onConfirm = {
                    val name = binding.nameForms.editText.text.toString().trim()
                    val description = binding.descriptionForms.editText.text.toString().trim()
                    val tags =
                        binding.tagsForms.editText.text.toString().trim().split(",").toMutableList()
                    val address = binding.addressForms.editText.text.toString().trim()
                    val cnpj = binding.cnpjForms.editText.text.toString().trim()
                    val pixKey = binding.pixForms.editText.text.toString().trim()

                    if (
                        name.isEmpty() ||
                            description.isEmpty() ||
                            description.isEmpty() ||
                            tags.isEmpty() ||
                            pixKey.isEmpty()
                    ) {
                        Utilities.notify(
                            requireContext(),
                            getString(R.string.preencha_todos_os_campos)
                        )
                    } else {
                        editVM.updateData(
                            ProjectDataModel(
                                name = name,
                                description = description,
                                tags = tags,
                                address = address,
                                CNPJ = cnpj,
                                pixKey = pixKey,
                            )
                        )
                    }
                }
                popup.show(childFragmentManager, "")
            }
            binding.deactivateButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_discontinue_project)
                popup.onConfirm = {
                    editVM.getProject().value?.status =
                        if (editVM.getProject().value?.status == ProjectStatusEnum.DEPRECATED)
                            ProjectStatusEnum.ACCEPTED
                        else ProjectStatusEnum.DEPRECATED
                }
                popup.show(childFragmentManager, "")
            }
            binding.actionsButton.id -> {
                val action =
                    EditProjectFragmentDirections
                        .actionEditProjectFragmentToProjectActionsListFragment()
                action.id = editVM.getProject().value?.id!!
                findNavController().navigate(action)
            }
            binding.membersButton.id -> {
                val action =
                    EditProjectFragmentDirections
                        .actionEditProjectFragmentToProjectMembersFragment()
                action.id = editVM.getProject().value?.id!!
                findNavController().navigate(action)
            }
            binding.donationsButton.id -> {
                val action =
                    EditProjectFragmentDirections.actionEditProjectFragmentToDonationListFragment()
                action.id = editVM.getProject().value?.id!!
                action.isProject = true
                findNavController().navigate(action)
            }
        }
    }
}
