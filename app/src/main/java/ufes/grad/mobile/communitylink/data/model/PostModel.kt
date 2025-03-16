package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
class PostModel(
    override var id: String = "",
    val text: String = "",
    val date: String = "",
    val media: String = "",

    @Serializable(with = ActionSerializer::class)
    val action: ActionModel = ActionDonationModel()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "text" to text,
            "date" to date,
            "media" to media,
            "action" to action.id
        )
    }
}
