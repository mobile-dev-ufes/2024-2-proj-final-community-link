package ufes.grad.mobile.communitylink.view.fragments

import android.net.Uri
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
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.FragmentProjectPageBinding
import ufes.grad.mobile.communitylink.view.popups.MakeDonationPopup

class ProjectPageFragment : Fragment(R.layout.fragment_project_page), View.OnClickListener {

    private var _binding: FragmentProjectPageBinding? = null
    private val binding
        get() = _binding!!

    private val args: ProjectPageFragmentArgs by navArgs()

    private lateinit var project: ProjectModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO("Load project from DB")
        project = StaticData.projects[0]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectPageBinding.inflate(inflater, container, false)

        setupLayout()

        return binding.root
    }

    fun setupLayout() {
        binding.nameText.text = project.currentData.name
        binding.descriptionText.text = project.currentData.description
        binding.addressText.text = project.currentData.address
        binding.cnpjText.text = project.currentData.CNPJ

        if (project.currentData.logo.isNotEmpty())
            binding.image.setImageURI(Uri.parse(project.currentData.logo))

        binding.actionsButton.setOnClickListener(this)
        binding.membersButton.setOnClickListener(this)
        binding.donationsButton.setOnClickListener(this)
        binding.donationButton.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.membersButton.id -> {
                findNavController().navigate(R.id.projectMembersFragment)
                // TODO("Add arguments to navigation")
            }
            binding.actionsButton.id -> {
                findNavController().navigate(R.id.projectActionsListFragment)
                // TODO("Add arguments to navigation")
            }
            binding.donationsButton.id -> {
                findNavController().navigate(R.id.donationListFragment)
                // TODO("Add arguments to navigation")
            }
            binding.donationButton.id -> {
                val popup = MakeDonationPopup()
                popup.setModel(project)
                popup.onConfirm = {
                    val donation: DonationModel? = popup.makeDonation()
                    if (donation != null) {
                        // TODO("Register donation")
                        popup.dismiss()
                    }
                }
                popup.show(childFragmentManager, "")
            }
        }
    }
}
