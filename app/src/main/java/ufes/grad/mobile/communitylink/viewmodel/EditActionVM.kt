package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class EditActionVM(application: Application) : AndroidViewModel(application) {

    private lateinit var startDate: MutableLiveData<String>
    private lateinit var endDate: MutableLiveData<String>
}
