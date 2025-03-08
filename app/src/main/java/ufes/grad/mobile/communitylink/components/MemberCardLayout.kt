package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutMemberCardBinding

class MemberCardLayout(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var binding: LayoutMemberCardBinding =
        LayoutMemberCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.MemberCardLayout)
        val name = customAttributesStyle.getString(R.styleable.MemberCardLayout_member_name)
        val status = customAttributesStyle.getString(R.styleable.MemberCardLayout_member_status)

        customAttributesStyle.recycle()

        setValues(name!!, status)
    }

    fun setValues(name: String, status: String? = null) {
        binding.nameText.text = name

        if (!status.isNullOrEmpty())
            binding.statusText.text = status
        else
            binding.statusText.visibility = GONE
    }
}