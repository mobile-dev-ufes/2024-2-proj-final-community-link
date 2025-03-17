package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutPostBinding

class PostLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutPostBinding =
        LayoutPostBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
        setAttributes(context, attrs)
        setOnClickListener { performClick() }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.PostLayout)
        val text = customAttributesStyle.getString(R.styleable.PostLayout_post_text)
        val date = customAttributesStyle.getString(R.styleable.PostLayout_post_date)
        val image = customAttributesStyle.getDrawable(R.styleable.PostLayout_post_image)

        customAttributesStyle.recycle()

        setValues(text!!, date!!, image)
    }

    fun setValues(text: String, date: String, image: Drawable? = null) {
        binding.post.text = text
        binding.date.text = date
        if (image == null) binding.image.visibility = GONE
        else binding.image.setImageDrawable(image)
    }
}
