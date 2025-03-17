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

/**
 * ViewModel responsible for handling action editing operations.
 * It retrieves and updates action data while providing user feedback.
 *
 * @param application The application context.
 */
class EditActionVM(application: Application) : AndroidViewModel(application) {

    private var action = MutableLiveData<ActionModel>()

    /**
     * Retrieves the action data as LiveData.
     *
     * @return LiveData containing the action details.
     */
    fun getAction(): LiveData<ActionModel> {
        return action
    }

    /**
     * Fetches an action by its ID and updates LiveData.
     *
     * @param actionId The ID of the action to be retrieved.
     */
    fun fetchAction(actionId: String) {
        viewModelScope.launch { action.postValue(ActionEventDAO.findById(actionId)!!) }
    }

    /**
     * Updates an action in the database and notifies the user about the result.
     *
     * @param action The action model containing updated data.
     */
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
