package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.ProjectStatusEnum
import ufes.grad.mobile.communitylink.databinding.FragmentEditProjectBinding
import ufes.grad.mobile.communitylink.view.popups.BasePopup

class EditProjectFragment : Fragment(R.layout.fragment_edit_project), View.OnClickListener {

    private var _binding: FragmentEditProjectBinding? = null
    private val binding
        get() = _binding!!

    private val args: EditProjectFragmentArgs by navArgs()

    private lateinit var project: ProjectModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO("Search for project")
        project = StaticData.projects[0]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditProjectBinding.inflate(inflater, container, false)

        if (project.status == ProjectStatusEnum.DEPRECATED)
            binding.deactivateButton.text = getString(R.string.continue_project)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_project)
                popup.onConfirm =
                    {
                        // TODO("Delete project")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.requestButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_project_revision)
                popup.onConfirm = {
                    // TODO("Send project to revision")
                    val newData =
                        ProjectDataModel(
                            name = binding.nameForms.editText.text.toString().trim(),
                            description = binding.descriptionForms.editText.text.toString().trim(),
                            tags =
                                binding.tagsForms.editText.text
                                    .toString()
                                    .trim()
                                    .split(",")
                                    .toMutableList(),
                            address = binding.addressForms.editText.text.toString().trim(),
                            CNPJ = binding.cnpjForms.editText.text.toString().trim(),
                            pixKey = binding.pixForms.editText.text.toString().trim(),
                        )
                    project.pendingData = newData
                }
                popup.show(childFragmentManager, "")
            }
            binding.deactivateButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_discontinue_project)
                popup.onConfirm = {
                    // TODO("De DEPRECATED ele vai pra onde?")
                    if (project.status == ProjectStatusEnum.DEPRECATED)
                        project.status = ProjectStatusEnum.PENDING
                }
                popup.show(childFragmentManager, "")
            }
            binding.actionsButton.id -> {
                val action =
                    EditProjectFragmentDirections
                        .actionEditProjectFragmentToProjectActionsListFragment()
                action.projectId = project.id
                findNavController().navigate(action)
            }
            binding.membersButton.id -> {
                val action =
                    EditProjectFragmentDirections
                        .actionEditProjectFragmentToProjectMembersFragment()
                action.id = project.id
                findNavController().navigate(action)
            }
            binding.donationsButton.id -> {
                val action =
                    EditProjectFragmentDirections.actionEditProjectFragmentToDonationListFragment()
                action.id = project.id
                action.isProject = true
                findNavController().navigate(action)
            }
        }
    }
}
