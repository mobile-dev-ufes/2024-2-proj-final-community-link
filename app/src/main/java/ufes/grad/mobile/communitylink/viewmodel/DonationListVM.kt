package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class DonationListVM(application: Application) : AndroidViewModel(application) {

    private val dateFilter = MutableLiveData<String>()

    fun getDateFilter(): LiveData<String> {
        return dateFilter
    }

    fun showDatePicker(context: Context) {
        val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val calendar = Calendar.getInstance()
        var year: Int = calendar.get(Calendar.YEAR)
        var month: Int = calendar.get(Calendar.MONTH)
        var day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        if (dateFilter.value != null) {
            val startTime =
                LocalDate.parse(
                    dateFilter.value.toString(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                )
            day = startTime.dayOfMonth
            month = startTime.monthValue
            year = startTime.year
        }

        val datePickerDialog =
            DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(Calendar.YEAR, selectedYear)
                    selectedDate.set(Calendar.MONTH, selectedMonth)
                    selectedDate.set(Calendar.DAY_OF_MONTH, selectedDay)

                    dateFilter.value = formatDate.format(selectedDate.time)
                },
                year,
                month,
                day
            )

        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
    }
}
