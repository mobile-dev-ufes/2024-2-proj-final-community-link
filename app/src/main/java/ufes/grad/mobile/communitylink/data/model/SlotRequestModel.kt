package ufes.grad.mobile.communitylink.data.model

import java.util.Date

class SlotRequestModel(
    val id: String,
    val date: Date,
    val isSelected: Boolean,
    val slot: VolunteerSlotModel,
    val user: UserModel
) {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to id,
            "date" to date,
            "isSelected" to isSelected,
            "slot" to slot.id,
            "user" to user.id
        )
    }
}
