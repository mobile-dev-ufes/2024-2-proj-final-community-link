package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutDashboardCardBinding

class DashboardCardLayout(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private var binding: LayoutDashboardCardBinding =
        LayoutDashboardCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.DashboardCardLayout)
        val project = customAttributesStyle.getString(R.styleable.DashboardCardLayout_project_text)
        val lower = customAttributesStyle.getString(R.styleable.DashboardCardLayout_lower_text)
        val date = customAttributesStyle.getString(R.styleable.DashboardCardLayout_date_text)
        val title = customAttributesStyle.getString(R.styleable.DashboardCardLayout_title_text)
        val description =
            customAttributesStyle.getString(R.styleable.DashboardCardLayout_description_text)

        val tag_color = customAttributesStyle.getColor(R.styleable.DashboardCardLayout_tag_color, 1)
        val tag_text = customAttributesStyle.getString(R.styleable.DashboardCardLayout_tag_text)

        customAttributesStyle.recycle()

        binding.projectText.text = project
        binding.lowerText.text = lower
        binding.dateText.text = date
        binding.nameText.text = title
        binding.descriptionText.text = description

        binding.tag.setValues(tag_text, tag_color)
    }
}