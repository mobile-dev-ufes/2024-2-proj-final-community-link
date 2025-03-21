package ufes.grad.mobile.communitylink.data.model

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.dao.ActionDAO
import ufes.grad.mobile.communitylink.data.dao.DonationDAO
import ufes.grad.mobile.communitylink.data.dao.MemberDAO

@Serializable
class UserModel(
    override var id: String = "",
    var name: String = "",
    var email: String = "",
    var cpf: String = "",
    var sex: String = "",
    var dob: String = "",
    var address: String = "",
    var phone: String = "",
) : BaseModel {

    val primaryRepresentative: List<ActionModel>
        get() = runBlocking { ActionDAO.findByPrimaryRepresentativeId(id) }

    val memberTo: List<MemberModel>
        get() = runBlocking { MemberDAO.findByUserId(id) }

    val donations: List<DonationModel>
        get() = runBlocking { DonationDAO.findByUserId(id) }

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "email" to email,
            "cpf" to cpf,
            "sex" to sex,
            "dob" to dob,
            "address" to address,
            "phone" to phone
        )
    }
}
