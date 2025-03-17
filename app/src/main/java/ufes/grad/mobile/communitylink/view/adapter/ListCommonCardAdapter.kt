package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.databinding.LayoutCommonCardBinding
import ufes.grad.mobile.communitylink.view.viewHolder.ListCommonCardViewHolder

class ListCommonCardAdapter : GenericAdapter<ListCommonCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommonCardViewHolder {
        val item =
            LayoutCommonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCommonCardViewHolder(item, onItemClickListener)
    }
}
