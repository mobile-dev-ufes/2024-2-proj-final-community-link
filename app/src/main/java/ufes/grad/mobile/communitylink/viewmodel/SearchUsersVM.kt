package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.UserModel

class SearchUsersVM(application: Application) : AndroidViewModel(application) {

    private var list = MutableLiveData<List<UserModel>>()

    fun getList(): MutableLiveData<List<UserModel>> {
        return list
    }

    fun search(searchKey: String) {
        var listDocs = mutableListOf<UserModel>()
        val ref = UserDAO.getCollectionPublic()
        ref.whereEqualTo("name", searchKey).get().addOnSuccessListener { documents ->
            for (document in documents) {
                runBlocking { listDocs += UserDAO.findById(document.id)!! }
            }
        }
        list.postValue(listDocs)
    }
}
