package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.DonationForActionModel
import ufes.grad.mobile.communitylink.data.model.GoalModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object DonationForActionDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("donationForAction")
    }

    override suspend fun findById(id: String): DonationForActionModel? {
        return findById(id, JsonManager::decode)
    }

    suspend fun findByActionId(id: String): List<DonationForActionModel> {
        return try {
            val document = getCollection().whereEqualTo("action", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun findByUserId(id: String): List<DonationForActionModel> {
        return try {
            val document = getCollection().whereEqualTo("user", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
