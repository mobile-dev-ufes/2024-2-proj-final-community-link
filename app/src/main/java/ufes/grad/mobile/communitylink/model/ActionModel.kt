package ufes.grad.mobile.communitylink.model

import java.util.Date

class ActionModel (
    val id: String,
    val name: String,
    val descripton: String,
    val tags: List<String>,
    val initDate: Date,
    val finishDate: Date,
    val status: Boolean,
    val project: ProjectModel
) {
}
