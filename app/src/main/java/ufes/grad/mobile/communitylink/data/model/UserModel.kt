package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable

@Serializable
class UserModel(
    override var id: String = "",
    val name: String = "",
    val email: String = "",
    val cpf: String = "",
    val sex: String = "",
    val dob: String = "",
    val address: String = "",
    val phone: String = "",
//    val primaryRepresentative: List<ActionModel>,
//    val secondaryRepresentative: List<ActionModel>,
//    val memberTo: List<MemberModel>,
//    val slotRequests: List<SlotRequestModel>,
//    val donations: List<DonationModel>
) : BaseModel {
}
