package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.GoalModel
import ufes.grad.mobile.communitylink.databinding.FragmentManageGoalBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.popups.BasePopup
import ufes.grad.mobile.communitylink.viewmodel.ManageGoalVM

class ManageGoalFragment : Fragment(R.layout.fragment_manage_goal), View.OnClickListener {

    private var _binding: FragmentManageGoalBinding? = null
    private val binding
        get() = _binding!!

    private val args: ManageGoalFragmentArgs by navArgs()

    private var goal: GoalModel? = null
    private lateinit var donation: ActionDonationModel

    private lateinit var viewModel: ManageGoalVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ManageGoalVM::class.java]

        viewModel.getGoalData(args.id)
        viewModel.changeGoalData(StaticData.goals[0])

        if (args.isDonation) {
            // TODO("Get project from BD")
            donation = StaticData.donationActions[0]
        } else {
            // TODO("Get goal from BD")
            goal = StaticData.goals[0]
            donation = goal!!.actionDonation
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentManageGoalBinding.inflate(inflater, container, false)

        setupLayout()
        setObserver()

        return binding.root
    }

    private fun setupLayout() {
        binding.confirmButton.setOnClickListener(this)
        binding.deleteButton.setOnClickListener(this)
        if (goal == null) binding.confirmButton.text = getString(R.string.create_new_goal)
    }

    private fun setObserver() {
        viewModel
            .goalData()
            .observe(
                viewLifecycleOwner,
                Observer { it ->
                    binding.descriptionForms.editText.setText(it.get("description"))
                    binding.minimumForms.editText.setText(it.get("minimalQuantity"))
                    binding.currentForms.editText.setText(it.get("actualQuantity"))
                }
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                val description = binding.descriptionForms.editText.text.toString().trim()
                val minimalQuantity = binding.minimumForms.editText.text.toString().toFloatOrNull()
                val actualQuantity = binding.currentForms.editText.text.toString().toFloatOrNull()

                if (description.isEmpty() || minimalQuantity == null || actualQuantity == null) {
                    Utilities.notify(requireContext(), getString(R.string.preencha_todos_os_campos))
                    return
                }

                val goal =
                    GoalModel(
                        description = description,
                        minimalQuantity = minimalQuantity,
                        actualQuantity = actualQuantity,
                        actionDonation = donation
                    )

                if (!args.isDonation) viewModel.changeGoalData(goal)
                else viewModel.createNewGoal(goal)
            }
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_goal)
                popup.onConfirm =
                    {
                        // TODO("Delete goal")
                    }
                popup.show(childFragmentManager, "")
            }
        }
    }
}
