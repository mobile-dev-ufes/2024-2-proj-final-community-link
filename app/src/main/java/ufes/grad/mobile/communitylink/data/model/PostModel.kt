package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ActionSerializer

@Serializable
class PostModel(
    override var id: String = "",
    var text: String = "",
    var date: String = "",
    @Serializable(with = ActionSerializer::class) var action: ActionModel = ActionEventModel()
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf("id" to id, "text" to text, "date" to date, "action" to action.id)
    }
}
