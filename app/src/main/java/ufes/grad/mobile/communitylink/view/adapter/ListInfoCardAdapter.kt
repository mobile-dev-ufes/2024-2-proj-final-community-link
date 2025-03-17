package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding
import ufes.grad.mobile.communitylink.view.viewHolder.ListInfoCardViewHolder

class ListInfoCardAdapter(var content: InfoCardContent = InfoCardContent.VIEW) :
    GenericAdapter<ListInfoCardViewHolder>() {

    enum class InfoCardContent {
        VIEW,
        MY_ACTIONS,
        CANCELLABLE,
        PAST
    }

    var onButtonClickListener: (Int) -> Unit = {}
    var onProjectClickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListInfoCardViewHolder {
        val item = LayoutInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListInfoCardViewHolder(
            item,
            onItemClickListener,
            onButtonClickListener,
            onProjectClickListener,
            content
        )
    }
}
