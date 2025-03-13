package ufes.grad.mobile.communitylink.model

class GoalModel(
    val id: String,
    val description: String,
    val minimalQuantity: Float,
    val actualQuantity: Float,
    val action: ActionDonationModel
) : BaseModel() {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to id,
            "description" to description,
            "minimalQuantity" to minimalQuantity,
            "actualQuantity" to actualQuantity,
            "action" to action.id
        )
    }
}
