package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.dao.ProjectDataDAO
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.utils.Utilities

class EditProjectVM(application: Application) : AndroidViewModel(application) {

    private var project = MutableLiveData<ProjectModel>()

    fun getProject(): LiveData<ProjectModel> {
        return project
    }

    fun getProjectById(projId: String) {
        viewModelScope.launch { project.postValue(ProjectDAO.findById(projId)!!) }
    }

    fun updateData(data: ProjectDataModel) {
        val cont = getApplication<Application>().applicationContext
        viewModelScope.launch {
            Utilities.notify(
                cont,
                if (ProjectDataDAO.update(data)) cont.getString(R.string.sucesso)
                else cont.getString(R.string.an_error_occured)
            )
        }
    }
}
