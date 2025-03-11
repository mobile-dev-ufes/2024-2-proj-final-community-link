package ufes.grad.mobile.communitylink.model

class ProjectModel(
    val status: ProjectStatusEnum,
    val projectDataList: List<ProjectDataModel>
) {
    val projectData: ProjectDataModel = projectDataList[0]

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "status" to status,
            "projectDataList" to projectDataList.forEach { it.toHashMap() },
        )
    }
}
