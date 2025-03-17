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
import kotlinx.coroutines.runBlocking
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.utils.Utilities

/**
 * ViewModel responsible for handling user registration (sign-up) functionality. It allows users to
 * register with their email, password, and other user details.
 *
 * @param application The application context.
 */
class SignupVM(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()

    /**
     * Registers a new user by creating an account with the provided email and password. If
     * registration is successful, it saves the user data to the database. If any error occurs
     * during registration, an appropriate message is displayed.
     *
     * @param email The email address of the user.
     * @param password The password for the user account.
     * @param user The UserModel object containing the user's information to be saved.
     * @return A Task representing the result of the user registration operation.
     */
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

    /**
     * Saves the user data to the database after successful registration.
     *
     * @param user The UserModel object containing the user's details to be saved.
     */
    private fun saveUserData(user: UserModel) {
        val currUserId = auth.currentUser?.uid
        if (currUserId != null) {
            user.id = currUserId

            runBlocking {
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
