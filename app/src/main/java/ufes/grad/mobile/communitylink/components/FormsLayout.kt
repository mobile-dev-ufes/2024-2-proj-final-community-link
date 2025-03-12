package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
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
        val hint = customAttributesStyle.getString(R.styleable.FormsLayout_forms_hint_text)
        val demand = customAttributesStyle.getBoolean(R.styleable.FormsLayout_forms_demand, false)

        customAttributesStyle.recycle()
        setValues(title!!, hint, demand)
    }

    fun setValues(title: String, hint: String? = null, demand: Boolean = false) {
        binding.formsEditText.hint = if (!hint.isNullOrEmpty()) hint else title
        binding.formsTitleText.text = if (demand) "$title*" else title
    }

}