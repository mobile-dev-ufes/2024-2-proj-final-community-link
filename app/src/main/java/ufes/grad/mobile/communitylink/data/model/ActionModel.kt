package ufes.grad.mobile.communitylink.data.model

interface ActionModel : BaseModel {
    var name: String
    var description: String
    val tags: MutableList<String>
    var initDate: String
    var finishDate: String
    var status: Boolean
    val posts: MutableList<PostModel>
    var project: ProjectModel
    var primaryRepresentative: UserModel
    var secondaryRepresentative: UserModel?
}
