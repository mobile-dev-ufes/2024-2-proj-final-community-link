package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer
import ufes.grad.mobile.communitylink.data.serializer.DonationSerializer
import ufes.grad.mobile.communitylink.data.serializer.MemberSerializer
import ufes.grad.mobile.communitylink.data.serializer.SlotRequestSerializer

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
    val primaryRepresentative:
        MutableList<@Serializable(with = ActionSerializer::class) ActionModel> =
        mutableListOf(),
    val secondaryRepresentative:
        MutableList<@Serializable(with = ActionSerializer::class) ActionModel> =
        mutableListOf(),
    val memberTo: MutableList<@Serializable(with = MemberSerializer::class) MemberModel> =
        mutableListOf(),
    val slotRequests:
        MutableList<@Serializable(with = SlotRequestSerializer::class) SlotRequestModel> =
        mutableListOf(),
    val donations: MutableList<@Serializable(with = DonationSerializer::class) DonationModel> =
        mutableListOf()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "email" to email,
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
