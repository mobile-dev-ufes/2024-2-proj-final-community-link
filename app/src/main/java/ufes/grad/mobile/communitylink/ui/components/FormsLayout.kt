package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.text.InputFilter
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
        isClickable = true
        setAttributes(context, attrs)
        setOnClickListener { performClick() }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.FormsLayout)
        val title = customAttributesStyle.getString(R.styleable.FormsLayout_forms_title_text)
        val title_color =
            customAttributesStyle.getColor(
                R.styleable.FormsLayout_forms_title_color,
                context.getColor(R.color.black)
            )
        val hint = customAttributesStyle.getString(R.styleable.FormsLayout_forms_hint_text)
        val demand = customAttributesStyle.getBoolean(R.styleable.FormsLayout_forms_demand, false)
        val max_length =
            customAttributesStyle.getInt(R.styleable.FormsLayout_forms_maximum_length, 140)
        val input_type = customAttributesStyle.getInt(R.styleable.FormsLayout_forms_input_type, 1)
        val box_size =
            customAttributesStyle.getDimension(R.styleable.FormsLayout_forms_box_size, 0.0f)
        val margin = customAttributesStyle.getBoolean(R.styleable.FormsLayout_forms_margin, true)

        customAttributesStyle.recycle()
        setValues(title!!, hint, demand, input_type, max_length, title_color, box_size, margin)
    }

    fun setValues(
        title: String,
        hint: String? = null,
        demand: Boolean = false,
        input_type: Int,
        max_length: Int,
        title_color: Int,
        box_size: Float = 0.0f,
        margin: Boolean = true
    ) {
        titleText.setTextColor(title_color)
        titleText.text = if (demand) "$title*" else title
        editText.hint = if (!hint.isNullOrEmpty()) hint else title
        editText.filters += InputFilter.LengthFilter(max_length)
        editText.inputType = input_type

        if (box_size > 0) {
            editText.height = box_size.toInt()
            editText.maxLines = (box_size / editText.textSize).toInt()
            editText.isSingleLine = false
        }

        if (!margin) {
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(0)
            editText.layoutParams = params
        }
    }
}
