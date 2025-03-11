package ufes.grad.mobile.communitylink.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentProfileBinding
import ufes.grad.mobile.communitylink.model.User
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.ProfileVM
import ufes.grad.mobile.communitylink.viewmodel.SignupVM

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileVM: ProfileVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.projectsButton.setOnClickListener(this)
        binding.actionsButton.setOnClickListener(this)
        binding.eventsButton.setOnClickListener(this)
        binding.pendingButton.setOnClickListener(this)
        binding.logoutButton.setOnClickListener(this)
        binding.confirmAlterations.setOnClickListener(this)
        binding.excludeAccount.setOnClickListener(this)
        profileVM = ViewModelProvider(this)[ProfileVM::class.java]
        setObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileVM.getUserData()
    }

    private fun setObserver() {
        profileVM.userData().observe(viewLifecycleOwner,
            Observer { it ->
            binding.name.editText.setText(it.get("name"))
            binding.email.editText.setText(it.get("email"))
            binding.cpf.editText.setText(it.get("cpf"))
            binding.dob.editText.setText(it.get("dob"))
            binding.addressForm.editText.setText(it.get("address"))
            binding.phone.editText.setText(it.get("phone"))
            binding.sex.editText.setText(it.get("sex"))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.projectsButton.id -> {
                Utilities.loadFragment(requireActivity(), MyProjectsFragment())
            }

            binding.actionsButton.id -> {
                Utilities.loadFragment(requireActivity(), MyActionsFragment())
            }

            binding.eventsButton.id -> {
                Utilities.loadFragment(requireActivity(), PastActionsFragment())
            }

            binding.pendingButton.id -> {
                Utilities.loadFragment(requireActivity(), PendingActionsFragment())
            }

            binding.logoutButton.id -> {
                TODO("NÃO FUNCIONANDO AINDA")
                profileVM.logout()
                Utilities.loadFragment(LoginActivity(), LoginFragment())
                requireActivity().finish()
            }

            binding.excludeAccount.id -> {
                TODO("ABRE O POPUP PRA CONFIRMAR")
                requireActivity().finish()
            }

            binding.confirmAlterations.id -> {
                //TODO("Abrir popup de confirmação")
                profileVM.changeUserData(
                    User(
                        binding.name.editText.text.toString(),
                        binding.cpf.editText.text.toString(),
                        binding.sex.editText.text.toString(),
                        binding.dob.editText.text.toString(),
                        binding.addressForm.editText.text.toString(),
                        binding.phone.editText.text.toString()
                    ),
                    binding.email.editText.text.toString()
                )
            }
        }
    }
}