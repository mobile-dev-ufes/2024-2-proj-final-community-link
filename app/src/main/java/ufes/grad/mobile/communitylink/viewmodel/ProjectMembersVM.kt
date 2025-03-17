package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.MemberDAO
import ufes.grad.mobile.communitylink.data.model.MemberModel

/**
 * ViewModel responsible for managing project members' data.
 * It provides functionality for fetching common members and responsible members of a project.
 *
 * @param application The application context.
 */
class ProjectMembersVM(application: Application) : AndroidViewModel(application) {

    private var common = MutableLiveData<List<MemberModel>>()
    private var responsible = MutableLiveData<List<MemberModel>>()

    /**
     * Retrieves the LiveData containing the list of common members of a project.
     *
     * @return LiveData holding the list of common members.
     */
    fun getCommon(): LiveData<List<MemberModel>> {
        return common
    }

    /**
     * Retrieves the LiveData containing the list of responsible members of a project.
     *
     * @return LiveData holding the list of responsible members.
     */
    fun getResponsible(): LiveData<List<MemberModel>> {
        return responsible
    }

    /**
     * Fetches the common members of a project based on a list of member IDs and updates the LiveData.
     *
     * @param memberIds List of unique member identifiers.
     */
    fun fetchCommonMembers(memberIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<MemberModel>()
            for (id in memberIds) list += MemberDAO.findById(id)!!
            common.postValue(list)
        }
    }

    /**
     * Fetches the responsible members of a project based on a list of member IDs and updates the LiveData.
     *
     * @param memberIds List of unique member identifiers.
     */
    fun fetchResponsibleMembers(memberIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<MemberModel>()
            for (id in memberIds) list += MemberDAO.findById(id)!!
            responsible.postValue(list)
        }
    }
}
