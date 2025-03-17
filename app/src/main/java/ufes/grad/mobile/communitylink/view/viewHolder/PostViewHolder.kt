package ufes.grad.mobile.communitylink.view.viewHolder

import android.view.View.GONE
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.LayoutPostBinding

class PostViewHolder(
    private val binding_: LayoutPostBinding,
    private val onItemClickListener: (Int) -> Unit,
    private val onButtonClickListener: (Int) -> Unit,
    private val edit: Boolean = false
) : GenericViewHolder(binding_) {

    init {
        binding_.background.setOnClickListener { onItemClickListener(adapterPosition) }
        binding_.deleteButton.setOnClickListener { onButtonClickListener(adapterPosition) }
    }

    override fun <T> bindVH(model_: BaseModel) {
        val model: PostModel? = model_ as? PostModel
        if (model != null) setPost(model)
    }

    fun setPost(model: PostModel) {
        val binding: LayoutPostBinding = binding_

        binding.date.text = model.date
        binding.post.text = model.text

        if (!edit) binding.deleteButton.visibility = GONE
    }
}
