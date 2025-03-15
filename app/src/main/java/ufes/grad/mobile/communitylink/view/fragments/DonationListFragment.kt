package ufes.grad.mobile.communitylink.view.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.Calendar
import kotlin.String
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.DonationStatusEnum
import ufes.grad.mobile.communitylink.databinding.FragmentDonationListBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.view.adapter.DonationCardAdapter

class DonationListFragment : Fragment(R.layout.fragment_donation_list), View.OnClickListener {

    private var _binding: FragmentDonationListBinding? = null
    private val binding
        get() = _binding!!

    private var date_filter: String? = null
    private var valid_dates: List<String> = listOf()
    private var status_filter: DonationStatusEnum? = null

    private var adapter: DonationCardAdapter = DonationCardAdapter()

    init {
        adapter.updateList(StaticData.donationActions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDonationListBinding.inflate(inflater, container, false)

        // TODO("Get dates from donation event data")
        // TODO("Make filters work")
        valid_dates = listOf<String>(getString(R.string.date), "12/03/2025", "13/03/2025")
        binding.dateFilter.setOnClickListener(this)
        setupFilters()

        adapter.onItemClickListener = { position -> }

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupFilters() {
        val status =
            listOf(
                getString(R.string.status),
                getString(R.string.all),
                getString(R.string.received),
                getString(R.string.pending)
            )
        binding.statusFilter.adapter = SpinnerAdapter(requireContext(), status)
        binding.statusFilter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    status_filter =
                        if (position > 1) DonationStatusEnum.entries.toTypedArray()[position - 2]
                        else null
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun showDatePicker(validDates: List<String>) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate =
                        String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)

                    if (validDates.contains(selectedDate)) {
                        Toast.makeText(
                                requireContext(),
                                "Data selecionada: $selectedDate",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    } else {
                        Toast.makeText(
                                requireContext(),
                                "Data inválida, escolha outra!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                },
                year,
                month,
                day
            )

        val datePicker = datePickerDialog.datePicker
        datePicker.setOnDateChangedListener { _, year, month, dayOfMonth ->
            val date = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)

            if (!validDates.contains(date)) {
                Toast.makeText(requireContext(), "Selecione um dia válido!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        datePickerDialog.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.dateFilter.id -> {
                showDatePicker(valid_dates)
            }
        }
    }
}
