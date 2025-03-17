package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import kotlin.collections.plus
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.VolunteerSlotDAO
import ufes.grad.mobile.communitylink.data.model.VolunteerSlotModel
import ufes.grad.mobile.communitylink.utils.Utilities

class EditSlotVM(application: Application) : AndroidViewModel(application) {

    private val slot = MutableLiveData<VolunteerSlotModel?>()

    fun getSlot(): LiveData<VolunteerSlotModel?> {
        return slot
    }

    fun fetchSlot(id: String?) {
        if (id == null) slot.value = null
        else viewModelScope.launch { slot.value = VolunteerSlotDAO.findById(id)!! }
    }

    fun createSlot(slot: VolunteerSlotModel) {
        val cont = getApplication<Application>().applicationContext
        viewModelScope.launch {
            Utilities.notify(
                cont,
                if (VolunteerSlotDAO.insert(slot)) cont.getString(R.string.sucesso)
                else cont.getString(R.string.an_error_occured)
            )
        }
    }

    fun updateSlot(slot: VolunteerSlotModel) {
        val cont = getApplication<Application>().applicationContext
        viewModelScope.launch {
            Utilities.notify(
                cont,
                if (VolunteerSlotDAO.update(slot)) cont.getString(R.string.sucesso)
                else cont.getString(R.string.an_error_occured)
            )
        }
    }

    fun checkIfAfterStart(): Boolean {
        val startTime =
            LocalDateTime.parse(
                slot.value?.initDate,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            )
        return startTime <= LocalDateTime.now()
    }

    fun removeVolunteer() {
        slot.value?.filledBy = null
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
