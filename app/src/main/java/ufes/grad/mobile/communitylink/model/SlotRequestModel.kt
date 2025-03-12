package ufes.grad.mobile.communitylink.model

import java.util.Date

class SlotRequestModel(
    val id: String,
    val date: Date,
    val selected: Boolean,
    val slot: VolunteerSlotModel,
    val user: UserModel
) {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to id,
            "date" to date,
            "selected" to selected,
            "slot" to slot.id,
            "user" to user.id
        )
    }
}
