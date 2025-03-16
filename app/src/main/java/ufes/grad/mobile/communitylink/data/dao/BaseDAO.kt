package ufes.grad.mobile.communitylink.data.dao

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.BaseModel

abstract class BaseDAO {
    protected abstract fun getCollection(): CollectionReference

    open suspend fun insert(model: BaseModel): Boolean {
        return try {
            model.id = getCollection().add(model.toMap()).await().id
            update(model)
            true
        } catch (e: Exception) {
            false
        }
    }

    open suspend fun update(model: BaseModel): Boolean {
        return try {
            getCollection().document(model.id).set(model.toMap()).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    open suspend fun delete(model: BaseModel): Boolean {
        return try {
            getCollection().document(model.id).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    protected open suspend fun <T> findById(id: String, decode: (DocumentSnapshot) -> T): T? {
        return try {
            val document = getCollection().document(id).get().await()
            decode(document)
        } catch (e: Exception) {
            null
        }
    }

    abstract suspend fun findById(id: String): BaseModel?
}
