package ufes.grad.mobile.communitylink.data.serializer

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ufes.grad.mobile.communitylink.data.dao.BaseDAO
import ufes.grad.mobile.communitylink.data.model.BaseModel

open class BaseSerializer(val dao: BaseDAO) : KSerializer<BaseModel> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("BaseModel", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BaseModel) {
        throw IllegalStateException("Esta função não deve ser chamada neste ponto.")
    }

    override fun deserialize(decoder: Decoder): BaseModel {
        val id = decoder.decodeString()
        return runBlocking {
            dao.findById(id) ?: throw IllegalArgumentException("Objeto com ID $id não encontrado")
        }
    }
}
