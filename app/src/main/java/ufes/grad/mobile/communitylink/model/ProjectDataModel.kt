package ufes.grad.mobile.communitylink.model

import java.util.Date

class ProjectDataModel(
    val name: String,
    val description: String,
    val tags: List<String>,
    val address: String,
    val CNPJ: String,
    val logo: String,
    val changeDate: Date,
    val underReview: Boolean,
    val pixKey: String
) {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
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
