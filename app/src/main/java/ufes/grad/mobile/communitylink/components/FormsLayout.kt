package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.setMargins
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutFormsBinding

class FormsLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutFormsBinding =
        LayoutFormsBinding.inflate(LayoutInflater.from(context), this, true)

    var titleText = binding.formsTitleText
    var editText = binding.formsEditText

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.FormsLayout)
        val title = customAttributesStyle.getString(R.styleable.FormsLayout_forms_title_text)
        val titleColor =
            customAttributesStyle.getColor(
                R.styleable.FormsLayout_forms_title_color,
                context.getColor(R.color.black)
            )
        val hint = customAttributesStyle.getString(R.styleable.FormsLayout_forms_hint_text)
        val demand = customAttributesStyle.getBoolean(R.styleable.FormsLayout_forms_demand, false)
        val input_type = customAttributesStyle.getInt(R.styleable.FormsLayout_forms_input_type, 1)
        val box_size =
            customAttributesStyle.getDimension(R.styleable.FormsLayout_forms_box_size, 0.0f)
        val margin = customAttributesStyle.getBoolean(R.styleable.FormsLayout_forms_margin, true)

        customAttributesStyle.recycle()
        setValues(title!!, hint, demand, input_type, titleColor, box_size, margin)
    }

    fun setValues(
        title: String,
        hint: String? = null,
        demand: Boolean = false,
        input_type: Int,
        title_color: Int,
        box_size: Float = 0.0f,
        margin: Boolean
    ) {
        binding.formsTitleText.setTextColor(title_color)
        binding.formsTitleText.text = if (demand) "$title*" else title
        binding.formsEditText.hint = if (!hint.isNullOrEmpty()) hint else title
        binding.formsEditText.inputType = input_type

        if (box_size > 0) binding.formsEditText.height = box_size.toInt()

        if (!margin) {
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(0)
            binding.formsEditText.layoutParams = params
        }
    }
}
