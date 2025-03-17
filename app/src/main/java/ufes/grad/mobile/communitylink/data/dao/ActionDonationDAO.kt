package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object ActionDonationDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("actionDonation")
    }

    override suspend fun findById(id: String): ActionDonationModel? {
        return findById(id, JsonManager::decode)
    }

    suspend fun findByProjectId(id: String): List<ActionDonationModel> {
        return try {
            val document = getCollection().whereEqualTo("project", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun findByPrimaryRepresentativeId(id: String): List<ActionDonationModel> {
        return try {
            val document = getCollection().whereEqualTo("primaryRepresentative", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun findBySecondaryRepresentativeId(id: String): List<ActionDonationModel> {
        return try {
            val document = getCollection().whereEqualTo("secondaryRepresentative", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
