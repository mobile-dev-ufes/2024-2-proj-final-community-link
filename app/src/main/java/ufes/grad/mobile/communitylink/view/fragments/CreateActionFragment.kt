package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.databinding.FragmentCreateActionBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.CreateActionVM

class CreateActionFragment : Fragment(R.layout.fragment_create_action), View.OnClickListener {

    private var _binding: FragmentCreateActionBinding? = null
    private val binding
        get() = _binding!!

    private val args: CreateActionFragmentArgs by navArgs()

    private lateinit var createActionVM: CreateActionVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createActionVM = ViewModelProvider(this)[CreateActionVM::class.java]
        createActionVM.getProjectById(args.projectId)
        createActionVM.fetchUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCreateActionBinding.inflate(inflater, container, false)
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
                val name = binding.nameForms.editText.text?.toString()?.trim() ?: ""
                val description = binding.descriptionForms.editText.text?.toString()?.trim() ?: ""
                val tags = binding.tagsForms.editText.text?.toString()?.split(",") ?: listOf()
                val primaryRepresentative = createActionVM.getUser().value

                if (primaryRepresentative == null) {
                    Utilities.notify(context, getString(R.string.erro_get_user_data))
                    return
                }
                if (name.isEmpty() || description.isEmpty() || tags.isEmpty()) {
                    Utilities.notify(context, getString(R.string.preencha_todos_os_campos))
                    return
                }

                val action: ActionModel =
                    ActionEventModel(
                        name = name,
                        description = description,
                        tags = tags.toMutableList(),
                        status = false,
                        project = createActionVM.getProject().value!!,
                        primaryRepresentative = primaryRepresentative
                    )

                createActionVM.createNewAction(action)
            }
        }
    }
}
