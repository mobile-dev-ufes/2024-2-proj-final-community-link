package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.DonationSerializer
import ufes.grad.mobile.communitylink.data.serializer.GoalSerializer
import ufes.grad.mobile.communitylink.data.serializer.PostSerializer
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer

@Serializable
class ActionDonationModel(
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

    val goals: List<
        @Serializable(with = GoalSerializer::class)
        GoalModel
    > = emptyList(),

    val donations: List<
        @Serializable(with = DonationSerializer::class)
        DonationForActionModel
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
            "goal" to goals.map { it.id },
            "donations" to donations.map { it.id }
        )
    }
}
