package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutInfoCardBinding

class InfoCardLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutInfoCardBinding =
        LayoutInfoCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
        setAttributes(context, attrs)
        setOnClickListener { performClick() }
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
        val second_title =
            customAttributesStyle.getString(R.styleable.InfoCardLayout_second_title_text)
        val second_description =
            customAttributesStyle.getString(R.styleable.InfoCardLayout_second_description_text)
        val show_alert =
            customAttributesStyle.getBoolean(R.styleable.InfoCardLayout_show_alert, false)
        val show_button =
            customAttributesStyle.getBoolean(R.styleable.InfoCardLayout_cancel_button, false)
        val tag_type = customAttributesStyle.getInt(R.styleable.InfoCardLayout_tag_type, 0)

        customAttributesStyle.recycle()
        setValues(
            title!!,
            description,
            date,
            project,
            lower,
            second_title,
            second_description,
            show_alert,
            show_button,
            TagLayout.TagType.entries.toTypedArray()[tag_type]
        )
    }

    fun setValues(
        title: String,
        description: String? = null,
        date: String? = null,
        project_name: String? = null,
        lower_text: String? = null,
        second_title: String? = null,
        second_description: String? = null,
        show_alert: Boolean = false,
        cancel_button: Boolean = false,
        tag_type: TagLayout.TagType = TagLayout.TagType.DEFAULT
    ) {
        binding.projectImage.visibility = GONE
        if (project_name.isNullOrEmpty() || lower_text.isNullOrEmpty())
            binding.projectInfo.visibility = GONE
        else {
            binding.projectText.text = project_name
            binding.lowerText.text = lower_text
        }

        binding.tag.setType(tag_type)

        if (!date.isNullOrEmpty()) binding.dateText.text = date
        else binding.dateText.visibility = GONE

        binding.nameText.text = title

        if (!description.isNullOrEmpty()) binding.descriptionText.text = description
        else binding.descriptionText.visibility = GONE

        if (!second_description.isNullOrEmpty())
            binding.secondDescriptionText.text = second_description
        else binding.secondDescriptionText.visibility = GONE

        if (!second_title.isNullOrEmpty()) binding.secondNameText.text = second_title
        else binding.secondNameText.visibility = GONE

        binding.cancelButton.visibility = if (cancel_button) VISIBLE else GONE
        binding.alertText.visibility = if (show_alert) VISIBLE else GONE
    }
}
