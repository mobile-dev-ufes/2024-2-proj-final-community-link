package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.UserModel

class UserDataVM(application: Application) : AndroidViewModel(application) {

    private var user = MutableLiveData<UserModel>()

    fun getUser(): LiveData<UserModel> {
        return user
    }

    fun getUserData(id: String) {
        viewModelScope.launch { user.postValue(UserDAO.findById(id)!!) }
    }
}
