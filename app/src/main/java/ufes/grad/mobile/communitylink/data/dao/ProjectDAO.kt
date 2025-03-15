package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.ProjectModel

object ProjectDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("projects")
    }

    override suspend fun findById(id: String): ProjectModel? {
        return findById(id, ProjectModel::class.java)
    }
}
