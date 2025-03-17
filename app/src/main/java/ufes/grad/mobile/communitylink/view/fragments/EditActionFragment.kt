package ufes.grad.mobile.communitylink.view.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import java.util.Calendar
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.databinding.FragmentEditActionBinding
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup
import ufes.grad.mobile.communitylink.view.popups.UserDataPopup.UserPopupType
import ufes.grad.mobile.communitylink.viewmodel.EditActionVM

class EditActionFragment : Fragment(R.layout.fragment_edit_action), View.OnClickListener {

    private var _binding: FragmentEditActionBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: EditActionVM

    private val args: EditActionFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditActionVM::class.java]
        viewModel.fetchAction(args.id, args.isEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditActionBinding.inflate(inflater, container, false)
        binding.confirmButton.setOnClickListener(this)
        setObserver()

        return binding.root
    }

    fun setObserver() {
        viewModel.getAction().observe(
            viewLifecycleOwner,
            Observer {
                binding.confirmButton.setOnClickListener(this)
                binding.startDateButton.setOnClickListener(this)
                binding.endDateButton.setOnClickListener(this)

                if (it.secondaryRepresentative == null)
                    binding.secondaryRepresentative.visibility = View.GONE

                binding.nameForms.editText.setText(it.name)
                binding.descriptionForms.editText.setText(it.description)
                binding.tagsForms.editText.setText(it.tags.joinToString(","))

                binding.startDateButton.text = it.initDate
                binding.endDateButton.text = it.finishDate

                if (it is ActionEventModel) {
                    val places = it.places
//                    binding.placeFormsOne.editText.setText(if (places[0].isNotEmpty()) places[0] else "")
//                    binding.placeFormsTwo.editText.setText(if (places[1].isNotEmpty()) places[1] else "")
//                    binding.placeFormsThree.editText.setText(if (places[2].isNotEmpty()) places[2] else "")
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.startDateButton.id -> {
                val calendar = Calendar.getInstance()
                var year: Int
                var month: Int
                var day: Int
                if (binding.startDateButton.text.isEmpty()) {
                    year = calendar.get(Calendar.YEAR)
                    month = calendar.get(Calendar.MONTH)
                    day = calendar.get(Calendar.DAY_OF_MONTH)
                } else {
                    val i: List<String> = binding.startDateButton.text.split("/")
                    day = i[0].toInt()
                    month = i[1].toInt()
                    year = i[2].toInt()
                }

                val datePickerDialog =
                    DatePickerDialog(
                        requireContext(),
                        { _, selectedYear, selectedMonth, selectedDay ->
                            binding.startDateButton.text =
                                String.format(
                                    "%02d/%02d/%d",
                                    selectedDay,
                                    selectedMonth + 1,
                                    selectedYear
                                )
                        },
                        year,
                        month,
                        day
                    )

                datePickerDialog.datePicker.minDate = calendar.timeInMillis
                datePickerDialog.show()
            }
            binding.endDateButton.id -> {
                val calendar = Calendar.getInstance()
                var year: Int
                var month: Int
                var day: Int
                if (binding.endDateButton.text.isEmpty()) {
                    year = calendar.get(Calendar.YEAR)
                    month = calendar.get(Calendar.MONTH)
                    day = calendar.get(Calendar.DAY_OF_MONTH)
                } else {
                    val i: List<String> = binding.endDateButton.text.split("/")
                    day = i[0].toInt()
                    month = i[1].toInt()
                    year = i[2].toInt()
                }

                val datePickerDialog =
                    DatePickerDialog(
                        requireContext(),
                        { _, selectedYear, selectedMonth, selectedDay ->
                            binding.endDateButton.text =
                                String.format(
                                    "%02d/%02d/%d",
                                    selectedDay,
                                    selectedMonth + 1,
                                    selectedYear
                                )
                        },
                        year,
                        month,
                        day
                    )

                val i: List<String> = binding.startDateButton.text.split("/")
                day = i[0].toInt()
                month = i[1].toInt()
                year = i[2].toInt()
                val cal = Calendar.getInstance()
                cal.set(year, month, day)

                datePickerDialog.datePicker.minDate = cal.timeInMillis
                datePickerDialog.show()
            }
            binding.confirmButton.id -> {

                val updatedAction: ActionModel
                if (viewModel.getAction().value is ActionDonationModel) {
                    updatedAction =
                        ActionDonationModel(
                            id = viewModel.getAction().value!!.id,
                            name = binding.nameForms.editText.text.toString().trim(),
                            description = binding.descriptionForms.editText.text.toString().trim(),
                            tags =
                                binding.tagsForms.editText.text
                                    .toString()
                                    .trim()
                                    .split(",")
                                    .toMutableList(),
                            initDate = binding.startDateButton.text.toString(),
                            finishDate = binding.endDateButton.text.toString(),
                            status = true,
                            project = viewModel.getAction().value!!.project,
                            posts = viewModel.getAction().value!!.posts,
                            primaryRepresentative = viewModel.getAction().value!!.primaryRepresentative,
                            goals = (viewModel.getAction().value!! as ActionDonationModel).goals,
                            donations = (viewModel.getAction().value!! as ActionDonationModel).donations
                        )
                } else {
                    var places = listOf<String>()
                    if (binding.placeFormsOne.editText.text.isNotEmpty())
                        places += binding.placeFormsOne.editText.text.toString().trim()
                    if (binding.placeFormsTwo.editText.text.isNotEmpty())
                        places += binding.placeFormsTwo.editText.text.toString().trim()
                    if (binding.placeFormsThree.editText.text.isNotEmpty())
                        places += binding.placeFormsThree.editText.text.toString().trim()

                    updatedAction =
                        ActionEventModel(
                            id = viewModel.getAction().value!!.id,
                            name = binding.nameForms.editText.text.toString().trim(),
                            description = binding.descriptionForms.editText.text.toString().trim(),
                            tags =
                                binding.tagsForms.editText.text
                                    .toString()
                                    .trim()
                                    .split(",")
                                    .toMutableList(),
                            initDate = binding.startDateButton.text.toString(),
                            finishDate = binding.endDateButton.text.toString(),
                            places = places.toMutableList(),
                            status = true,
                            project = viewModel.getAction().value!!.project,
                            posts = viewModel.getAction().value!!.posts,
                            primaryRepresentative = viewModel.getAction().value!!.primaryRepresentative,
                        )
                }
                viewModel.updateAction(updatedAction)
            }
            binding.primaryRepresentative.id -> {
                val popup = UserDataPopup(UserPopupType.VIEW_DATA)
                popup.setUser(viewModel.getAction().value!!.primaryRepresentative)
                popup.show(childFragmentManager, "")
            }
//            binding.secondaryRepresentative.id -> {
//                val popup = UserDataPopup(UserPopupType.VIEW_DATA)
//                popup.setUser(viewModel.getAction().value!!.secondaryRepresentative!!)
//                popup.show(childFragmentManager, "")
//            }
        }
    }
}
