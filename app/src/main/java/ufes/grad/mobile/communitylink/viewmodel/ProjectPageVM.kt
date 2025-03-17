package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.model.ProjectModel

class ProjectPageVM(application: Application) : AndroidViewModel(application) {

    private var project = MutableLiveData<ProjectModel>()

    fun getProject(): LiveData<ProjectModel> {
        return project
    }

    fun getProjectById(projId: String) {
        viewModelScope.launch { project.postValue(ProjectDAO.findById(projId)!!) }
    }
}
