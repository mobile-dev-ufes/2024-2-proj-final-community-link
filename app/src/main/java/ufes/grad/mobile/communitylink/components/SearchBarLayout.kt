package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutSearchBarBinding

class SearchBarLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var binding = LayoutSearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.SearchBarLayout)
        val is_top = customAttributesStyle.getBoolean(R.styleable.SearchBarLayout_fix_top, false)

        customAttributesStyle.recycle()

        setValues(is_top)
    }

    fun setValues(is_top: Boolean) {
        val style = ResourcesCompat.getDrawable(
            resources,
            (if (!is_top) R.drawable.style_tag else R.drawable.style_search_bar),
            null
        )
        binding.main.background = (style)
    }

    fun toggleX(state: Boolean) {
        binding.xParent.visibility = if (state) GONE else VISIBLE
    }

}