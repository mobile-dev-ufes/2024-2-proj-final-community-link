package ufes.grad.mobile.communitylink.view.viewHolder

import android.graphics.drawable.Drawable
import android.view.View.GONE
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.MemberModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.databinding.LayoutCommonCardBinding

class ListCommonCardViewHolder(
    private val binding_: LayoutCommonCardBinding,
    private val onItemClickListener: (Int) -> Unit
) : GenericViewHolder(binding_) {

    init {
        binding_.background.setOnClickListener { onItemClickListener(adapterPosition) }
    }

    override fun <T> bindVH(model_: BaseModel) {
        val user: UserModel? = model_ as? UserModel
        if (user != null) setValues(user.name, null, null)

        val project = model_ as? ProjectModel
        if (project != null)
            setValues(project.currentData.name, project.currentData.description, null)

        val member = model_ as? MemberModel
        if (member != null) setValues(member.user.name)
    }

    fun setValues(title: String? = null, description: String? = null, image: Drawable? = null) {
        val binding = binding_

        if (description.isNullOrEmpty()) binding.statusText.visibility = GONE
        else binding.statusText.text = description

        if (title.isNullOrEmpty()) binding.nameText.visibility = GONE
        else binding.nameText.text = title

        if (image == null) binding.image.visibility = GONE
        else binding.image.setImageDrawable(image)
    }
}
