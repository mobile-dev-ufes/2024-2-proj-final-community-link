package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.LayoutCommonCardBinding
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding
import ufes.grad.mobile.communitylink.view.viewHolder.GenericViewHolder
import ufes.grad.mobile.communitylink.view.viewHolder.ListCommonCardViewHolder
import ufes.grad.mobile.communitylink.view.viewHolder.ListInfoCardViewHolder

class ExploreCardAdapter(
    private val content: ListInfoCardAdapter.InfoCardContent =
        ListInfoCardAdapter.InfoCardContent.VIEW
) : GenericAdapter<GenericViewHolder>() {

    enum class ExploreCards {
        PROJECT,
        OTHER
    }

    var onButtonClickListener: (Int) -> Unit = {}
    var onProjectClickListener: (Int) -> Unit = {}

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is ProjectModel) ExploreCards.PROJECT.ordinal
        else ExploreCards.OTHER.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return when (viewType) {
            ExploreCards.PROJECT.ordinal -> {
                val item =
                    LayoutCommonCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return ListCommonCardViewHolder(item)
            }
            else -> {
                val item =
                    LayoutInfoCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return ListInfoCardViewHolder(
                    item,
                    onItemClickListener,
                    onButtonClickListener,
                    onProjectClickListener,
                    content
                )
            }
        }
    }
}
