package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.utils.Utilities

class EditActionVM(application: Application) : AndroidViewModel(application) {

    private var action = MutableLiveData<ActionModel>()

    fun getAction(): LiveData<ActionModel> {
        return action
    }

    fun fetchAction(actionId: String) {
        viewModelScope.launch { action.postValue(ActionEventDAO.findById(actionId)!!) }
    }

    fun updateAction(action: ActionModel) {
        val cont = getApplication<Application>().applicationContext
        viewModelScope.launch {
            Utilities.notify(
                cont,
                if (ActionEventDAO.update(action)) cont.getString(R.string.sucesso)
                else cont.getString(R.string.an_error_occured)
            )
        }
    }
}
