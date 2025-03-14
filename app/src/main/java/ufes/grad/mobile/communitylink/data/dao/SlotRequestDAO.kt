package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.SlotRequestModel

object SlotRequestDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("slotRequest")
    }

    override suspend fun findById(id: String): SlotRequestModel? {
        return findById(id, SlotRequestModel::class.java)
    }
}
