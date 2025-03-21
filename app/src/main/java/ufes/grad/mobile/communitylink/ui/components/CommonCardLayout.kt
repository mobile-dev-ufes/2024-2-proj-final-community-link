package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutCommonCardBinding

class CommonCardLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutCommonCardBinding =
        LayoutCommonCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
        setAttributes(context, attrs)
        setOnClickListener { performClick() }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.CommonCardLayout)
        val name = customAttributesStyle.getString(R.styleable.CommonCardLayout_card_upper_text)
        val status = customAttributesStyle.getString(R.styleable.CommonCardLayout_card_lower_text)

        customAttributesStyle.recycle()

        setValues(name, status)
    }

    fun setValues(name: String? = null, status: String? = null) {
        if (!name.isNullOrEmpty()) binding.nameText.text = name
        else binding.nameText.visibility = GONE

        if (!status.isNullOrEmpty()) binding.statusText.text = status
        else binding.statusText.visibility = GONE
    }
}
