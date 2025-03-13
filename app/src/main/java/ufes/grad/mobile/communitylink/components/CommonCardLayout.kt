package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutCommonCardBinding

class CommonCardLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: LayoutCommonCardBinding =
        LayoutCommonCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.CommonCardLayout)
        val name = customAttributesStyle.getString(R.styleable.CommonCardLayout_card_upper_text)
        val status = customAttributesStyle.getString(R.styleable.CommonCardLayout_card_lower_text)
        val image = customAttributesStyle.getDrawable(R.styleable.CommonCardLayout_card_image)

        customAttributesStyle.recycle()

        setValues(name, status, image)
    }

    fun setValues(name: String? = null, status: String? = null, image: Drawable? = null) {
        if (!name.isNullOrEmpty()) binding.nameText.text = name
        else binding.nameText.visibility = GONE

        if (!status.isNullOrEmpty()) binding.statusText.text = status
        else binding.statusText.visibility = GONE

        if (image == null) binding.image.visibility = GONE
        else binding.image.setImageDrawable(image)
    }
}
