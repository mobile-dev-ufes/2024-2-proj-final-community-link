package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.DonationForProjectDAO
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for managing donations for a project.
 * It allows creating donations and copying donation details to the clipboard.
 *
 * @param application The application context.
 */
class MakeDonationVM(application: Application) : AndroidViewModel(application) {

    /**
     * Creates a donation for a project and provides feedback to the user based on the result.
     *
     * @param donation The donation details to be saved.
     */
    fun createDonation(donation: DonationForProjectModel) {
        val context = getApplication<Application>().applicationContext
        var success = false
        viewModelScope.launch { success = DonationForProjectDAO.insert(donation) }
        Utilities.notify(
            context,
            context.getString(if (success) R.string.sucesso else R.string.an_error_occured)
        )
    }

    /**
     * Copies the provided text to the clipboard.
     *
     * @param text The text to be copied to the clipboard.
     */
    fun copyToClipboard(text: String) {
        val clipboard: ClipboardManager =
            getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
    }
}
