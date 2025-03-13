package ufes.grad.mobile.communitylink.model

import java.util.Date

class ActionEventModel(
    id: String,
    name: String,
    description: String,
    tags: List<String>,
    initDate: Date,
    finishDate: Date,
    status: Boolean,
    posts: List<PostModel>,
    project: ProjectModel,
    primaryRepresentative: UserModel,
    secondaryRepresentative: UserModel?,
    var places: List<String>,
    var volunteerSlots: List<VolunteerSlotModel>
) :
    ActionModel(
        id,
        name,
        description,
        tags,
        initDate,
        finishDate,
        status,
        posts,
        project,
        primaryRepresentative,
        secondaryRepresentative
    ) {

    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
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
