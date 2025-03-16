package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.PostSerializer
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer
import ufes.grad.mobile.communitylink.data.serializer.VolunteerSlotSerializer

@Serializable
class ActionEventModel(
    override var id: String = "",
    override var name: String = "",
    override var description: String = "",
    override var tags: List<String> = emptyList(),
    override var initDate: String = "",
    override var finishDate: String = "",
    override var status: Boolean = false,
    override var posts: List<@Serializable(with = PostSerializer::class) PostModel> = emptyList(),
    @Serializable(with = ProjectSerializer::class)
    override var project: ProjectModel = ProjectModel(),
    @Serializable(with = UserSerializer::class)
    override var primaryRepresentative: UserModel = UserModel(),
    @Serializable(with = UserSerializer::class)
    override var secondaryRepresentative: UserModel? = null,
    var places: List<String> = emptyList(),
    var volunteerSlots:
        List<@Serializable(with = VolunteerSlotSerializer::class) VolunteerSlotModel> =
        emptyList()
) : ActionModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "tags" to tags,
            "initDate" to initDate,
            "finishDate" to finishDate,
            "status" to status,
            "posts" to posts.map { it.id },
            "project" to project.id,
            "primaryRepresentative" to primaryRepresentative.id,
            "secondaryRepresentative" to secondaryRepresentative?.id,
            "places" to places,
            "volunteerSlots" to volunteerSlots.map { it.id }
        )
    }
}
