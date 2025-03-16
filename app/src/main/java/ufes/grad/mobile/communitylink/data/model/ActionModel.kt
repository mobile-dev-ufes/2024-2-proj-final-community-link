package ufes.grad.mobile.communitylink.data.model

interface ActionModel : BaseModel {
    var name: String
    var description: String
    var tags: List<String>
    var initDate: String
    var finishDate: String
    var status: Boolean
    var posts: List<PostModel>
    var project: ProjectModel
    var primaryRepresentative: UserModel
    var secondaryRepresentative: UserModel?
}
