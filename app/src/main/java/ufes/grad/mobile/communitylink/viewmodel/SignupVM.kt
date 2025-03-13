package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.utils.Utilities

class SignupVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun registerNewUser(
        email: String,
        password: String,
        user: HashMap<String, String>
    ): Task<AuthResult?> {
        if (email.isNotBlank() and password.isNotBlank()) {
            return auth
                .createUserWithEmailAndPassword(email, password)
                .addOnFailureListener {
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
                            msg,
                            Toast.LENGTH_SHORT,
                        )
                        .show()
                }
                .addOnSuccessListener {
                    saveUserData(user)
                    val app = getApplication<Application>()
                    Utilities.notify(app, app.getString(R.string.cadastro_realizado))
                    // Vai pra tela de Login
                }
        }
        throw IllegalArgumentException("Email e Senha são necessários")
    }

    private fun saveUserData(userMap: HashMap<String, String>) {
        val currUserId = auth.currentUser?.uid
        if (currUserId != null) {
            db.collection("users")
                .document(currUserId)
                .set(userMap)
                .addOnSuccessListener { Log.d("Firebase", "Salvo com sucesso") }
                .addOnFailureListener {
                    Log.d("Firebase", "Problema ao salvar: ", it)
                    throw Exception("Erro ao criar usuario")
                }
        }
    }
}
