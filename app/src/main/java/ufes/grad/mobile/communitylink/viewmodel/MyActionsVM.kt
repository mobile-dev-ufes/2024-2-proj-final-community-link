package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.utils.Utilities

class MyActionsVM(application: Application) : AndroidViewModel(application) {

    private var events = MutableLiveData<List<ActionEventModel>>()
    private val auth = FirebaseAuth.getInstance()

    fun getEvents(): MutableLiveData<List<ActionEventModel>> {
        return events
    }

    fun listEvents() {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                val user = UserDAO.findById(auth.currentUser!!.uid) ?: return@launch
                events.postValue(user.primaryRepresentative.map { it as ActionEventModel })
            }
        } catch (e: Exception) {
            Utilities.notify(context, context.getString(R.string.an_error_occured))
        }
    }
}
