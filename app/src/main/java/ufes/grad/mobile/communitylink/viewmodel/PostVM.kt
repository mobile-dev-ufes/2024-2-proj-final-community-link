package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.PostDAO
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.utils.Utilities

class PostVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private var postData = MutableLiveData<HashMap<String, String>>()

    fun postData(): LiveData<HashMap<String, String>> {
        return postData
    }

    fun getPostData(id: String) {
        val context = getApplication<Application>().applicationContext
        if (auth.currentUser != null) {
            viewModelScope.launch {
                val post = PostDAO.findById(id)
                post?.let {
                    postData.postValue(Json.decodeFromJsonElement(Json.encodeToJsonElement(post)))
                } ?: Utilities.notify(context, context.getString(R.string.user_has_no_data))
            }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }

    fun changePostData(post: PostModel) {
        val context = getApplication<Application>().applicationContext
        if (auth.currentUser != null) {
            viewModelScope.launch {
                val msg =
                    context.getString(
                        if (PostDAO.update(post)) R.string.success_updating_user_data
                        else R.string.erro_update_user_data
                    )
                Utilities.notify(context, msg)
            }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }
}
