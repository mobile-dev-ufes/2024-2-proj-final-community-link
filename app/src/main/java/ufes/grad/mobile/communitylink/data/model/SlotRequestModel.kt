package ufes.grad.mobile.communitylink.data.model

import java.util.Date

class SlotRequestModel(
    override var id: String,
    val date: Date,
    val isSelected: Boolean,
    val slot: VolunteerSlotModel,
    val user: UserModel
) : BaseModel {

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
