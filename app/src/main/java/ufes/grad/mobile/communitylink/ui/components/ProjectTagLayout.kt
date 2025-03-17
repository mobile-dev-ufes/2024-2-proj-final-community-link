package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutProjectTagBinding

class ProjectTagLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutProjectTagBinding =
        LayoutProjectTagBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
        setAttributes(context, attrs)
        setOnClickListener { performClick() }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.ProjectTagLayout)
        val text = customAttributesStyle.getString(R.styleable.ProjectTagLayout_project_tag_name)
        customAttributesStyle.recycle()
        setValues(text!!)
    }

    fun setValues(text: String) {
        binding.name.text = text
    }
}
