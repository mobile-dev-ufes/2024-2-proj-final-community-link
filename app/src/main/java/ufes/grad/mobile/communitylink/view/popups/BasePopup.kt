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

open class BasePopup(var type: PopupType, private val layout: Int) :
    DialogFragment(), View.OnClickListener {

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

    init {
        setCancelable(false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(true)
    }

    private fun setupDoubleButtons() {
        binding.confirmButton.setOnClickListener(this)
        binding.cancelButton.setOnClickListener(this)

        if (binding.confirmButton.lineCount > 1) {
            binding.buttonParent.orientation = VERTICAL

            val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 0, 10)

            binding.confirmButton.layoutParams = params
            binding.cancelButton.layoutParams = params
        }
    }

    /** Sets the possible button combinations for a popup. */
    fun setupPopupButtons() {
        when (type) {
            PopupType.ONE_BUTTON -> {
                binding.closeButton.setOnClickListener(this)
                binding.buttonParent.visibility = GONE
            }
            PopupType.TWO_BUTTON -> {
                binding.closeButton.visibility = GONE

                setupDoubleButtons()
            }
            PopupType.REMOVE_BUTTON -> {
                binding.closeButton.visibility = GONE
                binding.confirmButton.setBackgroundColor(requireContext().getColor(R.color.red))
                binding.confirmButton.text = getString(R.string.remove)
                binding.cancelButton.text = getString(R.string.close)

                setupDoubleButtons()
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

    /** Method executed when the 'Cancel' button is pressed on the popup'. */
    var onCancel: () -> Any = {}

    /** Method executed when the 'Close' button is pressed on the popup'. */
    var onClose: () -> Any = {}

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                onConfirm()
                dismiss()
            }
            binding.cancelButton.id -> {
                onCancel()
                dismiss()
            }
            binding.closeButton.id -> {
                onClose()
                dismiss()
            }
        }
    }
}
