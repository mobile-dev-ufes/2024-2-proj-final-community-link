package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.PostDAO
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.ActionDonationDAO

class DonationPageVM(application: Application) : AndroidViewModel(application) {
    private var donation = MutableLiveData<ActionDonationModel>()
    private var posts =  MutableLiveData<List<PostModel>>()

    fun getDonation(): MutableLiveData<ActionDonationModel> {
        return donation
    }

    fun getPosts(): MutableLiveData<List<PostModel>> {
        return posts
    }

    fun getDonationById(donationId: String) {
        viewModelScope.launch {
            donation.postValue(ActionDonationDAO.findById(donationId)!!)
        }
    }

    fun fetchPosts(postsIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<PostModel>()
            for (id in postsIds){
                list += PostDAO.findById(id)!!
            }
            posts.postValue(list)
        }
    }

    fun createNewPost(model: PostModel) {
        viewModelScope.launch{
            val context = getApplication<Application>().applicationContext
            if(PostDAO.insert(model)){
                Utilities.notify(context, context.getString(R.string.post_created))
            } else{
                Utilities.notify(context, context.getString(R.string.post_fail))
            }
        }
    }
}