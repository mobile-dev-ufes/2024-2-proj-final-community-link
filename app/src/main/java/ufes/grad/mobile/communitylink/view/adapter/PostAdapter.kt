package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.databinding.LayoutPostBinding
import ufes.grad.mobile.communitylink.view.viewHolder.GenericViewHolder
import ufes.grad.mobile.communitylink.view.viewHolder.PostViewHolder

class PostAdapter(var edit: Boolean = false) : GenericAdapter<GenericViewHolder>() {

    var onButtonClickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val item = LayoutPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(item, onItemClickListener, onButtonClickListener, edit)
    }
}
