package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.model.ActionEventModel

class EventPageVM(application: Application) : AndroidViewModel(application) {
    private var event = MutableLiveData<ActionEventModel>()

    fun getEvent(): MutableLiveData<ActionEventModel> {
        return event
    }

    fun getEventById(eventId: String) {
        viewModelScope.launch {
            Log.d("GET----------EVENT", eventId)
            event.postValue(ActionEventDAO.findById(eventId)!!)
        }
    }
}
