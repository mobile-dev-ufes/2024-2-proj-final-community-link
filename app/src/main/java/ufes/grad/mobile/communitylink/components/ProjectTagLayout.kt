package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutProjectTagBinding

class ProjectTagLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutProjectTagBinding =
        LayoutProjectTagBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.ProjectTagLayout)
        val text = customAttributesStyle.getString(R.styleable.ProjectTagLayout_project_tag_name)
        val image =
            customAttributesStyle.getDrawable(R.styleable.ProjectTagLayout_project_tag_image)

        customAttributesStyle.recycle()

        setValues(text!!, image)
    }

    fun setValues(text: String, image: Drawable? = null) {
        binding.name.text = text
        if (image == null) binding.image.visibility = GONE
        else binding.image.setImageDrawable(image)
    }
}
