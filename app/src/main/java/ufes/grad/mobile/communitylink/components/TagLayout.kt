package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutCardTagBinding

class TagLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutCardTagBinding =
        LayoutCardTagBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.TagLayout)
        val title = customAttributesStyle.getString(R.styleable.TagLayout_text)
        val color = customAttributesStyle.getColor(R.styleable.TagLayout_color, 1)
        customAttributesStyle.recycle()
        setValues(title, color)
    }

    fun setValues(text: String?, color: Int) {
        binding.background.setBackgroundColor(color)
        if (!text.isNullOrEmpty())
            binding.text.text = text
    }


}