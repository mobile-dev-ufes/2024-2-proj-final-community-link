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

class EventPageVM(application: Application) : AndroidViewModel(application) {

    private var event = MutableLiveData<ActionEventModel>()
    private var posts = MutableLiveData<List<PostModel>>()

    fun getEvent(): MutableLiveData<ActionEventModel> {
        return event
    }

    fun getPosts(): MutableLiveData<List<PostModel>> {
        return posts
    }

    fun getEventById(eventId: String) {
        viewModelScope.launch { event.postValue(ActionEventDAO.findById(eventId)!!) }
    }

    fun fetchPosts(postsIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<PostModel>()
            for (id in postsIds) list += PostDAO.findById(id)!!
            posts.postValue(list)
        }
    }
}
