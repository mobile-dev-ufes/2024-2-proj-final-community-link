package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
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
}
