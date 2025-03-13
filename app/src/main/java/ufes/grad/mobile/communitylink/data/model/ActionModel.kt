package ufes.grad.mobile.communitylink.data.model

import java.util.Date

interface ActionModel : BaseModel {
    val name: String
    val description: String
    val tags: List<String>
    val initDate: Date
    val finishDate: Date
    val status: Boolean
//    val posts: List<PostModel>
    val project: ProjectModel
    val primaryRepresentative: UserModel
    val secondaryRepresentative: UserModel?
}
