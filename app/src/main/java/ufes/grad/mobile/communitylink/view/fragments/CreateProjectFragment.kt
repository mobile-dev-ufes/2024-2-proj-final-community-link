package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentCreateProjectBinding

class CreateProjectFragment : Fragment(R.layout.fragment_create_project), View.OnClickListener {

    private var _binding: FragmentCreateProjectBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCreateProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.createButton.id -> {
                TODO("Get data and create new project")
                // (por favor, ao criar os dados, botem eles no pendingData do ProjectModel e
                // coloquem o campo request em false, o usuário deve solicitar a revisão na página
                // do projeto)
            }
        }
    }
}
