package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.PostSerializer
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer
import ufes.grad.mobile.communitylink.data.serializer.VolunteerSlotSerializer

@Serializable
class ActionEventModel(
    override var id: String = "",
    override val name: String = "",
    override val description: String = "",
    override val tags: List<String> = emptyList(),
    override val initDate: String = "",
    override val finishDate: String = "",
    override val status: Boolean = false,

    override val posts: List<
        @Serializable(with = PostSerializer::class)
        PostModel
    > = emptyList(),

    @Serializable(with = ProjectSerializer::class)
    override val project: ProjectModel = ProjectModel(),

    @Serializable(with = UserSerializer::class)
    override val primaryRepresentative: UserModel = UserModel(),

    @Serializable(with = UserSerializer::class)
    override val secondaryRepresentative: UserModel? = null,

    val places: List<String> = emptyList(),

    val volunteerSlots: List<
        @Serializable(with = VolunteerSlotSerializer::class)
        VolunteerSlotModel
    > = emptyList()
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
