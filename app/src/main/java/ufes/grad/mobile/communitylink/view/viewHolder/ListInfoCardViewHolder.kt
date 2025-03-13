package ufes.grad.mobile.communitylink.view.viewHolder

import android.view.View.GONE
import java.text.SimpleDateFormat
import java.util.Locale
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding

class ListInfoCardViewHolder(private val binding: LayoutInfoCardBinding) :
    GenericViewHolder(binding) {

    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun <T> bindVH(model_: BaseModel) {
        val action: ActionModel? = model_ as? ActionModel
        if (action == null) return

        setValues(
            action.name,
            action.description,
            dateFormatter.format(action.initDate),
            action.project.currentData.name
        )
    }

    fun setValues(
        title: String,
        description: String? = null,
        date: String? = null,
        project_name: String? = null,
        tag: Int = 0
    ) {
        if (project_name.isNullOrEmpty()) binding.projectInfo.visibility = GONE
        else binding.projectText.text = project_name

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
