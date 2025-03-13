package ufes.grad.mobile.communitylink.model

import java.util.Date

abstract class DonationModel(
    val id: String,
    val value: Float,
    val objectName: String,
    val status: DonationStatusEnum,
    val date: Date,
    val confirmationImage: String
) : BaseModel() {}
