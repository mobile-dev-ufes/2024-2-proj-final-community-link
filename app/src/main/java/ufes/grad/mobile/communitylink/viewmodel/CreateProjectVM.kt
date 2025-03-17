package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import java.util.Date
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.MemberModel
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.ProjectStatusEnum
import ufes.grad.mobile.communitylink.data.service.ProjectService
import ufes.grad.mobile.communitylink.utils.Utilities

class CreateProjectVM(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createNewProject(data: ProjectDataModel) {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                val member =
                    MemberModel(
                        isActive = true,
                        initDate = Date().toString(),
                        isResponsible = true,
                        user = UserDAO.findById(auth.currentUser!!.uid)!!,
                    )
                val project = ProjectModel(status = ProjectStatusEnum.ACCEPTED)

                ProjectService.create(project)
            }
            Utilities.notify(context, context.getString(R.string.sucess_saving_project_data))
        } catch (_: Exception) {
            Utilities.notify(context, context.getString(R.string.error_save_project_data))
        }
    }
}
