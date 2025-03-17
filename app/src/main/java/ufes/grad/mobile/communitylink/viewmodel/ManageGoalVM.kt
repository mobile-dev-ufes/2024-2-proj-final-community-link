package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.GoalDAO
import ufes.grad.mobile.communitylink.data.model.GoalModel
import ufes.grad.mobile.communitylink.utils.Utilities

class ManageGoalVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private var goalData = MutableLiveData<HashMap<String, String>>()

    fun goalData(): LiveData<HashMap<String, String>> {
        return goalData
    }

    fun createNewGoal(goal: GoalModel) {
        val context = getApplication<Application>().applicationContext
        try {
            viewModelScope.launch {
                if (GoalDAO.insert(goal)) Log.d("Firebase", "Ação criada com sucesso")
                else throw Exception("Erro ao criar ação de evento")
            }
        } catch (_: Exception) {
            Utilities.notify(context, context.getString(R.string.error_creating_action))
        }
    }

    fun getGoalData(id: String) {
        val context = getApplication<Application>().applicationContext
        if (auth.currentUser != null) {
            viewModelScope.launch {
                val goal = GoalDAO.findById(id)
                goal?.let {
                    goalData.postValue(Json.decodeFromJsonElement(Json.encodeToJsonElement(goal)))
                } ?: Utilities.notify(context, context.getString(R.string.user_has_no_data))
            }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }

    fun changeGoalData(goal: GoalModel) {
        val context = getApplication<Application>().applicationContext
        if (auth.currentUser != null) {
            viewModelScope.launch {
                val msg =
                    context.getString(
                        if (GoalDAO.update(goal)) R.string.success_updating_user_data
                        else R.string.erro_update_user_data
                    )
                Utilities.notify(context, msg)
            }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }
}
