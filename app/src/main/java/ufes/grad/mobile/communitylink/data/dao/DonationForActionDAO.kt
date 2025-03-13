package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.DonationForActionModel

class DonationForActionDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("donationForAction")
    }

    suspend fun findById(id: String): DonationForActionModel? {
        return findById(id, DonationForActionModel::class.java)
    }
}
