package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutIconButtonBinding

class IconButtonLayout(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private var binding: LayoutIconButtonBinding =
        LayoutIconButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
        setAttributes(context, attrs)
        setOnClickListener { performClick() }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.IconButtonLayout)
        val icon = customAttributesStyle.getDrawable(R.styleable.IconButtonLayout_button_icon)
        val text = customAttributesStyle.getString(R.styleable.IconButtonLayout_button_text)

        customAttributesStyle.recycle()

        binding.icon.setImageDrawable(icon)
        binding.text.text = text
    }
}