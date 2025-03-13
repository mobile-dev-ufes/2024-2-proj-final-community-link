package ufes.grad.mobile.communitylink.data.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ufes.grad.mobile.communitylink.data.dao.BaseDAO
import ufes.grad.mobile.communitylink.data.model.BaseModel

open class BaseSerializer(val dao: BaseDAO) : KSerializer<BaseModel> {
    override val descriptor: SerialDescriptor
        get() = TODO("Not yet implemented")

    override fun deserialize(decoder: Decoder): BaseModel {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: BaseModel) {
        TODO("Not yet implemented")
    }
}
