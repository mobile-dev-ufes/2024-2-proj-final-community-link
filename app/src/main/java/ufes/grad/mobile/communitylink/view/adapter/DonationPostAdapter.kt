package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.LayoutGoalPostBinding
import ufes.grad.mobile.communitylink.databinding.LayoutPostBinding
import ufes.grad.mobile.communitylink.view.viewHolder.GenericViewHolder
import ufes.grad.mobile.communitylink.view.viewHolder.GoalPostViewHolder
import ufes.grad.mobile.communitylink.view.viewHolder.PostViewHolder

class DonationPostAdapter(var edit: Boolean = false) : GenericAdapter<GenericViewHolder>() {

    enum class PostType {
        COMMON,
        GOAL
    }

    var onButtonClickListener: (Int) -> Unit = {}

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is PostModel) PostType.COMMON.ordinal else PostType.GOAL.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return when (viewType) {
            PostType.COMMON.ordinal -> {
                val item =
                    LayoutPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PostViewHolder(item, onItemClickListener, onButtonClickListener, edit)
            }
            else -> {
                val item =
                    LayoutGoalPostBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return GoalPostViewHolder(item, onItemClickListener)
            }
        }
    }
}
