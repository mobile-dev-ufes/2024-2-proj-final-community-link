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
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.BaseModel
import ufes.grad.mobile.communitylink.data.model.DonationForActionModel
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel
import ufes.grad.mobile.communitylink.data.model.DonationModel
import ufes.grad.mobile.communitylink.data.model.DonationStatusEnum
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.databinding.PopupMakeDonationBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.MakeDonationVM

class MakeDonationPopup :
    BasePopup(PopupType.TWO_BUTTON, R.layout.popup_make_donation), View.OnClickListener {

    private var _binding: PopupMakeDonationBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: MakeDonationVM

    private lateinit var projectOrAction: BaseModel
    private lateinit var pixKey: String

    fun setModel(model: BaseModel) {
        projectOrAction = model
        if (model as? ProjectModel != null) pixKey = model.currentData.pixKey
        else if (model as? ActionDonationModel != null) pixKey = model.project.currentData.pixKey
        else pixKey = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MakeDonationVM::class.java]
        onConfirmDismiss = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupMakeDonationBinding.inflate(inflater, container, false)
        return root
    }

    /** Returns a DonationModel with the content input by the user */
    fun makeDonation(): DonationModel? {
        var objectName = binding.objectForms.editText.text.toString().trim()
        val quantity = binding.quantityForms.editText.text.toString().toFloatOrNull()
        var value = binding.valueForms.editText.text.toString().toFloatOrNull()

        if ((!objectName.isEmpty() || quantity != null) && value != null) {
            Utilities.notify(context, "Preencha somente os campos do objeto ou do valor monetário!")
            return null
        } else if (
            (quantity == null && !objectName.isEmpty()) ||
                (quantity != null && objectName.isEmpty()) ||
                (value == null && quantity == null && objectName.isEmpty())
        ) {
            Utilities.notify(context, "Preencha todos os campos necessários!")
            return null
        }

        if (quantity != null) value = quantity else objectName = ""

        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return when (projectOrAction) {
            is ProjectModel ->
                DonationForProjectModel(
                    value = value!!,
                    objectName = objectName,
                    status = DonationStatusEnum.PENDING,
                    date = dateFormatter.format(Date()),
                    project = projectOrAction as ProjectModel
                )
            is ActionDonationModel ->
                DonationForActionModel(
                    value = value!!,
                    objectName = objectName,
                    status = DonationStatusEnum.PENDING,
                    date = dateFormatter.format(Date()),
                    action = projectOrAction as ActionDonationModel
                )
            else -> null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            binding.pixButton.id -> {
                viewModel.copyToClipboard(pixKey)
                Utilities.notify(context, getString(R.string.pix_to_clipboard))
            }
        }
    }
}
