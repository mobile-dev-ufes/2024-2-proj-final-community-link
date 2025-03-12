package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.model.UserModel

class ProfileVM (application: Application): AndroidViewModel(application){
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private var userData = MutableLiveData<HashMap<String, String>>()

    fun getUserData() {
        val context = getApplication<Application>().applicationContext
        Log.d("USERDATA", "GETTING USER DATA")
        if (auth.currentUser != null) {
            db.collection("users").document(auth.currentUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        Log.d("USERDATA", "Document Retrieved: ${document.data}")

                        // Create a new HashMap and assign it to LiveData
                        val newUserData = hashMapOf(
                            "name" to (document.getString("name") ?: ""),
                            "cpf" to (document.getString("cpf") ?: ""),
                            "sex" to (document.getString("sex") ?: ""),
                            "dob" to (document.getString("dob") ?: ""),
                            "address" to (document.getString("address") ?: ""),
                            "phone" to (document.getString("phone") ?: ""),
                            "email" to (auth.currentUser?.email ?: "")
                        )
                        userData.postValue(newUserData)
                    } else {
                        Utilities.notify(context, context.getString(R.string.user_has_no_data))
                    }
                }
                .addOnFailureListener {
                    Utilities.notify(context, context.getString(R.string.erro_get_user_data))
                }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }

    fun userData(): LiveData<HashMap<String, String>> {
        return userData
    }

    fun logout() {
        auth.signOut()
    }

    fun changeUserData(user: UserModel) {
        val context = getApplication<Application>().applicationContext
        if (auth.currentUser != null) {
            val userMap = hashMapOf(
                "name" to user.name,
                "cpf" to user.cpf,
                "sex" to user.sex,
                "dob" to user.dob,
                "address" to user.address,
                "phone" to user.phone,
            )
            db.collection("users").document(auth.currentUser!!.uid)
                .set(userMap)
                .addOnSuccessListener {
                    Utilities.notify(context, context.getString(R.string.success_updating_user_data))
                }
                .addOnFailureListener {
                    Utilities.notify(context, context.getString(R.string.erro_update_user_data))
                }
        } else {
            Utilities.notify(context, context.getString(R.string.erro_user))
        }
    }
}