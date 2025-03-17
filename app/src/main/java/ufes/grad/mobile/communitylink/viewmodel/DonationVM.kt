package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.DonationForProjectDAO
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.utils.Utilities

class DonationVM(application: Application) : AndroidViewModel(application) {

    fun createNewDonation(donation: DonationModel): Boolean {
        val context = getApplication<Application>().applicationContext
        var success = false
        viewModelScope.launch { success = DonationForProjectDAO.insert(donation) }
        Utilities.notify(
            context,
            context.getString(if (success) R.string.sucesso else R.string.an_error_occured)
        )
        return success
    }
}
