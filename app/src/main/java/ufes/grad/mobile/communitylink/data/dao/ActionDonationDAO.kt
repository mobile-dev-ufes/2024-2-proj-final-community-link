package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ufes.grad.mobile.communitylink.data.model.DonationForActionModel

object ActionDonationDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("actionDonation")
    }

    suspend fun findById(id: String): ActionDonationDAO? {
        return findById(id, ActionDonationDAO::class.java)
    }

    suspend fun getDonations(id: String): MutableList<String>? {
        return try {
            val model = getCollection().document(id).get().await()
            model.get("donations") as? MutableList<String> ?: mutableListOf()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addDonation(id: String, donation: DonationForActionModel): Boolean {
        return try {
            if (!ActionDonationDAO.insert(donation)) return false

            val donations = getDonations(id)
            donations!!.add(donation.id)

            getCollection().document(id).update("donations", donations).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
