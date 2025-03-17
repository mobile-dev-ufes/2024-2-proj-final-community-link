package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutDonationCardBinding

class DonationCardLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutDonationCardBinding =
        LayoutDonationCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
        setAttributes(context, attrs)
        setOnClickListener { performClick() }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.DonationCardLayout)
        val name = customAttributesStyle.getString(R.styleable.DonationCardLayout_donation_name)
        val date = customAttributesStyle.getString(R.styleable.DonationCardLayout_donation_date)
        val is_pending =
            customAttributesStyle.getBoolean(R.styleable.DonationCardLayout_donation_pending, false)

        customAttributesStyle.recycle()
        setValues(name!!, date!!, is_pending)
    }

    fun setValues(name: String, date: String, is_pending: Boolean) {
        binding.name.text = name
        binding.date.text = date
        binding.status.text =
            if (is_pending) context.getString(R.string.pending)
            else context.getString(R.string.received)
    }
}
