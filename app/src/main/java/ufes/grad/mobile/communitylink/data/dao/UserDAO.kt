package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.UserModel

object UserDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("users")
    }

    override suspend fun insert(model: BaseModel): Boolean {
        return update(model)
    }

    override suspend fun findById(id: String): UserModel? {
        return findById(id, UserModel::class.java)
    }
}
