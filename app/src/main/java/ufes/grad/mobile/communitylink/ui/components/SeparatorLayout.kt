package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutSeparatorBinding

class SeparatorLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var binding: LayoutSeparatorBinding =
        LayoutSeparatorBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.SeparatorLayout)
        val color =
            customAttributesStyle.getColor(
                R.styleable.SeparatorLayout_separator_color,
                context.getColor(R.color.black)
            )

        customAttributesStyle.recycle()

        binding.separator.setBackgroundColor(color)
    }
}
