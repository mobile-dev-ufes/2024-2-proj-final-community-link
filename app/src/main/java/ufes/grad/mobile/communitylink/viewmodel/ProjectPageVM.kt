package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ProjectModel

class ProjectPageVM (application: Application) : AndroidViewModel(application) {
    private var project = MutableLiveData<ProjectModel>()

    fun getProject(): LiveData<ProjectModel> {
        return project
    }

    fun getProjectById(projId: String) {
        viewModelScope.launch {
            project.postValue(StaticData.projects[0])
//            project.postValue(ProjectDAO.findById(projId)!!)
        }
    }

}