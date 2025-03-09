package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutSimplePostBinding

class SimplePostLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutSimplePostBinding = LayoutSimplePostBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.SimplePostLayout)
        val title = customAttributesStyle.getString(R.styleable.SimplePostLayout_post_title)
        val date = customAttributesStyle.getString(R.styleable.SimplePostLayout_post_date)
        customAttributesStyle.recycle()
        binding.postTitle.text = title
        binding.postDate.text = date
    }
}
