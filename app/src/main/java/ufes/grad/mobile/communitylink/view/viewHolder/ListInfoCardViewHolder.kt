package ufes.grad.mobile.communitylink.view.viewHolder

import android.view.View.GONE
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.GoalPostModel
import ufes.grad.mobile.communitylink.data.model.GoalPostTypeEnum
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
        val donation: ActionDonationModel? = model_ as? ActionDonationModel
        if (donation != null) setActionCard(donation)

        val event: ActionEventModel? = model_ as? ActionEventModel
        if (event != null) setActionCard(event)

        val goal_post: GoalPostModel? = model_ as? GoalPostModel
        if (goal_post != null) setGoalPost(goal_post)

        val post: PostModel? = model_ as? PostModel
        if (post != null) setPost(post)
    }

    fun setPost(model: PostModel) {
        val binding: LayoutInfoCardBinding = binding_

        binding.projectText.text = model.action.project.currentData.name
        // TODO("Set project image")
        //        binding.projectImage.setImageDrawable(model.action.project.currentData.logo)

        binding.tag.setType(
            if (model.action is ActionDonationModel) TagLayout.TagType.DONATION
            else TagLayout.TagType.EVENT
        )
        binding.dateText.text = model.date
        binding.nameText.text = model.action.name

        binding.descriptionText.text = model.text
        if (model.media.isNullOrEmpty()) binding.postImage.visibility = GONE
        // TODO("Set post media")
        //        else
        //            binding.postImage.setImageDrawable(model.media)
    }

    fun setGoalPost(model: GoalPostModel) {
        val binding: LayoutInfoCardBinding = binding_

        binding.projectText.text = model.actionDonation.project.currentData.name
        // TODO("Set project image")
        // binding.projectImage.setImageDrawable(model.actionDonation.project.currentData.logo)

        // TODO("Set lower text represent what the project is doing")

        binding.tag.setType(TagLayout.TagType.DONATION)
        binding.dateText.text = model.date
        binding.nameText.text = model.actionDonation.name

        val start_text: String =
            when (model.type) {
                GoalPostTypeEnum.NEW -> itemView.context.getString(R.string.new_goal_created)
                GoalPostTypeEnum.MODIFIED -> itemView.context.getString(R.string.modified_goal)
                GoalPostTypeEnum.HIT -> itemView.context.getString(R.string.goal_hit)
            }
        binding.descriptionText.text = "$start_text - ${model.description}"
        binding.postImage.visibility = GONE
    }

    fun setActionCard(model: ActionModel) {
        val binding: LayoutInfoCardBinding = binding_

        binding.projectText.text = model.project.currentData.name
        // TODO("Set project image")
        //        binding.projectImage.setImageDrawable(model.project.currentData.logo)

        binding.tag.setType(
            if (model is ActionDonationModel) TagLayout.TagType.DONATION
            else TagLayout.TagType.EVENT
        )

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
