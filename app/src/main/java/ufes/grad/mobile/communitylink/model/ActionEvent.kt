package ufes.grad.mobile.communitylink.model

import java.util.Date

class ActionEvent (
    id: String,
    name: String,
    descripton: String,
    tags: List<String>,
    initDate: Date,
    finishDate: Date,
    status: Boolean,
    project: ProjectModel,
    var places: List<String>,
    var volunteerSlots: List<VolunteerSlot>
) : ActionModel(id, name, descripton, tags, initDate, finishDate, status, project) {
}