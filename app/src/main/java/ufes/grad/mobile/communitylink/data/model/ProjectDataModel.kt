package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable

@Serializable
class ProjectDataModel(
    override var id: String = "",
    var name: String = "",
    var description: String = "",
    var tags: List<String> = emptyList(),
    var address: String = "",
    var CNPJ: String = "",
    var logo: String = "",
    var changeDate: String = "",
    var underReview: Boolean = false,
    var pixKey: String = "",
) : BaseModel {

    override fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "tags" to tags,
            "address" to address,
            "CNPJ" to CNPJ,
            "logo" to logo,
            "changeDate" to changeDate,
            "underReview" to underReview,
            "pixKey" to pixKey,
        )
    }
}
