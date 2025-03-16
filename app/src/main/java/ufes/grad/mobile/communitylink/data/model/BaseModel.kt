package ufes.grad.mobile.communitylink.data.model

interface BaseModel {
    var id: String

    fun toMap(): Map<String, Any?>
}
