package ufes.grad.mobile.communitylink.data.model

interface ActionModel : BaseModel {
    val name: String
    val description: String
    val tags: List<String>
    val initDate: String
    val finishDate: String
    val status: Boolean
    val posts: List<PostModel>
    val project: ProjectModel
    val primaryRepresentative: UserModel
    val secondaryRepresentative: UserModel?
}
