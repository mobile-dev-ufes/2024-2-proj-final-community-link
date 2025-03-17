package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.DonationForProjectDAO
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel

/**
 * ViewModel responsible for managing the list of donations associated with projects. It retrieves
 * donation data from the database and updates the LiveData accordingly.
 *
 * @param application The application context.
 */
class DonationListVM(application: Application) : AndroidViewModel(application) {

    private var donations = MutableLiveData<List<DonationForProjectModel>>()

    /**
     * Retrieves the list of donations as LiveData.
     *
     * @return LiveData containing the list of donations.
     */
    fun getDonations(): LiveData<List<DonationForProjectModel>> {
        return donations
    }

    /**
     * Fetches donations by their IDs and updates LiveData.
     *
     * @param donationsIds A list of donation IDs to be retrieved.
     */
    fun fetchDonations(donationsIds: List<String>) {
        viewModelScope.launch {
            var list = mutableListOf<DonationForProjectModel>()
            for (id in donationsIds) list += DonationForProjectDAO.findById(id)!!
            donations.postValue(list)
        }
    }
}
