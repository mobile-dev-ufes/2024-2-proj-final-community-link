package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
data class GoalModel(
    override var id: String = "",
    var description: String = "",
    var minimalQuantity: Float = 0.0f,
    var actualQuantity: Float = 0.0f,

    @Serializable(with = ActionSerializer::class)
    var actionDonation: ActionDonationModel = ActionDonationModel()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "description" to description,
            "minimalQuantity" to minimalQuantity,
            "actualQuantity" to actualQuantity,
            "actionDonation" to actionDonation.id
        )
    }
}
