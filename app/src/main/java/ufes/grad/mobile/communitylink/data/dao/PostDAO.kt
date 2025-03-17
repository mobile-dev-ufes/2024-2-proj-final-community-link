package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object PostDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("post")
    }

    override suspend fun findById(id: String): PostModel? {
        return findById(id, JsonManager::decode)
    }

    suspend fun findByActionId(id: String): List<PostModel> {
        return try {
            val document = getCollection().whereEqualTo("action", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
