package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class DonationListVM(application: Application) : AndroidViewModel(application) {

    private val dateFilter = MutableLiveData<String>()

    fun getDateFilter(): LiveData<String> {
        return dateFilter
    }

    fun showDatePicker() {
        val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val calendar = Calendar.getInstance()
        var year: Int
        var month: Int
        var day: Int
        if (dateFilter.value == "") {
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)
        } else {
            val startTime =
                LocalDateTime.parse(dateFilter.value, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            day = startTime.dayOfMonth
            month = startTime.monthValue
            year = startTime.year
        }

        val datePickerDialog =
            DatePickerDialog(
                getApplication<Application>().applicationContext,
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
