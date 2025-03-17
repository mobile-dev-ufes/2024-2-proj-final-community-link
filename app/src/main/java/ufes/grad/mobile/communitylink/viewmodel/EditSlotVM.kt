package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class EditSlotVM(application: Application) : AndroidViewModel(application) {

    private val startDate = MutableLiveData<String>()
    private val endDate = MutableLiveData<String>()

    fun getStartDate(): LiveData<String?> {
        return startDate
    }

    fun getEndDate(): LiveData<String?> {
        return endDate
    }

    fun changeStartDate() {
        val datePickerDialog =
            showDatePicker(startDate.value.toString(), { time -> startDate.value = time })
        val calendar = Calendar.getInstance()
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    fun changeEndDate() {
        val datePickerDialog =
            showDatePicker(endDate.value.toString(), { time -> endDate.value = time })
        val startTime =
            LocalDateTime.parse(
                startDate.value.toString(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            )
        datePickerDialog.datePicker.minDate = startTime.toEpochSecond(ZoneOffset.UTC)
        datePickerDialog.show()
    }

    fun showDatePicker(date: String?, onSelect: (String) -> Any?): DatePickerDialog {
        val formatDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        val calendar = Calendar.getInstance()
        var year: Int = calendar.get(Calendar.YEAR)
        var month: Int = calendar.get(Calendar.MONTH)
        var day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        var hour: Int = 0
        var minute: Int = 0

        if (date != null) {
            val startTime =
                LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            day = startTime.dayOfMonth
            month = startTime.monthValue
            year = startTime.year
            hour = startTime.hour
            minute = startTime.minute
        }

        val datePickerDialog =
            DatePickerDialog(
                getApplication<Application>().applicationContext,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(Calendar.YEAR, selectedYear)
                    selectedDate.set(Calendar.MONTH, selectedMonth)
                    selectedDate.set(Calendar.DAY_OF_MONTH, selectedDay)

                    // Time Picker
                    val timePicker =
                        TimePickerDialog(
                            getApplication<Application>().applicationContext,
                            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                                selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                selectedDate.set(Calendar.MINUTE, minute)
                                onSelect(formatDate.format(selectedDate.time))
                            },
                            hour,
                            minute,
                            true
                        )
                    timePicker.show()
                },
                year,
                month,
                day
            )

        return datePickerDialog
    }
}
