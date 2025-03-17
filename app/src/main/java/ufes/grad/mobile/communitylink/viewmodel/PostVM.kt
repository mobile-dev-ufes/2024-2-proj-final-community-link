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

/**
 * ViewModel responsible for managing post data and operations such as fetching, updating, and creating new posts.
 * It interacts with the PostDAO to retrieve or modify post data.
 *
 * @param application The application context.
 */
class PostVM(application: Application) : AndroidViewModel(application) {

    private var post = MutableLiveData<PostModel>()

    /**
     * Retrieves the LiveData containing the current post.
     *
     * @return LiveData holding the post data.
     */
    fun post(): LiveData<PostModel> {
        return post
    }

    /**
     * Fetches the post by its ID and updates the LiveData with the post data.
     * If the postId is null, an empty post model is set.
     *
     * @param postId The ID of the post to fetch.
     */
    fun fetchPost(postId: String?) {
        if (postId == null) post.postValue(PostModel())
        else viewModelScope.launch { post.postValue(PostDAO.findById(postId)!!) }
    }

    /**
     * Updates an existing post in the database and provides feedback to the user based on the result.
     *
     * @param post The post data to update.
     * @return True if the update was successful, otherwise false.
     */
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

    /**
     * Creates a new post in the database and provides feedback to the user based on the result.
     *
     * @param post The new post data to be created.
     * @return True if the creation was successful, otherwise false.
     */
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
