package ufes.grad.mobile.communitylink.data.serializer

import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

object JsonManager {

    val json = Json { ignoreUnknownKeys = true }

    inline fun <reified T> decode(document: DocumentSnapshot): T {
        val data: Map<String, JsonElement>? =
            document.data?.mapValues { entry ->
                when (val value = entry.value) {
                    is List<*> ->
                        JsonArray(value.filterIsInstance<String>().map { JsonPrimitive(it) })
                    else -> JsonPrimitive(value?.toString())
                }
            }
        return json.decodeFromString<T>(json.encodeToString(data))
    }
}
