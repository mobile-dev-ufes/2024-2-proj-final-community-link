package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import ufes.grad.mobile.communitylink.R

class FormsLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var titleText: TextView
    private var editText: EditText

    init {
        inflate(context, R.layout.forms_layout, this)
        titleText = findViewById<TextView>(R.id.forms_title_text)
        editText = findViewById<EditText>(R.id.forms_edit_text)
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.FormsLayout)
        val text = customAttributesStyle.getString(R.styleable.FormsLayout_texts)
        customAttributesStyle.recycle()
        titleText.text = text
        editText.hint = text
    }

}