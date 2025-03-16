package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.DonationForActionDAO
import ufes.grad.mobile.communitylink.data.dao.DonationForProjectDAO
import ufes.grad.mobile.communitylink.data.model.DonationForActionModel
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.utils.Utilities

class DonationVM(application: Application) : AndroidViewModel(application) {
    fun createNewDonation(donation: DonationModel) {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                when (donation) {
                    is DonationForProjectModel -> {
                        if (DonationForProjectDAO.insert(donation)) {
                            Log.d("Firebase", "Doação criada com sucesso")
                        } else {
                            throw Exception("Erro ao realizar doação para projeto")
                        }
                    }
                    is DonationForActionModel -> {
                        if (DonationForActionDAO.insert(donation)) {
                            Log.d("Firebase", "Doação criada com sucesso")
                        } else {
                            throw Exception("Erro ao realizar doação para ação")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            var msg: String? = e.message
            if (msg == null) msg = "Não foi possível realizar esta ação no momento."
            Utilities.notify(context, msg)
        }
    }
}
