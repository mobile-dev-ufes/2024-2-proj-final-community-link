package ufes.grad.mobile.communitylink.data.model

import java.util.Date
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionDonationSerializer

@Serializable
class DonationForActionModel(
    override var id: String,
    override val value: Float,
    override val objectName: String,
    override val status: DonationStatusEnum,
    @Contextual override val date: Date,
    override val confirmationImage: String,
    @Serializable(with = ActionDonationSerializer::class) val action: ActionDonationModel
) : DonationModel {}
