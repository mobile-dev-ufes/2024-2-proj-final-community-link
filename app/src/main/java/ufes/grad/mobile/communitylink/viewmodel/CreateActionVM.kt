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
import ufes.grad.mobile.communitylink.data.dao.ActionDonationDAO
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.data.service.ProjectService
import ufes.grad.mobile.communitylink.utils.Utilities

class CreateActionVM(application: Application) : AndroidViewModel(application) {

    private var project = MutableLiveData<ProjectModel?>()
    private var user = MutableLiveData<UserModel?>()
    private var auth = FirebaseAuth.getInstance()

    fun getProject(): LiveData<ProjectModel?> {
        return project
    }

    fun getUser(): LiveData<UserModel?> {
        return user
    }

    fun createNewAction(action: ActionModel) {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                when (action) {
                    is ActionEventModel -> {
                        if (ActionEventDAO.insert(action)) {
                            Log.d("Firebase", "Ação criada com sucesso")
                        } else {
                            throw Exception("Erro ao criar ação de evento")
                        }
                    }
                    is ActionDonationModel -> {
                        if (ActionDonationDAO.insert(action)) {
                            Log.d("Firebase", "Ação criada com sucesso")
                        } else {
                            throw Exception("Erro ao criar ação de doacao")
                        }
                    }
                }
                Utilities.notify(context, context.getString(R.string.sucess_saving_action))
            }
        } catch (_: Exception) {
            Utilities.notify(context, context.getString(R.string.error_creating_action))
        }
    }

    fun getProjectById(projId: String) {
        viewModelScope.launch { project.postValue(ProjectDAO.findById(projId)) }
    }

    fun fetchUser() {
        viewModelScope.launch { user.postValue(UserDAO.findById(auth.currentUser!!.uid)!!) }
    }
}
