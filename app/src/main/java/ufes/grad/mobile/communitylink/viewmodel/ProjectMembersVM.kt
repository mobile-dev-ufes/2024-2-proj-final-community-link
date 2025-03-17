package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.MemberDAO
import ufes.grad.mobile.communitylink.data.model.MemberModel

class ProjectMembersVM(application: Application) : AndroidViewModel(application) {

    private var common = MutableLiveData<List<MemberModel>>()
    private var responsible = MutableLiveData<List<MemberModel>>()

    fun getCommon(): LiveData<List<MemberModel>> {
        return common
    }

    fun getResponsible(): LiveData<List<MemberModel>> {
        return responsible
    }

    fun fetchCommonMembers(memberIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<MemberModel>()
            for (id in memberIds) list += MemberDAO.findById(id)!!
            common.postValue(list)
        }
    }

    fun fetchResponsibleMembers(memberIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<MemberModel>()
            for (id in memberIds) list += MemberDAO.findById(id)!!
            responsible.postValue(list)
        }
    }
}
