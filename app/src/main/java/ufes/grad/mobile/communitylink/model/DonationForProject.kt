package ufes.grad.mobile.communitylink.model

import android.media.Image

class DonationForProject (
    id: Int,
    value: Float,
    objectName: String,
    status: String,
    date: String,
    confirmationImage: Image
) : Donation(id, value, objectName, status, date, confirmationImage) {

}