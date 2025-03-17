package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutProjectCardBinding

class ProjectCardLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var binding: LayoutProjectCardBinding =
        LayoutProjectCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.ProjectCardLayout)
        val name = customAttributesStyle.getString(R.styleable.ProjectCardLayout_project_name)
        val description =
            customAttributesStyle.getString(R.styleable.ProjectCardLayout_project_description)

        customAttributesStyle.recycle()

        setValues(name!!, description!!)
    }

    fun setValues(name: String, description: String) {
        binding.name.text = name
        binding.description.text = description
    }
}
