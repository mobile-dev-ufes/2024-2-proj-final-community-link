package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.utils.Utilities

class ProfileVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private var user = MutableLiveData<UserModel?>()

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

    fun user(): LiveData<UserModel?> {
        return user
    }

    fun logout() {
        auth.signOut()
    }

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
