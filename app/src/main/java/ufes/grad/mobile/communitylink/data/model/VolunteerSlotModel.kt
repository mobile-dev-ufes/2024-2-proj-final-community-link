package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer
import ufes.grad.mobile.communitylink.data.serializer.SlotRequestSerializer

@Serializable
class VolunteerSlotModel(
    override var id: String = "",
    val position: String = "",
    val initDate: String = "",
    val finishDate: String = "",
    val place: String = "",
    val notes: String = "",
    val status: ParticipationStatusEnum = ParticipationStatusEnum.ON_TIME,

    @Serializable(with = ActionSerializer::class)
    val action: ActionEventModel = ActionEventModel(),

    @Serializable(with = SlotRequestSerializer::class)
    val filledBy: SlotRequestModel? = null,

    val slots: List<
        @Serializable(with = SlotRequestSerializer::class)
        SlotRequestModel
    > = emptyList()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "position" to position,
            "initDate" to initDate,
            "finishDate" to finishDate,
            "place" to place,
            "notes" to notes,
            "status" to status,
            "action" to action.id,
            "filledBy" to filledBy?.id,
            "slots" to slots.map { it.id }
        )
    }
}
