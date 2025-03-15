package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
data class GoalModel(
    override var id: String = "",
    val description: String = "",
    val minimalQuantity: Float = 0.0f,
    val actualQuantity: Float = 0.0f,
    @Serializable(with = ActionSerializer::class)
    private val actionDonation: ActionDonationModel = ActionDonationModel()
) : BaseModel {}
