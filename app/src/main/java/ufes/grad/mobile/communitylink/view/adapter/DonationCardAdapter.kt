package ufes.grad.mobile.communitylink.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.databinding.LayoutDonationCardBinding
import ufes.grad.mobile.communitylink.view.viewHolder.DonationCardViewHolder

class DonationCardAdapter : GenericAdapter<DonationCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationCardViewHolder {
        val item =
            LayoutDonationCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DonationCardViewHolder(item, onItemClickListener)
    }
}
