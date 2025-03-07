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
        val text = customAttributesStyle.getString(R.styleable.FormsLayout_texts)
        customAttributesStyle.recycle()
        binding.formsTitleText.text = text
        binding.formsEditText.hint = text
    }

}