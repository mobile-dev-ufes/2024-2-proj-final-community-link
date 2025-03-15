package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer
import ufes.grad.mobile.communitylink.data.serializer.VolunteerSlotSerializer

@Serializable
class SlotRequestModel(
    override var id: String = "",
    val date: String,
    val isSelected: Boolean = false,
    @Serializable(with = VolunteerSlotSerializer::class)
    val slot: VolunteerSlotModel = VolunteerSlotModel(),
    @Serializable(with = UserSerializer::class) val user: UserModel = UserModel()
) : BaseModel {}
