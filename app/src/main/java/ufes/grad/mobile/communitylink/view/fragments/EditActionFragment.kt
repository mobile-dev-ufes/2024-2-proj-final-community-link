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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import kotlin.getValue
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.databinding.FragmentEditActionBinding
import ufes.grad.mobile.communitylink.utils.Utilities
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
        viewModel.fetchAction(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditActionBinding.inflate(inflater, container, false)

        binding.confirmButton.setOnClickListener(this)
        binding.confirmButton.setOnClickListener(this)
        binding.startDateButton.setOnClickListener(this)
        binding.endDateButton.setOnClickListener(this)

        setObserver()

        return binding.root
    }

    fun setObserver() {
        viewModel
            .getAction()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.nameForms.editText.setText(it.name)
                    binding.descriptionForms.editText.setText(it.description)
                    binding.tagsForms.editText.setText(it.tags.joinToString(","))

                    binding.startDateButton.text = it.initDate
                    binding.endDateButton.text = it.finishDate

                    if (it is ActionEventModel) binding.placeForms.editText.setText(it.places)
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
                val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                val calendar = Calendar.getInstance()
                var year: Int = calendar.get(Calendar.YEAR)
                var month: Int = calendar.get(Calendar.MONTH)
                var day: Int = calendar.get(Calendar.DAY_OF_MONTH)
                if (binding.startDateButton.text != null) {
                    val startTime =
                        LocalDate.parse(
                            binding.startDateButton.text.toString(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        )
                    day = startTime.dayOfMonth
                    month = startTime.monthValue
                    year = startTime.year
                }

                val datePickerDialog =
                    DatePickerDialog(
                        requireContext(),
                        { _, selectedYear, selectedMonth, selectedDay ->
                            val selectedDate = Calendar.getInstance()
                            selectedDate.set(Calendar.YEAR, selectedYear)
                            selectedDate.set(Calendar.MONTH, selectedMonth)
                            selectedDate.set(Calendar.DAY_OF_MONTH, selectedDay)
                            binding.startDateButton.text = formatDate.format(selectedDate.time)
                        },
                        year,
                        month,
                        day
                    )

                if (binding.endDateButton.text != null) {
                    val startTime =
                        LocalDate.parse(
                            binding.endDateButton.text.toString(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        )
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(Calendar.YEAR, startTime.year)
                    selectedDate.set(Calendar.MONTH, startTime.monthValue)
                    selectedDate.set(Calendar.DAY_OF_MONTH, startTime.dayOfMonth)

                    datePickerDialog.datePicker.maxDate = selectedDate.timeInMillis
                    datePickerDialog.show()
                }

                datePickerDialog.datePicker.minDate = calendar.timeInMillis
                datePickerDialog.show()
            }
            binding.endDateButton.id -> {
                val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                val calendar = Calendar.getInstance()
                var year: Int = calendar.get(Calendar.YEAR)
                var month: Int = calendar.get(Calendar.MONTH)
                var day: Int = calendar.get(Calendar.DAY_OF_MONTH)
                if (binding.endDateButton.text != null) {
                    val startTime =
                        LocalDate.parse(
                            binding.endDateButton.text.toString(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        )
                    day = startTime.dayOfMonth
                    month = startTime.monthValue
                    year = startTime.year
                }

                val datePickerDialog =
                    DatePickerDialog(
                        requireContext(),
                        { _, selectedYear, selectedMonth, selectedDay ->
                            val selectedDate = Calendar.getInstance()
                            selectedDate.set(Calendar.YEAR, selectedYear)
                            selectedDate.set(Calendar.MONTH, selectedMonth)
                            selectedDate.set(Calendar.DAY_OF_MONTH, selectedDay)
                            binding.endDateButton.text = formatDate.format(selectedDate.time)
                        },
                        year,
                        month,
                        day
                    )

                var minDate = Calendar.getInstance()
                if (binding.startDateButton.text != null) {
                    val startTime =
                        LocalDate.parse(
                            binding.startDateButton.text.toString(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        )
                    minDate.set(Calendar.YEAR, startTime.year)
                    minDate.set(Calendar.MONTH, startTime.monthValue)
                    minDate.set(Calendar.DAY_OF_MONTH, startTime.dayOfMonth)
                }
                datePickerDialog.datePicker.minDate = minDate.timeInMillis
                datePickerDialog.show()
            }
            binding.confirmButton.id -> {
                val updatedAction: ActionModel
                val name = binding.nameForms.editText.text.toString().trim()
                val description = binding.descriptionForms.editText.text.toString().trim()
                val tags =
                    binding.tagsForms.editText.text.toString().trim().split(",").toMutableList()
                val initDate = binding.startDateButton.text.toString()
                val endDate = binding.endDateButton.text.toString()
                var places = binding.placeForms.editText.text.toString().trim()

                if (
                    name.isEmpty() ||
                        description.isEmpty() ||
                        tags.isEmpty() ||
                        initDate.isEmpty() ||
                        endDate.isEmpty() ||
                        places.isEmpty()
                ) {
                    Utilities.notify(requireContext(), getString(R.string.preencha_todos_os_campos))
                    return
                }

                updatedAction =
                    ActionEventModel(
                        id = viewModel.getAction().value!!.id,
                        name = name,
                        description = description,
                        tags = tags,
                        initDate = initDate,
                        finishDate = endDate,
                        places = places,
                        status = true,
                        project = viewModel.getAction().value!!.project,
                        primaryRepresentative = viewModel.getAction().value!!.primaryRepresentative,
                    )

                viewModel.updateAction(updatedAction)
            }
        }
    }
}
