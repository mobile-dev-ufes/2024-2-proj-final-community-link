package ufes.grad.mobile.communitylink.view.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.LinearLayout.*
import androidx.fragment.app.DialogFragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.PopupBaseBinding

open class BasePopup(
    var type: PopupType,
    private val layout: Int,
    private val is_purple: Boolean = true
) : DialogFragment(), View.OnClickListener {

    /**
     * Possible types of popup lower buttons:
     * - ONE_BUTTON: one 'Close' button
     * - TWO_BUTTON: two buttons, 'Cancel' and 'Confirm'
     * - REMOVE_BUTTON: special case, changes style of 'Confirm' to 'Remove' and 'Cancel' to 'Close'
     */
    enum class PopupType {
        ONE_BUTTON,
        TWO_BUTTON,
        REMOVE_BUTTON
    }

    private var _binding: PopupBaseBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(true)
        val params = dialog?.window?.attributes
        params?.width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        dialog?.window?.attributes = params
    }

    /** Sets the possible button combinations for a popup. */
    fun setupPopupButtons() {
        if (!is_purple) {
            binding.closeButton.setTextColor(requireContext().getColor(R.color.blue))
            binding.cancelButton.setTextColor(requireContext().getColor(R.color.blue))
        }

        when (type) {
            PopupType.ONE_BUTTON -> {
                binding.closeButton.setOnClickListener(this)
                binding.buttonParent.visibility = GONE
                binding.closeButton.visibility = VISIBLE
            }
            PopupType.TWO_BUTTON -> {
                binding.closeButton.visibility = GONE
                binding.confirmButton.setOnClickListener(this)
                binding.cancelButton.setOnClickListener(this)
            }
            PopupType.REMOVE_BUTTON -> {
                binding.closeButton.visibility = GONE
                binding.confirmButton.setBackgroundColor(requireContext().getColor(R.color.red))
                binding.confirmButton.text = getString(R.string.remove)
                binding.cancelButton.text = getString(R.string.close)
                binding.confirmButton.setOnClickListener(this)
                binding.cancelButton.setOnClickListener(this)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupBaseBinding.inflate(inflater, container, false)

        setupPopupButtons()

        val container: FrameLayout = binding.container
        container.addView(inflater.inflate(layout, container, false))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /** Method executed when the 'Confirm' button is pressed on the popup'. */
    var onConfirm: () -> Any = {}
    var onConfirmDismiss: Boolean = true

    /** Method executed when the 'Cancel' button is pressed on the popup'. */
    var onCancel: () -> Any = {}
    var onCancelDismiss: Boolean = true

    /** Method executed when the 'Close' button is pressed on the popup'. */
    var onClose: () -> Any = {}
    var onCloseDismiss: Boolean = true

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                onConfirm()
                if (onConfirmDismiss) dismiss()
            }
            binding.cancelButton.id -> {
                onCancel()
                if (onCancelDismiss) dismiss()
            }
            binding.closeButton.id -> {
                onClose()
                if (onCloseDismiss) dismiss()
            }
        }
    }
}
