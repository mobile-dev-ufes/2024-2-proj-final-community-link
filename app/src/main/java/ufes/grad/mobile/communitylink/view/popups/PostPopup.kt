package ufes.grad.mobile.communitylink.view.popups

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.PopupPostBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.PostVM

class PostPopup(
    private var post: PostModel?,
    private var action: ActionModel,
    private val mode: PostMode
) : BasePopup(PopupType.TWO_BUTTON, R.layout.popup_post), View.OnClickListener {

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

    private lateinit var viewModel: PostVM

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.setImage(result.data?.data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PostVM::class.java]
        setObserver()
    }

    fun setObserver() {
        viewModel.getImage().observe(this, Observer { binding.image.setImageURI(it) })
    }

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
                binding.contentText.text = post?.text
                viewModel.setImage(Uri.parse(post?.media))
                type = PopupType.ONE_BUTTON
            }
            PostMode.NEW -> {
                binding.dateText.visibility = View.GONE
                binding.contentText.visibility = View.GONE
                binding.titleText.text = getString(R.string.new_post)
                onConfirmDismiss = false
                type = PopupType.TWO_BUTTON
            }
            PostMode.EDIT -> {
                binding.contentText.visibility = View.GONE
                binding.titleText.text = getString(R.string.edit_post)
                viewModel.setImage(Uri.parse(post?.media))
                onConfirmDismiss = false
                type = PopupType.TWO_BUTTON
            }
        }

        setupPopupButtons()

        return root
    }

    fun createPost(): PostModel? {
        val content = binding.contentForms.editText.text.toString()
        if (content.isEmpty()) {
            Utilities.notify(context, getString(R.string.the_post_content_cannot_be_empty))
            return null
        }

        if (post != null) {
            return PostModel(
                id = post!!.id,
                text = content,
                date = post!!.date,
                media = binding.image.drawable.toString(),
                action = action
            )
        }
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return PostModel(
            text = content,
            date = dateFormatter.format(Date()),
            media = binding.image.drawable.toString(),
            action = action
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            binding.imageButton.id -> {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                resultLauncher.launch(intent)
            }
        }
    }
}
