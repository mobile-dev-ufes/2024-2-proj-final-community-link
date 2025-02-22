package ufes.grad.mobile.communitylink.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R

class CommunityLinkTitle(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.community_link_title_layout, this)
    }
}