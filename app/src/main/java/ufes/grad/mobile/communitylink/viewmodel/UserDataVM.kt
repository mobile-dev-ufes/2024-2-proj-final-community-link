package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.MemberDAO
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.MemberModel
import ufes.grad.mobile.communitylink.data.model.UserModel

class UserDataVM(application: Application) : AndroidViewModel(application) {

    private var user = MutableLiveData<UserModel>()
    private var member = MutableLiveData<MemberModel>()

    fun getUser(): LiveData<UserModel> {
        return user
    }

    fun getMember(): LiveData<MemberModel> {
        return member
    }

    fun getUserData(id: String) {
        viewModelScope.launch { user.postValue(UserDAO.findById(id)!!) }
    }

    fun getMemberData(id: String) {
        viewModelScope.launch { member.postValue(MemberDAO.findById(id)!!) }
    }

    fun updateMemberStatus(responsible: Boolean) {
        member.value?.isResponsible = responsible
    }
}
