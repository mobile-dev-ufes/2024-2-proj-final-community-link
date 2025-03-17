package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.model.BaseModel

/**
 * ViewModel responsible for managing event search and display. It performs searches in the public
 * event collection based on the name and updates the list of found events.
 *
 * @param application The application context.
 */
class ExploreVM(application: Application) : AndroidViewModel(application) {
    private var list = MutableLiveData<List<BaseModel>>()

    /**
     * Retrieves the LiveData containing the list of found events.
     *
     * @return LiveData holding the list of events.
     */
    fun getList(): MutableLiveData<List<BaseModel>> {
        return list
    }

    /**
     * Searches for events in the public collection based on the name and updates the LiveData with
     * the results.
     *
     * @param searchKey The search key (event name) to be used for the search.
     */
    fun search(searchKey: String) {
        var listDocs = mutableListOf<BaseModel>()
        val ref = ActionEventDAO.getCollectionPublic()
        ref.whereEqualTo("name", searchKey).get().addOnSuccessListener { documents ->
            for (document in documents) {
                runBlocking { listDocs += ActionEventDAO.findById(document.id)!! }
            }
        }
        list.postValue(listDocs)
    }
}
