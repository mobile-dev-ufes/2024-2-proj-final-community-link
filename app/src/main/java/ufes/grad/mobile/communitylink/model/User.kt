package ufes.grad.mobile.communitylink.model

class User(
    val name: String,
    val cpf: String,
    val sex: String,
    val dob: String,
    val address: String,
    val phone: String
) {

    init {
        require(name.isNotBlank()) { "Name cannot be empty." }
        require(cpf.isNotBlank()) { "CPF cannot be empty." }
        require(sex.isNotBlank()) { "Sex cannot be empty." }
        require(dob.isNotBlank()) { "Date of Birth cannot be empty." }
        require(address.isNotBlank()) { "Address cannot be empty." }
        require(phone.isNotBlank()) { "Phone cannot be empty." }
    }

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "name" to name,
            "cpf" to cpf,
            "sex" to sex,
            "dob" to dob,
            "address" to address,
            "phone" to phone,
        )
    }
}
