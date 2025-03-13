package ufes.grad.mobile.communitylink.data.model

import java.util.Date

interface DonationModel : BaseModel {
    val value: Float
    val objectName: String
    val status: DonationStatusEnum
    val date: Date
    val confirmationImage: String
}
