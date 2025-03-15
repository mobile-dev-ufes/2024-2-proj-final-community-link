package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.R

class ForgotPasswordVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()

    fun sendEmailCode(email: String) {
        val context = getApplication<Application>().applicationContext
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            Utilities.notify(context, context.getString(R.string.email_sent))
        }.addOnFailureListener {
            Utilities.notify(context, context.getString(R.string.verifique_email))
        }
    }
}