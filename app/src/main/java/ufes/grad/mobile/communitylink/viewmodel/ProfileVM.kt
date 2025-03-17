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
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for managing user profile data. It provides functionality for fetching user
 * data, logging out, and updating user information.
 *
 * @param application The application context.
 */
class ProfileVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private var user = MutableLiveData<UserModel?>()

    /**
     * Fetches the current user's data and updates the LiveData with the user information. If the
     * user is not logged in, it shows an error message.
     */
    fun getUserData() {
        val context = getApplication<Application>().applicationContext
        Log.d("USERDATA", "GETTING USER DATA")
        if (auth.currentUser != null) {
            viewModelScope.launch {
                val u = UserDAO.findById(auth.currentUser!!.uid)
                Log.d("USERDATA", u!!.toMap().toString())
                user.postValue(u)
            }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }

    /**
     * Retrieves the LiveData containing the user's data.
     *
     * @return LiveData holding the user data.
     */
    fun user(): LiveData<UserModel?> {
        return user
    }

    /** Logs the user out by signing them out using Firebase Authentication. */
    fun logout() {
        auth.signOut()
    }

    /**
     * Updates the current user's data in the database and provides feedback to the user.
     *
     * @param user The updated user data to be saved.
     */
    fun changeUserData(user: UserModel) {
        val context = getApplication<Application>().applicationContext
        if (auth.currentUser != null) {
            viewModelScope.launch {
                user.id = auth.currentUser!!.uid
                if (UserDAO.update(user)) {
                    Utilities.notify(
                        context,
                        context.getString(R.string.success_updating_user_data)
                    )
                } else {
                    Utilities.notify(context, context.getString(R.string.erro_update_user_data))
                }
            }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }
}
