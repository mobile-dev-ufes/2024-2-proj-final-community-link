package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutSpinnerBinding
import ufes.grad.mobile.communitylink.databinding.LayoutSpinnerItemBinding

class SpinnerAdapter(context: Context, private val items: List<String>) :
    ArrayAdapter<String>(context, R.layout.layout_spinner, items) {

    private var invalid: Boolean = false

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =
            convertView
                ?: LayoutInflater.from(context).inflate(R.layout.layout_spinner, parent, false)
        val binding = LayoutSpinnerBinding.bind(view)

        binding.text.setTextColor(
            context.getColor(
                when {
                    invalid -> R.color.red
                    position == 0 -> R.color.gray
                    else -> R.color.black
                }
            )
        )
        binding.text.text = items[position]

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =
            convertView
                ?: LayoutInflater.from(context).inflate(R.layout.layout_spinner_item, parent, false)
        val binding = LayoutSpinnerItemBinding.bind(view)

        binding.text.setTextColor(
            context.getColor(
                when {
                    position == 0 -> R.color.gray
                    else -> R.color.black
                }
            )
        )
        binding.text.isEnabled = position > 0
        binding.text.text = items[position]

        return view
    }

    fun setValid(status: Boolean) {
        invalid = !status
        notifyDataSetChanged()
    }
}
