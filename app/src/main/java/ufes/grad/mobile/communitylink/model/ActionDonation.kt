package ufes.grad.mobile.communitylink.model

import java.util.Date

class ActionDonation (
    id: String,
    name: String,
    descripton: String,
    tags: List<String>,
    initDate: Date,
    finishDate: Date,
    status: Boolean,
    project: ProjectModel,
    var goal: Goal,
    var listOfDonations: List<DonationForAction>
) : ActionModel(id, name, descripton, tags, initDate, finishDate, status, project) {

}