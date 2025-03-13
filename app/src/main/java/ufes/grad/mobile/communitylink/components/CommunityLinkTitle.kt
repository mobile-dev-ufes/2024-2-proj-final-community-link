package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.databinding.LayoutCommunityLinkTitleBinding

class CommunityLinkTitle(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutCommunityLinkTitleBinding.inflate(LayoutInflater.from(context), this, true)
    }
}
