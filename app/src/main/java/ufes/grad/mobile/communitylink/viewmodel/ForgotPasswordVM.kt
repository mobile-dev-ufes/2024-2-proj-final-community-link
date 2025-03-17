package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for handling the process of sending a password reset email. It uses
 * Firebase Authentication to send the reset email and provides feedback to the user based on
 * success or failure.
 *
 * @param application The application context.
 */
class ForgotPasswordVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()

    /**
     * Sends a password reset email to the provided email address and notifies the user of the
     * result.
     *
     * @param email The email address to which the password reset email will be sent.
     */
    fun sendEmailCode(email: String) {
        val context = getApplication<Application>().applicationContext
        auth
            .sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Utilities.notify(context, context.getString(R.string.email_sent))
            }
            .addOnFailureListener {
                Utilities.notify(context, context.getString(R.string.verifique_email))
            }
    }
}
