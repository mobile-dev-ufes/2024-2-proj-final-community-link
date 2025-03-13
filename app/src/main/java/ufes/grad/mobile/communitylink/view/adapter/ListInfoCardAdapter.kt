package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding
import ufes.grad.mobile.communitylink.model.ActionModel
import ufes.grad.mobile.communitylink.view.viewHolder.ListInfoCardViewHolder

class ListInfoCardAdapter : GenericAdapter<ListInfoCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListInfoCardViewHolder {
        val item = LayoutInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListInfoCardViewHolder(item)
    }

    fun updateInfoCardList(list_: List<ActionModel>) {
        list = list_
        //        notifyDatasetChanged()
    }
}
