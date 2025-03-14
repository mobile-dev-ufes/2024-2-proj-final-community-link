package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer
import ufes.grad.mobile.communitylink.data.serializer.DonationSerializer
import ufes.grad.mobile.communitylink.data.serializer.MemberSerializer
import ufes.grad.mobile.communitylink.data.serializer.ProjectDataSerializer

@Serializable
class ProjectModel(
    override var id: String = "",
    val status: ProjectStatusEnum = ProjectStatusEnum.ACCEPTED,

    @Serializable(with = ProjectDataSerializer::class)
    val currentData: ProjectDataModel = ProjectDataModel(),

    @Serializable(with = ProjectDataSerializer::class)
    val pendingData: ProjectDataModel? = null,

    val actions: List<
        @Serializable(with = ActionSerializer::class)
        ActionModel
    > = emptyList(),

    val members: List<
        @Serializable(with = MemberSerializer::class)
        MemberModel
    > = emptyList(),

    val donations: List<
        @Serializable(with = DonationSerializer::class)
        DonationForProjectModel
    > = emptyList()
) : BaseModel {}
