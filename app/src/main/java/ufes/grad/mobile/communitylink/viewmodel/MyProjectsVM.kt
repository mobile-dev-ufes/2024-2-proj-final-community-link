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

class MyProjectsVM(application: Application) : AndroidViewModel(application) {

    private var projects = MutableLiveData<List<ProjectModel>>()
    private val auth = FirebaseAuth.getInstance()

    fun getProjects(): MutableLiveData<List<ProjectModel>> {
        return projects
    }

    fun listProjects() {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                val user = UserDAO.findById(auth.currentUser!!.uid) ?: return@launch
                projects.postValue(user.memberTo.filter { it.isResponsible }.map { it.project })
            }
        } catch (e: Exception) {
            Utilities.notify(context, context.getString(R.string.an_error_occured))
        }
    }
}
