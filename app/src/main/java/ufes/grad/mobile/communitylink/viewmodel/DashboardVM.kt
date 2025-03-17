package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionModel

/**
 * ViewModel responsible for managing actions displayed on the dashboard.
 * It retrieves user-related actions and updates the LiveData accordingly.
 *
 * @param application The application context.
 */
class DashboardVM(application: Application) : AndroidViewModel(application) {
    private var listActions = MutableLiveData<List<ActionModel>>()
    private val auth = FirebaseAuth.getInstance()

    /**
     * Retrieves the list of actions as LiveData.
     *
     * @return LiveData containing the list of actions.
     */
    fun getListActions(): MutableLiveData<List<ActionModel>> {
        return listActions
    }

    /**
     * Fetches actions related to the current user and updates LiveData.
     * It iterates over user memberships and collects associated actions.
     */
    fun setListActions() {
        viewModelScope.launch {
            var actionsList = listOf<ActionModel>()
            val user = UserDAO.findById(auth.currentUser!!.uid) ?: return@launch
            for (member in user.memberTo) {
                for (action in member.project.actions) {
                    Log.d("DEBUG", member.project.toMap().toString())
                    actionsList += action
                }
            }
            listActions.postValue(actionsList.sortedBy { it.initDate } )
        }
    }
}
