package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.model.ProjectModel

/**
 * ViewModel responsible for managing project data and retrieving project details.
 * It provides functionality to fetch a project by its ID.
 *
 * @param application The application context.
 */
class ProjectPageVM(application: Application) : AndroidViewModel(application) {

    private var project = MutableLiveData<ProjectModel>()

    /**
     * Retrieves the LiveData containing the project details.
     *
     * @return LiveData holding the project data.
     */
    fun getProject(): LiveData<ProjectModel> {
        return project
    }

    /**
     * Fetches the project details by its ID and updates the LiveData.
     *
     * @param projId The unique identifier of the project.
     */
    fun getProjectById(projId: String) {
        viewModelScope.launch { project.postValue(ProjectDAO.findById(projId)!!) }
    }
}
