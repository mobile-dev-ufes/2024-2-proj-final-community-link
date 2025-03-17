package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer

@Serializable
class DonationForProjectModel(
    override var id: String = "",
    override var value: Float = 0.0f,
    override var objectName: String = "",
    override var status: DonationStatusEnum = DonationStatusEnum.PENDING,
    override var date: String = "",
    override var confirmationImage: String = "",

    @Serializable(with = UserSerializer::class)
    override var user: UserModel = UserModel(),

    @Serializable(with = ProjectSerializer::class)
    val project: ProjectModel = ProjectModel()
) : DonationModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "value" to value,
            "objectName" to objectName,
            "status" to status,
            "date" to date,
            "confirmationImage" to confirmationImage,
            "user" to user.id,
            "project" to project.id
        )
    }
}
