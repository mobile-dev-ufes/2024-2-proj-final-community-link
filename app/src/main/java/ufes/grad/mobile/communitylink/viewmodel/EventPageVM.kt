package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.dao.PostDAO
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.PostModel

/**
 * ViewModel responsible for managing event details and associated posts. It fetches the event data
 * and posts related to a specific event.
 *
 * @param application The application context.
 */
class EventPageVM(application: Application) : AndroidViewModel(application) {

    private var event = MutableLiveData<ActionEventModel>()
    private var posts = MutableLiveData<List<PostModel>>()

    /**
     * Retrieves the LiveData containing the event details.
     *
     * @return LiveData holding the event details.
     */
    fun getEvent(): MutableLiveData<ActionEventModel> {
        return event
    }

    /**
     * Retrieves the LiveData containing the list of posts related to the event.
     *
     * @return LiveData holding the list of posts.
     */
    fun getPosts(): MutableLiveData<List<PostModel>> {
        return posts
    }

    /**
     * Fetches event details from the database using its ID and updates LiveData.
     *
     * @param eventId The unique identifier of the event.
     */
    fun getEventById(eventId: String) {
        viewModelScope.launch { event.postValue(ActionEventDAO.findById(eventId)!!) }
    }

    /**
     * Fetches posts related to the event using a list of post IDs and updates LiveData.
     *
     * @param postsIds List of unique post identifiers.
     */
    fun fetchPosts(postsIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<PostModel>()
            for (id in postsIds) list += PostDAO.findById(id)!!
            posts.postValue(list)
        }
    }
}
