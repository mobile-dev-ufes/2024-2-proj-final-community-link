package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlin.collections.plus
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.PostDAO
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.utils.Utilities

class PostVM(application: Application) : AndroidViewModel(application) {

    private var post = MutableLiveData<PostModel>()

    fun post(): LiveData<PostModel> {
        return post
    }

    fun fetchPost(postId: String?) {
        if (postId == null) post.postValue(PostModel())
        else viewModelScope.launch { post.postValue(PostDAO.findById(postId)!!) }
    }

    fun updatePost(post: PostModel): Boolean {
        val context = getApplication<Application>().applicationContext
        var success = false
        viewModelScope.launch { success = PostDAO.update(post) }
        Utilities.notify(
            context,
            context.getString(if (success) R.string.sucesso else R.string.an_error_occured)
        )
        return success
    }

    fun createNewPost(post: PostModel): Boolean {
        val context = getApplication<Application>().applicationContext
        var success = false
        viewModelScope.launch { success = PostDAO.insert(post) }
        Utilities.notify(
            context,
            context.getString(if (success) R.string.sucesso else R.string.an_error_occured)
        )
        return success
    }
}
