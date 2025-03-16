package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.database.StaticData
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.VolunteerSlotModel
import ufes.grad.mobile.communitylink.databinding.FragmentEditSlotBinding
import ufes.grad.mobile.communitylink.ui.components.SpinnerAdapter
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.popups.BasePopup
import ufes.grad.mobile.communitylink.viewmodel.EditSlotVM

class EditSlotFragment : Fragment(R.layout.fragment_edit_slot), View.OnClickListener {

    private var _binding: FragmentEditSlotBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: EditSlotVM

    private val args: EditSlotFragmentArgs by navArgs()

    private var slot: VolunteerSlotModel? = null
    private lateinit var event: ActionEventModel
    private var slotDone: Boolean = false

    private var selectedPlace: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditSlotVM::class.java]

        // TODO("Get slot from BD")
        if (args.newSlot) {
            event = StaticData.eventActions[0]
            slotDone = false
        } else {
            slot = StaticData.slots[0]
            event = slot!!.action
            val startTime =
                LocalDateTime.parse(
                    slot!!.initDate,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                )
            slotDone = startTime <= LocalDateTime.now()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentEditSlotBinding.inflate(inflater, container, false)

        setObserver()
        setupLayout()
        setupDropdown()

        return binding.root
    }

    fun setObserver() {
        viewModel
            .getStartDate()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.startDateButton.text =
                        if (it == null) getString(R.string.selecione_uma_data) else it
                }
            )
        viewModel
            .getEndDate()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.endDateButton.text =
                        if (it == null) getString(R.string.selecione_uma_data) else it
                }
            )
    }

    fun setupLayout() {
        if (slot == null) {
            binding.filledParent.visibility = View.GONE
            binding.notesParent.visibility = View.GONE
            binding.emptyParent.visibility = View.GONE
            binding.confirmButton.text = getString(R.string.create_new_slot)
        } else {
            if (slotDone) {
                binding.filledParent.visibility = View.GONE
                binding.emptyParent.visibility = View.GONE
            } else if (slot!!.filledBy == null) {
                binding.filledParent.visibility = View.GONE
                binding.notesParent.visibility = View.GONE
            } else {
                binding.emptyParent.visibility = View.GONE
                binding.notesParent.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                val position = binding.nameForms.editText.text.toString().trim()
                val startDate = binding.startDateButton.text.toString()
                val endDate = binding.startDateButton.text.toString()
                val place = selectedPlace

                if (
                    place.isEmpty() ||
                        position.isEmpty() ||
                        startDate.isEmpty() ||
                        endDate.isEmpty()
                ) {
                    Utilities.notify(requireContext(), getString(R.string.preencha_todos_os_campos))
                    return
                }

                TODO("Create or modify slot")
                val slot: VolunteerSlotModel
                if (this.slot == null) {
                    slot =
                        VolunteerSlotModel(
                            position = position,
                            initDate = startDate,
                            finishDate = endDate,
                            place = place,
                            action = event
                        )
                } else {
                    this.slot!!.position = position
                    this.slot!!.initDate = startDate
                    this.slot!!.finishDate = endDate
                    this.slot!!.place = place
                }
            }
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_slot)
                popup.onConfirm = {
                    // TODO("Delete slot")
                    this.slot!!
                }
                popup.show(childFragmentManager, "")
            }
            binding.removeButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_remove_volunteer)
                popup.onConfirm = {
                    // TODO("Remove volunteer")
                    this.slot?.filledBy = null
                }
                popup.show(childFragmentManager, "")
            }
            binding.notesButton.id -> {
                // TODO("Modify slot data")
                this.slot?.notes = binding.notesForms.editText.text.toString().trim()
            }
            binding.startDateButton.id -> {
                viewModel.changeStartDate()
            }
            binding.endDateButton.id -> {
                viewModel.changeEndDate()
            }
        }
    }

    fun setupDropdown() {
        val status = listOf(getString(R.string.select_a_place)) + event.places
        binding.placeDropdown.adapter = SpinnerAdapter(requireContext(), status)
        binding.placeDropdown.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedPlace = if (position > 1) status[position - 1] else ""
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }
}
