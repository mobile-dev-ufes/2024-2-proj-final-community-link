package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Serializable

@Serializable
class ProjectDataModel(
    override var id: String = "",
    val name: String = "",
    val description: String = "",
    val tags: List<String> = emptyList(),
    val address: String = "",
    val CNPJ: String = "",
    val logo: String = "",
    val changeDate: String = "",
    val underReview: Boolean = false,
    val pixKey: String = "",
) : BaseModel {}
