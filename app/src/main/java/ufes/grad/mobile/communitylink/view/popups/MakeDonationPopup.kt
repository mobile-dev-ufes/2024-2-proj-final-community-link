package ufes.grad.mobile.communitylink.view.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.data.model.DonationStatusEnum
import ufes.grad.mobile.communitylink.databinding.PopupMakeDonationBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.MakeDonationVM
import ufes.grad.mobile.communitylink.viewmodel.ProjectPageVM

class MakeDonationPopup(private val projectId: String) :
    BasePopup(PopupType.TWO_BUTTON, R.layout.popup_make_donation), View.OnClickListener {

    private var _binding: PopupMakeDonationBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var donationVM: MakeDonationVM
    private lateinit var projectVM: ProjectPageVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        donationVM = ViewModelProvider(this)[MakeDonationVM::class.java]
        projectVM = ViewModelProvider(this)[ProjectPageVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupMakeDonationBinding.inflate(inflater, container, false)
        binding.pixButton.setOnClickListener(this)
        return root
    }

    /** Returns a DonationModel with the content input by the user */
    fun makeDonation(): DonationModel? {
        var objectName = binding.objectForms.editText.text.toString().trim()
        var value = binding.valueForms.editText.text.toString().toFloatOrNull()

        if (!objectName.isEmpty() && value != null) {
            Utilities.notify(context, "Preencha somente os campos do objeto ou do valor monetário!")
            return null
        } else if (value == null && objectName.isEmpty()) {
            Utilities.notify(context, "Preencha todos os campos necessários!")
            return null
        }

        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return DonationForProjectModel(
            value = value!!,
            objectName = objectName,
            status = DonationStatusEnum.PENDING,
            date = dateFormatter.format(Date()),
            project = projectVM.getProject().value!!
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            binding.pixButton.id -> {
                donationVM.copyToClipboard(projectVM.getProject().value!!.currentData.pixKey)
                Utilities.notify(context, getString(R.string.pix_to_clipboard))
            }
        }
    }
}
