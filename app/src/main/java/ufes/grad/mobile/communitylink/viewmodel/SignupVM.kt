package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignupVM(application: Application): AndroidViewModel(application)  {
    private var auth = FirebaseAuth.getInstance()

    fun registerNewUser(email: String, password: String): Task<AuthResult?> {
        var isSucess = false
        return auth.createUserWithEmailAndPassword(email, password).addOnFailureListener {
            var msg = "Houve algum problema no cadastro"
            if (it is FirebaseAuthWeakPasswordException) {
                msg = "Digite uma senha com no mínimo 6 caracteres"
            } else if (it is FirebaseAuthInvalidCredentialsException) {
                msg = "Digite um email válido"
            } else if (it is FirebaseAuthUserCollisionException) {
                msg = "Este e-mail já foi cadastrado"
            } else if (it is FirebaseNetworkException) {
                msg = "Falha ao conectar com a internet"
            }
            Toast.makeText(
                getApplication<Application>().applicationContext,
                msg, Toast.LENGTH_SHORT,
            ).show()
        }.addOnSuccessListener {
            Toast.makeText(
                getApplication<Application>().applicationContext,
                "Cadastro realizado com sucesso",
                Toast.LENGTH_SHORT,
                ).show()
            // Vai pra tela de Login
            isSucess = true
        }
    }
}