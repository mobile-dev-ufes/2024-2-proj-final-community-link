package ufes.grad.mobile.communitylink.view.fragments

import android.net.Uri
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
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.databinding.FragmentProjectPageBinding
import ufes.grad.mobile.communitylink.view.popups.MakeDonationPopup
import ufes.grad.mobile.communitylink.viewmodel.ProjectPageVM

class ProjectPageFragment : Fragment(R.layout.fragment_project_page), View.OnClickListener {

    private var _binding: FragmentProjectPageBinding? = null
    private val binding
        get() = _binding!!

    private val args: ProjectPageFragmentArgs by navArgs()

    private lateinit var projectVM: ProjectPageVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        projectVM = ViewModelProvider(this)[ProjectPageVM::class.java]
        projectVM.getProjectById(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProjectPageBinding.inflate(inflater, container, false)

        setObserver()

        return binding.root
    }

    fun setObserver() {
        projectVM.getProject().observe(viewLifecycleOwner, Observer{
            binding.nameText.text = it.currentData.name
            binding.descriptionText.text = it.currentData.description
            binding.addressText.text = it.currentData.address
            binding.cnpjText.text = it.currentData.CNPJ

            if (it.currentData.logo.isNotEmpty())
                binding.image.setImageURI(Uri.parse(it.currentData.logo))

            binding.actionsButton.setOnClickListener(this)
            binding.membersButton.setOnClickListener(this)
            binding.donationsButton.setOnClickListener(this)
            binding.donationButton.setOnClickListener(this)
        })
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
                popup.setModel(projectVM.getProject().value!!)
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
