package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.FragmentEditSlotBinding
import ufes.grad.mobile.communitylink.view.popups.BasePopup

class EditSlotFragment : Fragment(R.layout.fragment_edit_slot), View.OnClickListener {

    /** Determines the layout of the popup. */
    enum class SlotState {
        NEW,
        EMPTY,
        FILLED,
        DONE
    }

    private var _binding: FragmentEditSlotBinding? = null
    private val binding
        get() = _binding!!

    private var state: SlotState = SlotState.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        state = SlotState.entries.toTypedArray()[requireArguments().getInt("STATE")]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditSlotBinding.inflate(inflater, container, false)
        //        TODO("Get slot model")

        when (state) {
            SlotState.EMPTY -> {
                binding.filledParent.visibility = View.GONE
                binding.notesParent.visibility = View.GONE
            }
            SlotState.FILLED -> {
                binding.emptyParent.visibility = View.GONE
                binding.notesParent.visibility = View.GONE
            }
            SlotState.DONE -> {
                binding.filledParent.visibility = View.GONE
                binding.emptyParent.visibility = View.GONE
            }
            SlotState.NEW -> {
                binding.filledParent.visibility = View.GONE
                binding.notesParent.visibility = View.GONE
                binding.emptyParent.visibility = View.GONE
                binding.confirmButton.text = getString(R.string.create_new_slot)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                TODO("onClick 'Confirmar Alterações'")
                // atentar-se que são duas possibilidades de ação: criar e editar
            }
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_slot)
                //                TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
            binding.removeButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_remove_volunteer)
                //                TODO("Make popup functional")
                popup.show(childFragmentManager, "")
            }
            binding.notesButton.id -> {
                TODO("onClick 'Confirmar alterações'")
            }
        }
    }
}
