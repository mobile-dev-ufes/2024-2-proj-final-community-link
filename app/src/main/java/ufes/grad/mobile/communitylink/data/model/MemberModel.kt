package ufes.grad.mobile.communitylink.data.model

import java.util.Date

class MemberModel(
    override var id: String,
    val isActive: Boolean,
    val initDate: Date,
    val finishDate: Date,
    val isResponsible: Boolean,
    val user: UserModel,
    val project: ProjectModel
) : BaseModel {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
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
