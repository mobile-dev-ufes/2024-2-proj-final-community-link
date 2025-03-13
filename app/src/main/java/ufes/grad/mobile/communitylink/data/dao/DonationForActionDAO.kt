package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class DonationForActionDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("donationForAction")
    }

    suspend fun findById(id: String): DonationForActionDAO? {
        return findById(id, DonationForActionDAO::class.java)
    }
}
