package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.ActionDAO
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.databinding.FragmentCreateActionBinding
import ufes.grad.mobile.communitylink.viewmodel.CreateActionVM

class CreateActionFragment : Fragment(R.layout.fragment_create_action), View.OnClickListener {

    private var _binding: FragmentCreateActionBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var createActionVM: CreateActionVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCreateActionBinding.inflate(inflater, container, false)
        createActionVM = ViewModelProvider(this)[CreateActionVM::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                var action : ActionModel
                if (true){
                    action = ActionEventModel(
                        name = binding.nameForms.editText.text.toString().trim(),
                        description = binding.descriptionForms.editText.text.toString().trim(),
                        tags = binding.tagsForms.editText.text.toString().split(","),
                        status = false
                        // TODO representatives
                    )
                } else {
                    action = ActionDonationModel(
                        name = binding.nameForms.editText.text.toString().trim(),
                        description = binding.descriptionForms.editText.text.toString().trim(),
                        tags = binding.tagsForms.editText.text.toString().split(","),
                        status = false
                        // TODO representatives
                    )
                }
                // A ação que acabou de ser criada deve ser ativada primeiro, ou seja, o campo
                // status deve estar false
                createActionVM.createNewAction(action)
            }
        }
    }
}
