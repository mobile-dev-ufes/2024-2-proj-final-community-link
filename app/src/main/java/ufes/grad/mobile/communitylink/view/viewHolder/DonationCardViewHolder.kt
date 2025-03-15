package ufes.grad.mobile.communitylink.view.viewHolder

import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.data.model.DonationStatusEnum
import ufes.grad.mobile.communitylink.databinding.LayoutDonationCardBinding

class DonationCardViewHolder(
    private val binding_: LayoutDonationCardBinding,
    private val onItemClickListener: (Int) -> Unit
) : GenericViewHolder(binding_) {

    init {
        itemView.setOnClickListener { onItemClickListener(adapterPosition) }
    }

    override fun <T> bindVH(model_: BaseModel) {
        val donation: DonationModel? = model_ as? DonationModel
        if (donation != null) setCard(donation)
    }

    fun setCard(model: DonationModel) {
        val binding: LayoutDonationCardBinding = binding_

        binding.status.text =
            if (model.status == DonationStatusEnum.PENDING)
                itemView.context.getString(R.string.pending)
            else itemView.context.getString(R.string.received)
        binding.date.text = model.date
        binding.name.text =
            if (model.objectName.isEmpty()) model.value.toString() else model.objectName
    }
}
