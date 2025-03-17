package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.MemberModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object MemberDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("member")
    }

    override suspend fun findById(id: String): MemberModel? {
        return findById(id, JsonManager::decode)
    }

    suspend fun findByProjectId(id: String): List<MemberModel> {
        return try {
            val document = getCollection().whereEqualTo("project", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun findByUserId(id: String): List<MemberModel> {
        return try {
            val document = getCollection().whereEqualTo("user", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
