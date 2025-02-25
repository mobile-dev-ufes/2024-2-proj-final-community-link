package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginVM(application: Application): AndroidViewModel(application) {
    private var auth = FirebaseAuth.getInstance()

    fun loginUser(email: String, password: String): Task<AuthResult?> {
        return auth.signInWithEmailAndPassword(email, password).addOnFailureListener {
            var msg = "Houve algum problema no login, tente novamente"
            if (it is FirebaseAuthInvalidUserException) {
                msg = "Email ou senha incorretos"
            } else if (it is FirebaseAuthInvalidCredentialsException) {
                msg = "Email ou senha incorretos"
            } else if (it is FirebaseNetworkException) {
                msg = "Falha ao conectar com a internet"
            }
            Toast.makeText(
                getApplication<Application>().applicationContext,
                msg, Toast.LENGTH_SHORT
            ).show()
        }
    }

}