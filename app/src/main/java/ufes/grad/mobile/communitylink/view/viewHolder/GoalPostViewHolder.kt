package ufes.grad.mobile.communitylink.view.viewHolder

import android.view.View.GONE
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.GoalPostModel
import ufes.grad.mobile.communitylink.data.model.GoalPostTypeEnum
import ufes.grad.mobile.communitylink.databinding.LayoutGoalPostBinding
import ufes.grad.mobile.communitylink.ui.components.TagLayout

class GoalPostViewHolder(
    private val binding_: LayoutGoalPostBinding,
    private val onItemClickListener: (Int) -> Unit
) : GenericViewHolder(binding_) {

    init {
        itemView.setOnClickListener { onItemClickListener(adapterPosition) }
    }

    override fun <T> bindVH(model_: BaseModel) {
        val model: GoalPostModel? = model_ as? GoalPostModel
        if (model != null) setGoalPost(model)
    }

    fun setGoalPost(model: GoalPostModel) {
        val binding: LayoutGoalPostBinding = binding_

        binding.tag.setType(TagLayout.TagType.GOAL)
        binding.dateText.text = model.date
        binding.nameText.text =
            when (model.type) {
                GoalPostTypeEnum.NEW -> itemView.context.getString(R.string.new_goal_created)
                GoalPostTypeEnum.MODIFIED -> itemView.context.getString(R.string.modified_goal)
                GoalPostTypeEnum.HIT -> itemView.context.getString(R.string.goal_hit)
            }

        val text: String? =
            when (model.type) {
                GoalPostTypeEnum.NEW -> itemView.context.getString(R.string.goal_post)
                GoalPostTypeEnum.MODIFIED -> itemView.context.getString(R.string.new_goal_post)
                GoalPostTypeEnum.HIT -> null
            }
        if (text == null) binding.footnoteText.visibility = GONE
        else binding.footnoteText.text = "$text model.amount"
    }
}
