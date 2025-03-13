package ufes.grad.mobile.communitylink.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.view.viewHolder.GenericViewHolder

open class GenericAdapter<T : GenericViewHolder> : RecyclerView.Adapter<T>() {

    var list: List<BaseModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        TODO("Colocar um erro aqui")
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindVH<T>(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}
