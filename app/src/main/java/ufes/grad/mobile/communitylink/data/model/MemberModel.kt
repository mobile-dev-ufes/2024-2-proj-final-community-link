package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer

@Serializable
class MemberModel(
    override var id: String = "",
    val isActive: Boolean = false,
    val initDate: String = "",
    val finishDate: String = "",
    val isResponsible: Boolean = false,

    @Serializable(with = UserSerializer::class)
    val user: UserModel = UserModel()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "isActive" to isActive,
            "initDate" to initDate,
            "finishDate" to finishDate,
            "isResponsible" to isResponsible,
            "user" to user.id
        )
    }
}
