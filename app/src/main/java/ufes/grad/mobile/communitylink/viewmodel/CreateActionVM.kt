package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.data.dao.ActionDonationDAO
import ufes.grad.mobile.communitylink.data.dao.ActionEventDAO
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.R

class CreateActionVM(application: Application) : AndroidViewModel(application)  {
    fun createNewAction(action: ActionModel) {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                when (action) {
                    is ActionEventModel -> {
                        if(ActionEventDAO.insert(action)) {
                            Log.d("Firebase","Ação criada com sucesso")
                        }
                        else {
                            throw Exception("Erro ao criar ação de evento")
                        }
                    }
                    is ActionDonationModel -> {
                        if(ActionDonationDAO.insert(action)) {
                            Log.d("Firebase","Ação criada com sucesso")
                        }else{
                            throw Exception("Erro ao criar ação de doacao")
                        }
                    }
                }
            }
        } catch (_: Exception) {
            Utilities.notify(context, context.getString(R.string.error_creating_action))
        }
    }
}