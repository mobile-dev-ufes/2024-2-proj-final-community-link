package ufes.grad.mobile.communitylink.view.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.PopupPostBinding

class PostPopup(private val mode: PostMode) :
    BasePopup(PopupType.TWO_BUTTON, R.layout.popup_post), View.OnClickListener {

    /**
     * Determines which type of post layout is seen.
     * - VIEW: user can only see the post and image
     * - NEW: user can write a new post and add a new image
     * - EDIT: user can edit the post or delete it
     */
    enum class PostMode() {
        VIEW,
        NEW,
        EDIT
    }

    private var _binding: PopupPostBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupPostBinding.inflate(inflater, container, false)

        when (mode) {
            PostMode.VIEW -> {
                binding.contentForms.visibility = View.GONE
                binding.imageButton.visibility = View.GONE
                binding.titleText.text = getString(R.string.post)
                type = PopupType.ONE_BUTTON
            }
            PostMode.NEW -> {
                binding.dateText.visibility = View.GONE
                binding.contentText.visibility = View.GONE
                binding.titleText.text = getString(R.string.new_post)
                type = PopupType.TWO_BUTTON
            }
            PostMode.EDIT -> {
                binding.contentText.visibility = View.GONE
                binding.titleText.text = getString(R.string.edit_post)
                type = PopupType.TWO_BUTTON
            }
        }

        setupPopupButtons()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            binding.imageButton.id -> {
                // open photo gallery
            }
        }
    }
}
