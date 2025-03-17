package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.BaseModel

object ActionDAO : BaseDAO() {

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

    override suspend fun findById(id: String): ActionModel? {
        return ActionDonationDAO.findById(id) ?: ActionEventDAO.findById(id)
    }

    suspend fun findByProjectId(id: String): List<ActionModel> {
        return ActionDonationDAO.findByProjectId(id) + ActionEventDAO.findByProjectId(id)
    }

    suspend fun findByPrimaryRepresentativeId(id: String): List<ActionModel> {
        return ActionDonationDAO.findByPrimaryRepresentativeId(id) + ActionEventDAO.findByPrimaryRepresentativeId(id)
    }

    suspend fun findBySecondaryRepresentativeId(id: String): List<ActionModel> {
        return ActionDonationDAO.findBySecondaryRepresentativeId(id) + ActionEventDAO.findBySecondaryRepresentativeId(id)
    }
}
