package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.view.viewHolder.ListInfoCardViewHolder

class ListInfoCardAdapter : RecyclerView.Adapter<ListInfoCardViewHolder>() {

    private var infoCardList: List<ActionModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListInfoCardViewHolder {
        val item = LayoutInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListInfoCardViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListInfoCardViewHolder, position: Int) {
        holder.bindVH(infoCardList[position])
    }

    override fun getItemCount(): Int {
        return infoCardList.count()
    }

    fun updateInfoCardList(list: List<ActionModel>) {
        infoCardList = list
        notifyDataSetChanged()
    }
}
