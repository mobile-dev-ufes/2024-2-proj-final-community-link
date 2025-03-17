package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer
import ufes.grad.mobile.communitylink.data.serializer.VolunteerSlotSerializer

@Serializable
class SlotRequestModel(
    override var id: String = "",
    var date: String,
    var isSelected: Boolean = false,

    @Serializable(with = VolunteerSlotSerializer::class)
    var slot: VolunteerSlotModel = VolunteerSlotModel(),

    @Serializable(with = UserSerializer::class)
    var user: UserModel = UserModel()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "date" to date,
            "isSelected" to isSelected,
            "slot" to slot.id,
            "user" to user.id
        )
    }
}
