package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding
import ufes.grad.mobile.communitylink.view.viewHolder.GenericViewHolder
import ufes.grad.mobile.communitylink.view.viewHolder.ListInfoCardViewHolder

class DashboardCardAdapter(
    private val content: ListInfoCardAdapter.InfoCardContent =
        ListInfoCardAdapter.InfoCardContent.VIEW
) : GenericAdapter<GenericViewHolder>() {

    var onButtonClickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val item = LayoutInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListInfoCardViewHolder(item, onItemClickListener, onButtonClickListener, content)
    }
}
