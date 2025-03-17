package ufes.grad.mobile.communitylink.data.model

interface DonationModel : BaseModel {
    var value: Float
    var objectName: String
    var status: DonationStatusEnum
    var date: String
    var user: UserModel
}
