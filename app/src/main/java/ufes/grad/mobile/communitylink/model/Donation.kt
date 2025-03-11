package ufes.grad.mobile.communitylink.model

import android.media.Image

open class Donation (
    val id: Int,
    val value: Float,
    val objectName: String,
    val status: String,
    val date: String,
    val confirmationImage: Image
) {
}