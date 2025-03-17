package ufes.grad.mobile.communitylink.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.VolunteerSlotModel
import ufes.grad.mobile.communitylink.databinding.FragmentEditSlotBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.view.popups.BasePopup
import ufes.grad.mobile.communitylink.viewmodel.EditSlotVM
import ufes.grad.mobile.communitylink.viewmodel.EventPageVM

class EditSlotFragment : Fragment(R.layout.fragment_edit_slot), View.OnClickListener {

    private var _binding: FragmentEditSlotBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var slotVM: EditSlotVM
    private lateinit var eventVM: EventPageVM

    private val args: EditSlotFragmentArgs by navArgs()

    private var afterInit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        slotVM = ViewModelProvider(this)[EditSlotVM::class.java]
        eventVM = ViewModelProvider(this)[EventPageVM::class.java]
        if (args.newSlot) {
            eventVM.getEventById(args.id)
        } else {
            slotVM.fetchSlot(args.id)
            eventVM.getEventById(slotVM.getSlot().value?.action?.id!!)
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

        return binding.root
    }

    fun setObserver() {
        slotVM
            .getSlot()
            .observe(
                viewLifecycleOwner,
                Observer {
                    binding.nameForms.editText.setText(it?.position)
                    binding.startDateButton.text = it?.initDate
                    binding.endDateButton.text = it?.finishDate
                    binding.notesForms.editText.setText(it?.notes)
                    afterInit = slotVM.checkIfAfterStart()
                }
            )
    }

    fun setupLayout() {
        binding.startDateButton.setOnClickListener(this)
        binding.endDateButton.setOnClickListener(this)

        if (slotVM.getSlot().value == null) {
            binding.filledParent.visibility = View.GONE
            binding.emptyParent.visibility = View.GONE
            binding.confirmButton.text = getString(R.string.create_new_slot)
        } else {
            if (afterInit) {
                binding.filledParent.visibility = View.GONE
                binding.emptyParent.visibility = View.GONE
            } else if (slotVM.getSlot().value?.filledBy == null) {
                binding.filledParent.visibility = View.GONE
                binding.notesForms.visibility = View.GONE
            } else {
                binding.emptyParent.visibility = View.GONE
                binding.notesForms.visibility = View.GONE
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

                if (position.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    Utilities.notify(requireContext(), getString(R.string.preencha_todos_os_campos))
                    return
                }

                val slot =
                    VolunteerSlotModel(
                        position = position,
                        initDate = startDate,
                        finishDate = endDate,
                        place = eventVM.getEvent().value?.places!!,
                        action = eventVM.getEvent().value!!
                    )

                if (slotVM.getSlot().value == null) slotVM.createSlot(slot)
                else slotVM.updateSlot(slot)
            }
            binding.deleteButton.id -> {
                val popup = BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_delete_slot)
                popup.onConfirm =
                    {
                        // TODO("Delete slot")
                    }
                popup.show(childFragmentManager, "")
            }
            binding.removeButton.id -> {
                val popup =
                    BasePopup(BasePopup.PopupType.TWO_BUTTON, R.layout.popup_remove_volunteer)
                popup.onConfirm = { slotVM.removeVolunteer() }
                popup.show(childFragmentManager, "")
            }
            binding.startDateButton.id -> {
                val datePickerDialog =
                    slotVM.showDatePicker(
                        binding.startDateButton.text.toString(),
                        { time -> binding.startDateButton.text = time }
                    )
                val calendar = Calendar.getInstance()
                datePickerDialog.datePicker.minDate = calendar.timeInMillis
                datePickerDialog.show()
            }
            binding.endDateButton.id -> {
                val datePickerDialog =
                    slotVM.showDatePicker(
                        binding.endDateButton.text.toString(),
                        { time -> binding.endDateButton.text = time }
                    )
                val startTime =
                    LocalDateTime.parse(
                        binding.startDateButton.text.toString(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    )
                datePickerDialog.datePicker.minDate = startTime.toEpochSecond(ZoneOffset.UTC)
                datePickerDialog.show()
            }
        }
    }
}
