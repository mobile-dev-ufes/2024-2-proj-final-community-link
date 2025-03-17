package ufes.grad.mobile.communitylink.data.model

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.dao.SlotRequestDAO
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer
import ufes.grad.mobile.communitylink.data.serializer.SlotRequestSerializer

@Serializable
class VolunteerSlotModel(
    override var id: String = "",
    var position: String = "",
    var initDate: String = "",
    var finishDate: String = "",
    var place: String = "",
    var notes: String = "",
    var status: ParticipationStatusEnum = ParticipationStatusEnum.ON_TIME,

    @Serializable(with = ActionSerializer::class)
    var action: ActionEventModel = ActionEventModel(),

    @Serializable(with = SlotRequestSerializer::class)
    var filledBy: SlotRequestModel? = null,
) : BaseModel {

    val slots: List<SlotRequestModel>
        get() = runBlocking { SlotRequestDAO.findByVolunteerSlotId(id) }

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
            "filledBy" to filledBy?.id
        )
    }
}
