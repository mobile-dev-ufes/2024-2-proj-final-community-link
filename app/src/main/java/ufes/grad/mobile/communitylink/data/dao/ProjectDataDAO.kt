package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel

object ProjectDataDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("projectData")
    }

    override suspend fun findById(id: String): ProjectDataModel? {
        return findById(id, ProjectDataModel::class.java)
    }
}
