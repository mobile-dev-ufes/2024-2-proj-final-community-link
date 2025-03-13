package ufes.grad.mobile.communitylink.model

import java.util.Date

class DonationForProjectModel(
    id: String,
    value: Float,
    objectName: String,
    status: DonationStatusEnum,
    date: Date,
    confirmationImage: String,
    val project: ProjectModel
) : DonationModel(id, value, objectName, status, date, confirmationImage) {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to id,
            "value" to value,
            "objectName" to objectName,
            "status" to status,
            "date" to date,
            "confirmationImage" to confirmationImage,
            "project" to project.id
        )
    }
}
