package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.FragmentCreateProjectBinding
import ufes.grad.mobile.communitylink.viewmodel.CreateProjectVM
import java.util.Date

class CreateProjectFragment : Fragment(R.layout.fragment_create_project), View.OnClickListener {

    private var _binding: FragmentCreateProjectBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var createProjectVM: CreateProjectVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCreateProjectBinding.inflate(inflater, container, false)
        binding.createButton.setOnClickListener(this)
        createProjectVM = ViewModelProvider(this)[CreateProjectVM::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                // TODO("Get data and create new project")
                // (por favor, ao criar os dados, botem eles no pendingData do ProjectModel e
                // coloquem o campo request em false, o usuário deve solicitar a revisão na página
                // do projeto)
                createProjectVM.createNewProject(
                    ProjectDataModel(
                        name = binding.nameForms.editText.text.toString().trim(),
                        description = binding.descriptionForms.editText.text.toString().trim(),
                        tags = binding.tagsForms.editText.text.toString().split(','),
                        address = binding.addressForms.editText.text.toString(),
                        CNPJ =  binding.cnpjForms.editText.text.toString().trim(),
                        pixKey = binding.pixForms.editText.text.toString().trim(),
                        underReview = false,
                        changeDate = Date().toString(),
                        // TODO("FALTA A LOGO")
                    )
                )
            }
        }
    }
}
