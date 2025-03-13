package ufes.grad.mobile.communitylink.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ufes.grad.mobile.communitylink.data.serializer.ProjectSerializer
import ufes.grad.mobile.communitylink.data.serializer.UserSerializer
import java.util.Date

@Serializable
class ActionEventModel (
    override var id: String,
    override val name: String,
    override val description: String,
    override val tags: List<String>,
    @Contextual override val initDate: Date,
    @Contextual override val finishDate: Date,
    override val status: Boolean,
    @Serializable(with = ProjectSerializer::class) override val project: ProjectModel,
    @Serializable(with = UserSerializer::class) override val primaryRepresentative: UserModel,
    @Serializable(with = UserSerializer::class) override val secondaryRepresentative: UserModel?,
    val places: List<String>,
//    val volunteerSlots: List<VolunteerSlotModel>
) : ActionModel {
}
