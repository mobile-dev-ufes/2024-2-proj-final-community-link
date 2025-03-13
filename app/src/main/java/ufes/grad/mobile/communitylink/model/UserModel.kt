package ufes.grad.mobile.communitylink.model

import java.util.Date

class UserModel(
    val id: String,
    val name: String,
    val cpf: String,
    val sex: String,
    val dob: Date,
    val address: String,
    val phone: String,
    val primaryRepresentative: List<ActionModel>,
    val secondaryRepresentative: List<ActionModel>,
    val memberTo: List<MemberModel>,
    val slotRequests: List<SlotRequestModel>,
    val donations: List<DonationModel>
) : BaseModel() {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to id,
            "name" to name,
            "cpf" to cpf,
            "sex" to sex,
            "dob" to dob,
            "address" to address,
            "phone" to phone,
            "primaryRepresentative" to primaryRepresentative.map { it.id },
            "secondaryRepresentative" to secondaryRepresentative.map { it.id },
            "memberTo" to memberTo.map { it.id },
            "slotRequests" to slotRequests.map { it.id },
            "donations" to donations.map { it.id }
        )
    }
}
