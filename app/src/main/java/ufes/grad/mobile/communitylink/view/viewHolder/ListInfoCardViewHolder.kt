package ufes.grad.mobile.communitylink.view.viewHolder

import android.view.View.GONE
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding

class ListInfoCardViewHolder(private val binding: LayoutInfoCardBinding) :
    GenericViewHolder(binding) {

    override fun <T> bindVH(model_: BaseModel) {
        val action: ActionModel? = model_ as? ActionModel
        if (action == null) return

        setValues(
            action.name,
            action.description,
            action.initDate,
            action.project.currentData.name
        )
    }

    fun setValues(
        title: String,
        description: String? = null,
        date: String? = null,
        projectName: String? = null,
        tag: Int = 0
    ) {
        if (projectName.isNullOrEmpty()) binding.projectInfo.visibility = GONE
        else binding.projectText.text = projectName

        binding.tag.setType(tag)

        binding.dateText.text = date
        binding.nameText.text = title
        binding.descriptionText.text = description

        binding.secondDescriptionText.visibility = GONE
        binding.secondNameText.visibility = GONE
        binding.cancelButton.visibility = GONE
        binding.alertText.visibility = GONE
    }
}
