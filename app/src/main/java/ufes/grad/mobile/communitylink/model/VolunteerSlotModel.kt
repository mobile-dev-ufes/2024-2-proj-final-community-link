package ufes.grad.mobile.communitylink.model

import java.util.Date

class VolunteerSlotModel(
    val id: String,
    val position: String,
    val initDate: Date,
    val finishDate: Date,
    val place: String,
    val notes: String,
    val status: ParticipationStatusEnum,
    val action: ActionEventModel,
    val slots: List<SlotRequestModel>
) {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to id,
            "position" to position,
            "initDate" to initDate,
            "finishDate" to finishDate,
            "place" to place,
            "notes" to notes,
            "status" to status,
            "action" to action.id,
            "slots" to slots.map { it.id }
        )
    }
}
