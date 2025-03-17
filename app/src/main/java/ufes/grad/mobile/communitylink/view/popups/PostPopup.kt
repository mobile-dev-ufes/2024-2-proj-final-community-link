package ufes.grad.mobile.communitylink.view.popups

import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.databinding.PopupPostBinding
import ufes.grad.mobile.communitylink.utils.Utilities
import ufes.grad.mobile.communitylink.viewmodel.EditActionVM
import ufes.grad.mobile.communitylink.viewmodel.PostVM

class PostPopup(
    private val postId: String?,
    private val actionId: String,
    private val mode: PostMode
) : DialogFragment(R.layout.popup_post), View.OnClickListener {

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

    private lateinit var postVM: PostVM
    private lateinit var actionVM: EditActionVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postVM = ViewModelProvider(this)[PostVM::class.java]
        actionVM = ViewModelProvider(this)[EditActionVM::class.java]

        postVM.fetchPost(postId)
        actionVM.fetchAction(actionId)
    }

    override fun onResume() {
        super.onResume()
        val display: Display? = dialog?.window?.windowManager?.defaultDisplay
        var size: Point = Point()
        display?.getSize(size)
        dialog?.window?.setLayout((size.x * 0.9).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setGravity(Gravity.CENTER)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = PopupPostBinding.inflate(inflater, container, false)

        setObserver()
        setupLayout()

        return binding.root
    }

    private fun setObserver() {
        postVM
            .post()
            .observe(
                viewLifecycleOwner,
                Observer { it -> binding.contentForms.editText.setText(it.text) }
            )
    }

    fun setupLayout() {
        when (mode) {
            PostMode.VIEW -> {
                binding.contentForms.visibility = View.GONE
                binding.titleText.text = getString(R.string.post)
                binding.buttonParent.visibility = View.GONE
                binding.closeButton.setOnClickListener(this)
                binding.background.setBackgroundColor(requireContext().getColor(R.color.blue))
            }
            PostMode.NEW -> {
                binding.dateText.visibility = View.GONE
                binding.contentText.visibility = View.GONE
                binding.titleText.text = getString(R.string.new_post)
                binding.closeButton.visibility = View.GONE
                binding.confirmButton.setOnClickListener(this)
                binding.cancelButton.setOnClickListener(this)
            }
            PostMode.EDIT -> {
                binding.contentText.visibility = View.GONE
                binding.titleText.text = getString(R.string.edit_post)
                binding.closeButton.visibility = View.GONE
                binding.confirmButton.setOnClickListener(this)
                binding.cancelButton.setOnClickListener(this)
            }
        }
    }

    fun createPost(): Boolean {
        val content = binding.contentForms.editText.text.toString()
        if (content.isEmpty()) {
            Utilities.notify(context, getString(R.string.the_post_content_cannot_be_empty))
            return false
        }

        var post: PostModel
        if (postId != null) {
            post =
                PostModel(
                    id = postVM.post().value!!.id,
                    text = content,
                    date = postVM.post().value!!.date,
                    action = actionVM.getAction().value!!
                )
            return postVM.updatePost(post)
        } else {
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            post =
                PostModel(
                    text = content,
                    date = dateFormatter.format(Date()),
                    action = actionVM.getAction().value!!
                )
            return postVM.createNewPost(post)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.confirmButton.id -> {
                if (createPost()) dismiss()
            }
            binding.cancelButton.id,
            binding.closeButton.id -> {
                dismiss()
            }
        }
    }
}
