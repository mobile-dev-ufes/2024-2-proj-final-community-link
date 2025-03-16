package ufes.grad.mobile.communitylink.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.utils.Utilities

class SignupVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()

    fun registerNewUser(email: String, password: String, user: UserModel): Task<AuthResult?> {
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
                    val app = getApplication<Application>()
                    try {
                        saveUserData(user)
                    } catch (e: Exception) {
                        Utilities.notify(
                            app.applicationContext,
                            app.getString(R.string.error_save_user_data)
                        )
                        throw Exception("Erro ao salvar dados no BD")
                    }
                    Utilities.notify(
                        app.applicationContext,
                        app.getString(R.string.cadastro_realizado)
                    )
                    // Vai pra tela de Login
                }
        }
        throw IllegalArgumentException("Email e Senha são necessários")
    }

    private fun saveUserData(user: UserModel) {
        val currUserId = auth.currentUser?.uid
        if (currUserId != null) {
            user.id = currUserId

            viewModelScope.launch {
                if (UserDAO.insert(user)) {
                    Log.d("Firebase", "Salvo com sucesso")
                } else {
                    Log.d("Firebase", "Problema ao salvar")
                    throw Exception("Erro ao criar usuario")
                }
            }
        }
    }
}
