package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.model.BaseModel

class ExploreVM(application: Application) : AndroidViewModel(application) {
    private var list = MutableLiveData<List<BaseModel>>()

    fun getList(): MutableLiveData<List<BaseModel>> {
        return list
    }

    fun search(searchKey: String){
        var listDocs = mutableListOf<BaseModel>()
        val ref = ActionEventDAO.getCollectionPublic()
        ref.whereEqualTo("name", searchKey).get().addOnSuccessListener{ documents ->
            for (document in documents) {
                runBlocking {
                    listDocs += ActionEventDAO.findById(document.id)!!
                }
            }
        }
        list.postValue(listDocs)
    }
}