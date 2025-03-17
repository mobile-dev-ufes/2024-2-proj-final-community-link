package ufes.grad.mobile.communitylink.data.model

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.dao.ActionDAO
import ufes.grad.mobile.communitylink.data.dao.DonationForProjectDAO
import ufes.grad.mobile.communitylink.data.dao.MemberDAO
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
) : BaseModel {

    val actions: List<ActionModel>
        get() = runBlocking { ActionDAO.findByProjectId(id) }

    val members: List<MemberModel>
        get() = runBlocking { MemberDAO.findByProjectId(id) }

    val donations: List<DonationForProjectModel>
        get() = runBlocking { DonationForProjectDAO.findByProjectId(id) }

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "status" to status,
            "currentData" to currentData.id
        )
    }
}
