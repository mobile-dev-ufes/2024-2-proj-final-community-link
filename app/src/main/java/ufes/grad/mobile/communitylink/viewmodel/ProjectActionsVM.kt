package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.model.ActionEventModel

class ProjectActionsVM(application: Application) : AndroidViewModel(application) {

    private var listActions = MutableLiveData<List<ActionEventModel>>()

    fun listActions(): LiveData<List<ActionEventModel>> {
        return listActions
    }

    fun setListActions(projectId: String) {
        viewModelScope.launch {
            var actionsList = listOf<ActionEventModel>()
            for (action in ProjectDAO.findById(projectId)!!.actions) actionsList +=
                (action as ActionEventModel)
            listActions.postValue(actionsList.sortedBy { it.initDate })
        }
    }
}
