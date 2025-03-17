package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.ActionDonationDAO
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.R

class EditActionVM(application: Application) : AndroidViewModel(application) {

    private var action = MutableLiveData<ActionModel>()

    fun getAction(): LiveData<ActionModel> {
        return action
    }

    fun fetchAction(actionId: String, isEvent: Boolean) {
        viewModelScope.launch{
            if(isEvent) {
                action.postValue(ActionEventDAO.findById(actionId)!!)
            } else{
                action.postValue(ActionDonationDAO.findById(actionId)!!)
            }
        }
    }

    fun updateAction(action: ActionModel) {
        val cont = getApplication<Application>().applicationContext
        viewModelScope.launch{
            if(action is ActionEventModel) {
                if (ActionEventDAO.update(action)) {
                    Utilities.notify(cont, cont.getString(R.string.sucesso))
                }
                else {
                    Utilities.notify(cont, cont.getString(R.string.an_error_occured))
                }
            } else{
                if (ActionEventDAO.update(action)) {
                    Utilities.notify(cont, cont.getString(R.string.sucesso))
                }
                else{
                    Utilities.notify(cont, cont.getString(R.string.an_error_occured))
                }
            }
        }
    }
}
