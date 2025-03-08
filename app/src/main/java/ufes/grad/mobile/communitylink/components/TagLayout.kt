package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutCardTagBinding

class TagLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    enum class TagType() { DEFAULT, DONATION, EVENT, FILLED, EMPTY }

    var type: TagType = TagType.DEFAULT

    private var binding: LayoutCardTagBinding =
        LayoutCardTagBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setType(0)
    }

    private fun setValues(text: String, color: ColorStateList) {
        binding.background.backgroundTintList = color
        binding.text.text = text
    }

    fun setType(type: Int) {
        this.type = TagType.entries.toTypedArray()[type]
        var color = context.getColorStateList(R.color.black)
        var text = context.getString(R.string.placeholder)

        when (this.type) {
            TagType.DONATION -> {
                color = context.getColorStateList(R.color.purple)
                text = context.getString(R.string.donation)
            }

            TagType.EVENT -> {
                color = context.getColorStateList(R.color.blue)
                text = context.getString(R.string.event)
            }

            TagType.FILLED -> {
                color = context.getColorStateList(R.color.red)
                text = context.getString(R.string.filled)
            }

            TagType.EMPTY -> {
                color = context.getColorStateList(R.color.green)
                text = context.getString(R.string.available)
            }

            TagType.DEFAULT -> {}
        }

        setValues(text, color)
    }

}