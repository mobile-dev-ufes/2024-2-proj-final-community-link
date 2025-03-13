package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object ProjectDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("project")
    }

    suspend fun findById(id: String): ProjectDAO? {
        return findById(id, ProjectDAO::class.java)
    }
}
