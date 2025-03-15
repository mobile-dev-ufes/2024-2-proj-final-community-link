package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.databinding.LayoutGoalPostBinding
import ufes.grad.mobile.communitylink.view.viewHolder.GoalPostViewHolder

class GoalPostAdapter() : GenericAdapter<GoalPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalPostViewHolder {
        val item = LayoutGoalPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalPostViewHolder(item, onItemClickListener)
    }
}
