package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class MakeDonationVM(application: Application) : AndroidViewModel(application) {

    fun copyToClipboard(text: String) {
        val clipboard: ClipboardManager =
            getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
    }
}
