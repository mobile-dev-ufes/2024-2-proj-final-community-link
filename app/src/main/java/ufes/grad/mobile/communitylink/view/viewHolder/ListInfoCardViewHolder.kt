package ufes.grad.mobile.communitylink.view.viewHolder

import android.view.View.GONE
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding
import ufes.grad.mobile.communitylink.ui.components.TagLayout
import ufes.grad.mobile.communitylink.view.adapter.ListInfoCardAdapter

class ListInfoCardViewHolder(
    private val binding_: LayoutInfoCardBinding,
    private val onItemClickListener: (Int) -> Unit,
    private val onButtonClickListener: (Int) -> Unit,
    private val onProjectClickListener: (Int) -> Unit,
    private val content: ListInfoCardAdapter.InfoCardContent
) : GenericViewHolder(binding_) {

    init {
        binding_.cancelButton.setOnClickListener { onButtonClickListener(adapterPosition) }
        binding_.content.setOnClickListener { onItemClickListener(adapterPosition) }
        binding_.projectInfo.setOnClickListener { onProjectClickListener(adapterPosition) }
    }

    override fun <T> bindVH(model_: BaseModel) {
        val event: ActionEventModel? = model_ as? ActionEventModel
        if (event != null) setActionCard(event)

        val post: PostModel? = model_ as? PostModel
        if (post != null) setPost(post)
    }

    fun setPost(model: PostModel) {
        val binding: LayoutInfoCardBinding = binding_

        binding.projectText.text = model.action.project.currentData.name

        binding.tag.setType(TagLayout.TagType.EVENT)
        binding.dateText.text = model.date
        binding.nameText.text = model.action.name

        binding.descriptionText.text = model.text
        binding.postImage.visibility = GONE
    }

    fun setActionCard(model: ActionModel) {
        val binding: LayoutInfoCardBinding = binding_

        binding.projectText.text = model.project.currentData.name

        binding.tag.setType(TagLayout.TagType.EVENT)

        binding.dateText.text = model.initDate
        binding.nameText.text = model.name
        binding.descriptionText.text = model.description

        binding.postImage.visibility = GONE

        if (content != ListInfoCardAdapter.InfoCardContent.PAST) {
            binding.secondDescriptionText.visibility = GONE
            binding.secondNameText.visibility = GONE
        }

        if (content != ListInfoCardAdapter.InfoCardContent.CANCELLABLE) {
            binding.cancelButton.visibility = GONE
        }

        if (content != ListInfoCardAdapter.InfoCardContent.MY_ACTIONS || model.status) {
            binding.alertText.visibility = GONE
        }
    }
}
