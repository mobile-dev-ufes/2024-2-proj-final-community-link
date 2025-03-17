package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for managing the list of projects associated with the current user. It
 * retrieves projects the user is involved in and whether the user is responsible for any project.
 *
 * @param application The application context.
 */
class MyProjectsVM(application: Application) : AndroidViewModel(application) {

    private var projects = MutableLiveData<List<ProjectModel>>()
    private val auth = FirebaseAuth.getInstance()
    private lateinit var responsibleProjects: List<String>

    /**
     * Retrieves the LiveData containing the list of projects associated with the user.
     *
     * @return LiveData holding the list of projects.
     */
    fun getProjects(): MutableLiveData<List<ProjectModel>> {
        return projects
    }

    /**
     * Fetches the list of projects the current user is associated with, including the projects they
     * are responsible for. Updates the LiveData with the results.
     */
    fun listProjects() {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                val user = UserDAO.findById(auth.currentUser!!.uid) ?: return@launch
                projects.postValue(user.memberTo.map { it.project })
                responsibleProjects =
                    user.memberTo.filter { it.isResponsible }.map { it.project.id }
            }
        } catch (e: Exception) {
            Utilities.notify(context, context.getString(R.string.an_error_occured))
        }
    }

    /**
     * Checks if the current user is responsible for a project with the given ID.
     *
     * @param id The ID of the project to check.
     * @return True if the user is responsible for the project, otherwise false.
     */
    fun isResponsible(id: String): Boolean {
        val context = getApplication<Application>().applicationContext
        return try {
            responsibleProjects.contains(id)
        } catch (e: Exception) {
            Utilities.notify(context, context.getString(R.string.an_error_occured))
            false
        }
    }
}
