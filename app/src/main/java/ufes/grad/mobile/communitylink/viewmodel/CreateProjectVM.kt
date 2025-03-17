package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import java.util.Date
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.MemberDAO
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.dao.ProjectDataDAO
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.MemberModel
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.ProjectStatusEnum
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for handling the creation of new projects.
 * It manages authentication, project creation, and member association.
 *
 * @param application The application context.
 */
class CreateProjectVM(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Creates a new project and inserts it into the database.
     * Also associates the current user as a responsible member of the project.
     *
     * @param data The project data to be used for creation.
     */
    fun createNewProject(data: ProjectDataModel) {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                val project = ProjectModel(status = ProjectStatusEnum.ACCEPTED, currentData = data)
                val member =
                    MemberModel(
                        isActive = true,
                        initDate = Date().toString(),
                        isResponsible = true,
                        user = UserDAO.findById(auth.currentUser!!.uid)!!,
                        project = project
                    )

                project.status = ProjectStatusEnum.ACCEPTED
                if (
                    !ProjectDataDAO.insert(project.currentData) ||
                        !ProjectDAO.insert(project) ||
                        !MemberDAO.insert(member)
                )
                    throw Exception()
            }
            Utilities.notify(context, context.getString(R.string.sucess_saving_project_data))
        } catch (_: Exception) {
            Utilities.notify(context, context.getString(R.string.error_save_project_data))
        }
    }
}
