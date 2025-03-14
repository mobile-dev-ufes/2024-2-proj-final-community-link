package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.DonationModel

object DonationDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("default")
    }

    override suspend fun insert(model: BaseModel): Boolean {
        return false
    }

    override suspend fun update(model: BaseModel): Boolean {
        return false
    }

    override suspend fun delete(model: BaseModel): Boolean {
        return false
    }

    override suspend fun findById(id: String): DonationModel? {
        return DonationForActionDAO.findById(id) ?: DonationForProjectDAO.findById(id)
    }
}
