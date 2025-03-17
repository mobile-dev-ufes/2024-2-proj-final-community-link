package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for managing user login. It uses Firebase Authentication to sign in the
 * user with email and password, and provides feedback based on the login outcome.
 *
 * @param application The application context.
 */
class LoginVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()

    /**
     * Attempts to sign in a user with the provided email and password. Notifies the user with an
     * appropriate message based on the result.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return A Task containing the result of the login attempt.
     * @throws IllegalArgumentException If the email or password is blank.
     */
    fun loginUser(email: String, password: String): Task<AuthResult?> {
        val app = getApplication<Application>()
        if (email.isNotBlank() and password.isNotBlank()) {
            return auth.signInWithEmailAndPassword(email.trim(), password).addOnFailureListener {
                var msg = app.getString(R.string.login_error_0)
                if (it is FirebaseAuthInvalidUserException) {
                    msg = app.getString(R.string.login_error_1)
                } else if (it is FirebaseAuthInvalidCredentialsException) {
                    msg = app.getString(R.string.login_error_1)
                } else if (it is FirebaseNetworkException) {
                    msg = app.getString(R.string.internet_error)
                }
                Utilities.notify(getApplication<Application>(), msg)
                if (auth.currentUser?.isEmailVerified == false) {
                    auth.currentUser?.sendEmailVerification()
                }
            }
        }
        throw IllegalArgumentException("Email ou senha faltando")
    }

    /**
     * Checks if the user is logged in.
     *
     * @return True if a user is currently logged in, otherwise false.
     */
    fun userLogedIn(): Boolean {
        return auth.currentUser != null
    }
}
