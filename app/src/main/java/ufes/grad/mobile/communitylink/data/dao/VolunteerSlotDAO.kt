package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.VolunteerSlotModel

object VolunteerSlotDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("volunteerSlot")
    }

    override suspend fun findById(id: String): VolunteerSlotModel? {
        return findById(id, VolunteerSlotModel::class.java)
    }
}
