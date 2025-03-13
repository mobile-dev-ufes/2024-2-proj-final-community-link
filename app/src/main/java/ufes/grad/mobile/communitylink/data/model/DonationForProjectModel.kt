package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import java.util.Date

@Serializable
class DonationForProjectModel (
    override var id: String,
    override val value: Float,
    override val objectName: String,
    override val status: DonationStatusEnum,
    @Contextual override val date: Date,
    override val confirmationImage: String,
    @Serializable(with = ProjectSerializer::class) val project: ProjectModel
) : DonationModel {
}
