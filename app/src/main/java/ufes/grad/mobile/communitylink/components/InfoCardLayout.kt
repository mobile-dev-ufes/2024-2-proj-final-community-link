package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding

class InfoCardLayout(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var binding: LayoutInfoCardBinding =
        LayoutInfoCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.InfoCardLayout)
        val project = customAttributesStyle.getString(R.styleable.InfoCardLayout_project_text)
        val lower = customAttributesStyle.getString(R.styleable.InfoCardLayout_lower_text)
        val date = customAttributesStyle.getString(R.styleable.InfoCardLayout_date_text)
        val title = customAttributesStyle.getString(R.styleable.InfoCardLayout_title_text)
        val description =
            customAttributesStyle.getString(R.styleable.InfoCardLayout_description_text)
        val tag = customAttributesStyle.getInt(R.styleable.InfoCardLayout_tag_type, 0)

        customAttributesStyle.recycle()
        setValues(title!!, description!!, date, project, lower, tag)
    }

    fun setValues(
        title: String,
        description: String,
        date: String? = null,
        project_name: String? = null,
        lower_text: String? = null,
        tag: Int = 0
    ) {
        if (project_name.isNullOrEmpty() || lower_text.isNullOrEmpty())
            binding.projectInfo.visibility = GONE
        else {
            binding.projectText.text = project_name
            binding.lowerText.text = lower_text
        }

        binding.tag.setType(tag)

        if (!date.isNullOrEmpty())
            binding.dateText.text = date
        else
            binding.dateText.visibility = GONE

        binding.nameText.text = title
        binding.descriptionText.text = description
    }
}