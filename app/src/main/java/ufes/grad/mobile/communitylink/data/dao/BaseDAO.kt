package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.BaseModel

abstract class BaseDAO {
    abstract fun getCollection(): CollectionReference

    suspend fun insert(model: BaseModel): Boolean {
        return try {
            model.id = getCollection().add(model).await().id
            true
        } catch (e: Exception) { false }
    }

    suspend fun update(model: BaseModel): Boolean {
        return try {
            getCollection().document(model.id).set(model).await()
            true
        } catch (e: Exception) { false }
    }

    suspend fun delete(model: BaseModel): Boolean {
        return try {
            getCollection().document(model.id).delete().await()
            true
        } catch (e: Exception) { false }
    }

    protected suspend fun <T> findById(id: String, clazz: Class<T>): T? {
        return try {
            val model = getCollection().document(id).get().await()
            model.toObject(clazz)
        } catch (e: Exception) { null }
    }
}
