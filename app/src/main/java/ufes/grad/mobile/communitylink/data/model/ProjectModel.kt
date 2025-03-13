package ufes.grad.mobile.communitylink.data.model

class ProjectModel(
    override var id: String,
    val status: ProjectStatusEnum,
    val currentData: ProjectDataModel,
    val pendingData: ProjectDataModel?,
    val actions: List<ActionModel>,
    val members: List<MemberModel>,
    val donations: List<DonationForProjectModel>
) : BaseModel {

    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
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
