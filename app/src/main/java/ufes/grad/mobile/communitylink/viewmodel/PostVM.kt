package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostVM(application: Application) : AndroidViewModel(application) {

    private var image = MutableLiveData<Uri?>()

    fun getImage(): LiveData<Uri?> {
        return image
    }

    fun setImage(img: Uri?) {
        image.value = img
    }
}
