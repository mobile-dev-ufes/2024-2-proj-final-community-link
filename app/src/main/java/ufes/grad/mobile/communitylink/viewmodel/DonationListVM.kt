package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.DonationForProjectDAO
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel

class DonationListVM(application: Application) : AndroidViewModel(application) {

    private var donations = MutableLiveData<List<DonationForProjectModel>>()

    fun getDonations(): LiveData<List<DonationForProjectModel>> {
        return donations
    }

    fun fetchDonations(donationsIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<DonationForProjectModel>()
            for (id in donationsIds) list += DonationForProjectDAO.findById(id)!!
            donations.postValue(list)
        }
    }
}
