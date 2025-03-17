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

class MakeDonationVM(application: Application) : AndroidViewModel(application) {

    fun createDonation(donation: DonationForProjectModel) {
        val context = getApplication<Application>().applicationContext
        var success = false
        viewModelScope.launch { success = DonationForProjectDAO.insert(donation) }
        Utilities.notify(
            context,
            context.getString(if (success) R.string.sucesso else R.string.an_error_occured)
        )
    }

    fun copyToClipboard(text: String) {
        val clipboard: ClipboardManager =
            getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
    }
}
