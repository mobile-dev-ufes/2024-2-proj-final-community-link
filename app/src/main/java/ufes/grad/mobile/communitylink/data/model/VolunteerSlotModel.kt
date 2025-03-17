package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer

@Serializable
class VolunteerSlotModel(
    override var id: String = "",
    var position: String = "",
    var initDate: String = "",
    var finishDate: String = "",
    var place: String = "",
    var notes: String = "",
    @Serializable(with = ActionSerializer::class) var action: ActionEventModel = ActionEventModel(),
    @Serializable(with = UserSerializer::class) var filledBy: UserModel? = null,
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "position" to position,
            "initDate" to initDate,
            "finishDate" to finishDate,
            "place" to place,
            "notes" to notes,
            "action" to action.id,
            "filledBy" to filledBy?.id
        )
    }
}
