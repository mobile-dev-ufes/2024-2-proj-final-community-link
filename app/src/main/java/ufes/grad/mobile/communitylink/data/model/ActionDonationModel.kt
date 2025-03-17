package ufes.grad.mobile.communitylink.data.model

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.dao.DonationForActionDAO
import ufes.grad.mobile.communitylink.data.dao.GoalDAO
import ufes.grad.mobile.communitylink.data.dao.PostDAO
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

    @Serializable(with = ProjectSerializer::class)
    override var project: ProjectModel = ProjectModel(),

    @Serializable(with = UserSerializer::class)
    override var primaryRepresentative: UserModel = UserModel(),

    @Serializable(with = UserSerializer::class)
    override var secondaryRepresentative: UserModel? = null,
) : ActionModel {

    val posts: List<PostModel>
        get() = runBlocking { PostDAO.findByActionId(id) }

    val goals: List<GoalModel>
        get() = runBlocking { GoalDAO.findByActionId(id) }

    val donations: List<DonationForActionModel>
        get() = runBlocking { DonationForActionDAO.findByActionId(id) }

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "tags" to tags,
            "initDate" to initDate,
            "finishDate" to finishDate,
            "status" to status,
            "project" to project.id,
            "primaryRepresentative" to primaryRepresentative.id,
            "secondaryRepresentative" to secondaryRepresentative?.id,
        )
    }
}
