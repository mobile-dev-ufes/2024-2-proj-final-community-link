package ufes.grad.mobile.communitylink.data.dao

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ufes.grad.mobile.communitylink.data.model.MemberModel
import ufes.grad.mobile.communitylink.data.serializer.JsonManager

object MemberDAO : BaseDAO() {

    override fun getCollection(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection("member")
    }

    override suspend fun findById(id: String): MemberModel? {
        return findById(id, JsonManager::decode)
    }
}
