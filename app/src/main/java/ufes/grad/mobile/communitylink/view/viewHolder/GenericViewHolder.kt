package ufes.grad.mobile.communitylink.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ufes.grad.mobile.communitylink.model.BaseModel

abstract class GenericViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun <T> bindVH(model_: BaseModel)
}
