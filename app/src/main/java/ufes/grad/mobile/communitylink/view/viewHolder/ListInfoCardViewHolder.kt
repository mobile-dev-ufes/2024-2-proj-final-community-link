package ufes.grad.mobile.communitylink.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding
import ufes.grad.mobile.communitylink.model.ActionModel
import java.text.SimpleDateFormat
import java.util.Locale

class ListInfoCardViewHolder(private val binding: LayoutInfoCardBinding)
    : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        fun bindVH(action: ActionModel) {
            binding.projectText.text = action.project.projectData.name
            binding.dateText.text = dateFormatter.format(action.initDate)
            binding.nameText.text = action.name
            binding.descriptionText.text = action.descripton
        }
}
