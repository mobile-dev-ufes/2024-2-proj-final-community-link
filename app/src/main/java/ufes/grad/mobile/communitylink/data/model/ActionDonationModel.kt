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
    override var name: String = "",
    override var description: String = "",
    override val tags: MutableList<String> = mutableListOf(),
    override var initDate: String = "",
    override var finishDate: String = "",
    override var status: Boolean = false,
    override val posts: MutableList<@Serializable(with = PostSerializer::class) PostModel> = mutableListOf(),
    @Serializable(with = ProjectSerializer::class)
    override var project: ProjectModel = ProjectModel(),
    @Serializable(with = UserSerializer::class)
    override var primaryRepresentative: UserModel = UserModel(),
    @Serializable(with = UserSerializer::class)
    override var secondaryRepresentative: UserModel? = null,
    val goals: MutableList<@Serializable(with = GoalSerializer::class) GoalModel> = mutableListOf(),
    val donations: MutableList<@Serializable(with = DonationSerializer::class) DonationForActionModel> =
        mutableListOf()
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
