package ufes.grad.mobile.communitylink.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.util.Date
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.databinding.FragmentCreateProjectBinding
import ufes.grad.mobile.communitylink.viewmodel.CreateProjectVM

class CreateProjectFragment : Fragment(R.layout.fragment_create_project), View.OnClickListener {

    private var _binding: FragmentCreateProjectBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var createProjectVM: CreateProjectVM

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                createProjectVM.setImage(result.data?.data)
            }
        }

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
        binding.imageButton.setOnClickListener(this)

        setObserver()

        return binding.root
    }

    fun setObserver() {
        createProjectVM
            .getImageSelected()
            .observe(viewLifecycleOwner, Observer { binding.image.setImageURI(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.imageButton.id -> {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                resultLauncher.launch(intent)
            }
            binding.createButton.id -> {
                createProjectVM.createNewProject(
                    ProjectDataModel(
                        name = binding.nameForms.editText.text.toString().trim(),
                        description = binding.descriptionForms.editText.text.toString().trim(),
                        tags = binding.tagsForms.editText.text.toString().split(',').toMutableList(),
                        address = binding.addressForms.editText.text.toString(),
                        CNPJ = binding.cnpjForms.editText.text.toString().trim(),
                        pixKey = binding.pixForms.editText.text.toString().trim(),
                        underReview = false,
                        changeDate = Date().toString(),
                        //                        logo = Utilities.imageToBytes(
                        //                            requireContext(),
                        //                            createProjectVM.getImageSelected().value
                        //                        )
                    )
                )
            }
        }
    }
}
