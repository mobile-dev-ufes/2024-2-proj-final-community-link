package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.VolunteerSlotModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object VolunteerSlotDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("volunteerSlot")
    }

    override suspend fun findById(id: String): VolunteerSlotModel? {
        return findById(id, JsonManager::decode)
    }

    suspend fun findByActionEventId(id: String): List<VolunteerSlotModel> {
        return try {
            val document = getCollection().whereEqualTo("action", id).get().await()
            document.documents.mapNotNull { JsonManager.decode(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
