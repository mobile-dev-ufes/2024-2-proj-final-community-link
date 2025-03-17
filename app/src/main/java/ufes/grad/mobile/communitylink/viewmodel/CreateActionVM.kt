package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for handling the creation of actions within a project. Manages user and
 * project data and interacts with the Firebase database.
 *
 * @param application The application context.
 */
class CreateActionVM(application: Application) : AndroidViewModel(application) {

    private var project = MutableLiveData<ProjectModel?>()
    private var user = MutableLiveData<UserModel?>()
    private var auth = FirebaseAuth.getInstance()

    /**
     * Retrieves the current project as LiveData.
     *
     * @return LiveData containing the current project.
     */
    fun getProject(): LiveData<ProjectModel?> {
        return project
    }

    /**
     * Retrieves the current user as LiveData.
     *
     * @return LiveData containing the current user.
     */
    fun getUser(): LiveData<UserModel?> {
        return user
    }

    /**
     * Creates a new action event and inserts it into the database. Notifies the user of success or
     * failure.
     *
     * @param action The action event to be created.
     */
    fun createNewAction(action: ActionModel) {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                if (ActionEventDAO.insert(action)) Log.d("Firebase", "Ação criada com sucesso")
                else throw Exception("Erro ao criar ação de evento")

                getProject()
                if (project.value == null) throw Exception("Criou ação mas sem projeto")

                Utilities.notify(context, context.getString(R.string.sucess_saving_action))
            }
        } catch (_: Exception) {
            Utilities.notify(context, context.getString(R.string.error_creating_action))
        }
    }

    /**
     * Fetches a project by its ID and updates LiveData.
     *
     * @param projId The ID of the project to be retrieved.
     */
    fun getProjectById(projId: String) {
        viewModelScope.launch { project.postValue(ProjectDAO.findById(projId)) }
    }

    /** Fetches the currently authenticated user and updates LiveData. */
    fun fetchUser() {
        viewModelScope.launch { user.postValue(UserDAO.findById(auth.currentUser!!.uid)!!) }
    }
}
