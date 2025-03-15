package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer

@Serializable
class DonationForProjectModel(
    override var id: String = "",
    override val value: Float = 0.0f,
    override val objectName: String = "",
    override val status: DonationStatusEnum = DonationStatusEnum.PENDING,
    override val date: String = "",
    override val confirmationImage: String = "",
    @Serializable(with = ProjectSerializer::class) val project: ProjectModel = ProjectModel()
) : DonationModel {}
