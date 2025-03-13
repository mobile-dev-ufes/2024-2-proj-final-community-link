package ufes.grad.mobile.communitylink.model

import java.util.Date

abstract class ActionModel(
    val id: String,
    val name: String,
    val description: String,
    val tags: List<String>,
    val initDate: Date,
    val finishDate: Date,
    val status: Boolean,
    val posts: List<PostModel>,
    val project: ProjectModel,
    val primaryRepresentative: UserModel,
    val secondaryRepresentative: UserModel?
) : BaseModel() {}
