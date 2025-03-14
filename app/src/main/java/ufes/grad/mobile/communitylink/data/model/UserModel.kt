package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer
import ufes.grad.mobile.communitylink.data.serializer.DonationSerializer
import ufes.grad.mobile.communitylink.data.serializer.MemberSerializer
import ufes.grad.mobile.communitylink.data.serializer.SlotRequestSerializer

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

    val primaryRepresentative: List<
        @Serializable(with = ActionSerializer::class)
        ActionModel
    > = emptyList(),

    val secondaryRepresentative: List<
        @Serializable(with = ActionSerializer::class)
        ActionModel
    > = emptyList(),

    val memberTo: List<
        @Serializable(with = MemberSerializer::class)
        MemberModel
    > = emptyList(),

    val slotRequests: List<
        @Serializable(with = SlotRequestSerializer::class)
        SlotRequestModel
    > = emptyList(),

    val donations: List<
        @Serializable(with = DonationSerializer::class)
        DonationModel
    > = emptyList()
) : BaseModel {}
