package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionDonationSerializer

@Serializable
data class GoalModel(
    override var id: String,
    val description: String,
    val minimalQuantity: Float,
    val actualQuantity: Float,
    @Serializable(with = ActionDonationSerializer::class)
    private val actionDonation: ActionDonationModel
) : BaseModel {
}
