package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.DonationForActionModel
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object DonationForProjectDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("donationForProject")
    }

    override suspend fun findById(id: String): DonationForProjectModel? {
        return findById(id, JsonManager::decode)
    }

    suspend fun findByProjectId(id: String): List<DonationForProjectModel> {
        return try {
            val document = getCollection().whereEqualTo("project", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun findByUserId(id: String): List<DonationForProjectModel> {
        return try {
            val document = getCollection().whereEqualTo("user", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
