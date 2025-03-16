package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
class DonationForActionModel(
    override var id: String = "",
    override val value: Float = 0.0f,
    override val objectName: String = "",
    override val status: DonationStatusEnum = DonationStatusEnum.PENDING,
    override val date: String = "",
    override val confirmationImage: String = "",

    @Serializable(with = ActionSerializer::class)
    val action: ActionDonationModel = ActionDonationModel()
) : DonationModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "value" to value,
            "objectName" to objectName,
            "status" to status,
            "date" to date,
            "confirmationImage" to confirmationImage,
            "action" to action.id
        )
    }
}
