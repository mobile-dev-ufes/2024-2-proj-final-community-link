package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.util.Date
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.databinding.FragmentCreateProjectBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.CreateProjectVM

class CreateProjectFragment : Fragment(R.layout.fragment_create_project), View.OnClickListener {

    private var _binding: FragmentCreateProjectBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var createProjectVM: CreateProjectVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createProjectVM = ViewModelProvider(this)[CreateProjectVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCreateProjectBinding.inflate(inflater, container, false)

        binding.createButton.setOnClickListener(this)

        return binding.root
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
                val tags = binding.tagsForms.editText.text.toString().split(',').toMutableList()
                val pixKey = binding.pixForms.editText.text.toString().trim()
                if (name.isEmpty() || description.isEmpty() || tags.isEmpty() || pixKey.isEmpty()) {
                    Utilities.notify(requireContext(), getString(R.string.preencha_todos_os_campos))
                    return
                }

                createProjectVM.createNewProject(
                    ProjectDataModel(
                        name = name,
                        description = description,
                        tags = tags,
                        address = binding.addressForms.editText.text.toString(),
                        CNPJ = binding.cnpjForms.editText.text.toString().trim(),
                        pixKey = pixKey,
                        underReview = false,
                        changeDate = Date().toString(),
                    )
                )
            }
        }
    }
}
