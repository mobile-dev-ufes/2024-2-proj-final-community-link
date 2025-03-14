package ufes.grad.mobile.communitylink.data.model

interface DonationModel : BaseModel {
    val value: Float
    val objectName: String
    val status: DonationStatusEnum
    val date: String
    val confirmationImage: String
}
