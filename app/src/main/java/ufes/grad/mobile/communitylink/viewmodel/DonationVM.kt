package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.DonationForProjectDAO
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for handling donation-related operations.
 * It manages the creation of new donations and provides user feedback.
 *
 * @param application The application context.
 */
class DonationVM(application: Application) : AndroidViewModel(application) {

    /**
     * Creates a new donation and inserts it into the database.
     * Displays a notification based on the operation's success.
     *
     * @param donation The donation data to be inserted.
     * @return Boolean indicating whether the operation was successful.
     */
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
