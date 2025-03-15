package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutIconButtonBinding

class IconButtonLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

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
        val text_size =
            customAttributesStyle.getDimension(R.styleable.IconButtonLayout_button_text_size, 0.0f)
        val icon_size =
            customAttributesStyle.getDimension(R.styleable.IconButtonLayout_button_icon_size, 0.0f)

        customAttributesStyle.recycle()

        binding.icon.setImageDrawable(icon)
        if (icon_size > 0.0f)
            binding.icon.layoutParams = LayoutParams(icon_size.toInt(), icon_size.toInt())
        binding.text.text = text
        if (text_size > 0.0f) binding.text.textSize = text_size
    }
}
