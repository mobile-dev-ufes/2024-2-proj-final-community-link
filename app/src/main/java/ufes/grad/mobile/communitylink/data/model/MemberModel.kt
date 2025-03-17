package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer

@Serializable
class MemberModel(
    override var id: String = "",
    var isActive: Boolean = false,
    var initDate: String = "",
    var finishDate: String = "",
    var isResponsible: Boolean = false,

    @Serializable(with = UserSerializer::class)
    var user: UserModel = UserModel(),

    @Serializable(with = ProjectSerializer::class)
    var project: ProjectModel = ProjectModel()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "isActive" to isActive,
            "initDate" to initDate,
            "finishDate" to finishDate,
            "isResponsible" to isResponsible,
            "user" to user.id,
            "project" to project.id
        )
    }
}
