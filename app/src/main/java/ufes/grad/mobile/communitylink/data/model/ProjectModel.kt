package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer
import ufes.grad.mobile.communitylink.data.serializer.DonationSerializer
import ufes.grad.mobile.communitylink.data.serializer.MemberSerializer
import ufes.grad.mobile.communitylink.data.serializer.ProjectDataSerializer

@Serializable
class ProjectModel(
    override var id: String = "",
    var status: ProjectStatusEnum = ProjectStatusEnum.PENDING,

    @Serializable(with = ProjectDataSerializer::class)
    var currentData: ProjectDataModel = ProjectDataModel(),

    @Serializable(with = ProjectDataSerializer::class)
    var pendingData: ProjectDataModel? = null,

    val actions: MutableList<
        @Serializable(with = ActionSerializer::class)
        ActionModel
    > = mutableListOf(),

    val members: MutableList<
        @Serializable(with = MemberSerializer::class)
        MemberModel
    > = mutableListOf(),

    val donations: MutableList<
        @Serializable(with = DonationSerializer::class)
        DonationForProjectModel
    > = mutableListOf()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "status" to status,
            "currentData" to currentData.id,
            "pendingData" to pendingData?.id,
            "actions" to actions.map { it.id },
            "members" to members.map { it.id },
            "donations" to donations.map { it.id }
        )
    }
}
