package ufes.grad.mobile.communitylink.model

import java.util.Date

class PostModel(
    val id: String,
    val text: String,
    val date: Date,
    val media: String,
    val action: ActionModel
) : BaseModel() {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "id" to id,
            "text" to text,
            "date" to date,
            "media" to media,
            "action" to action.id
        )
    }
}
