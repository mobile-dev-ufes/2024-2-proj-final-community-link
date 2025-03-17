package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object UserDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("users")
    }

    fun getCollectionPublic(): CollectionReference {
        return getCollection()
    }

    override suspend fun insert(model: BaseModel): Boolean {
        return update(model)
    }

    override suspend fun findById(id: String): UserModel? {
        return findById(id, JsonManager::decode)
    }
}
