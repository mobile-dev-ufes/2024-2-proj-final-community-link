package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel

object DonationForProjectDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("donationForProject")
    }

    override suspend fun findById(id: String): DonationForProjectModel? {
        return findById(id, DonationForProjectModel::class.java)
    }
}
