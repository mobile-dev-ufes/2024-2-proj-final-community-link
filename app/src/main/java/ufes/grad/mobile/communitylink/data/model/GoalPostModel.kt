package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
class GoalPostModel(
    override var id: String = "",
    var type: GoalPostTypeEnum = GoalPostTypeEnum.NEW,
    var description: String = "",
    var amount: Float = 0.0f,
    var date: String = "",
    @Serializable(with = ActionSerializer::class)
    var actionDonation: ActionDonationModel = ActionDonationModel()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "type" to type,
            "description" to description,
            "amount" to amount,
            "date" to date,
            "actionDonation" to actionDonation.id
        )
    }
}
