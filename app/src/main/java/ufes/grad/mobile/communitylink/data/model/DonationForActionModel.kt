package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
class DonationForActionModel(
    override var id: String = "",
    override var value: Float = 0.0f,
    override var objectName: String = "",
    override var status: DonationStatusEnum = DonationStatusEnum.PENDING,
    override var date: String = "",
    override var confirmationImage: String = "",
    @Serializable(with = ActionSerializer::class)
    var action: ActionDonationModel = ActionDonationModel()
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
