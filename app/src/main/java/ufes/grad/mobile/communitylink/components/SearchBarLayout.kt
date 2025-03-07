package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ufes.grad.mobile.communitylink.databinding.LayoutSearchBarBinding

class SearchBarLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        LayoutSearchBarBinding.inflate(LayoutInflater.from(context), this, true)
    }
}