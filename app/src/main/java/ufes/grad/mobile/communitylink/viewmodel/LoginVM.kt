package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.R

class LoginVM(application: Application): AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()

    fun loginUser(email: String, password: String): Task<AuthResult?> {
        val app = getApplication<Application>()
        if (email.isNotBlank() and password.isNotBlank()) {
            return auth.signInWithEmailAndPassword(email, password).addOnFailureListener {
                var msg = app.getString(R.string.login_error_0)
                if (it is FirebaseAuthInvalidUserException) {
                    msg = app.getString(R.string.login_error_1)
                } else if (it is FirebaseAuthInvalidCredentialsException) {
                    msg = app.getString(R.string.login_error_1)
                } else if (it is FirebaseNetworkException) {
                    msg = app.getString(R.string.internet_error)
                }
                Utilities.notify(getApplication<Application>(), msg)
                if (auth.currentUser?.isEmailVerified == false){
                    auth.currentUser?.sendEmailVerification()
                }
            }
        }
        throw IllegalArgumentException("Email ou senha faltando")
    }

    fun userLogedIn(): Boolean {
        return auth.currentUser != null
    }
}