package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
class GoalPostModel(
    override var id: String = "",
    val type: GoalPostTypeEnum = GoalPostTypeEnum.NEW,
    val description: String = "",
    val amount: Float = 0.0f,
    val date: String = "",

    @Serializable(with = ActionSerializer::class)
    val actionDonation: ActionDonationModel = ActionDonationModel()
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
