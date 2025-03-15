package ufes.grad.mobile.communitylink.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ufes.grad.mobile.communitylink.R
import ufes.grad.mobile.communitylink.databinding.LayoutGoalPostBinding

class GoalPostLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    enum class GoalPostType {
        HIT,
        MODIFIED,
        NEW
    }

    private var binding: LayoutGoalPostBinding =
        LayoutGoalPostBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setAttributes(context, attrs)
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val customAttributesStyle =
            context.obtainStyledAttributes(attrs, R.styleable.GoalPostLayout)
        val type = customAttributesStyle.getInt(R.styleable.GoalPostLayout_goal_post_type, 0)
        val date = customAttributesStyle.getString(R.styleable.GoalPostLayout_goal_post_date)
        val description =
            customAttributesStyle.getString(R.styleable.GoalPostLayout_goal_post_description)
        val footnote =
            customAttributesStyle.getString(R.styleable.GoalPostLayout_goal_post_footnote)

        customAttributesStyle.recycle()

        setValues(type, description!!, footnote, date!!)
    }

    fun setValues(type: Int, description: String, footnote: String? = null, date: String) {
        when (GoalPostType.entries.toTypedArray()[type]) {
            GoalPostType.HIT -> {
                binding.nameText.text = context.getString(R.string.goal_hit)
            }
            GoalPostType.MODIFIED -> {
                binding.nameText.text = context.getString(R.string.modified_goal)
            }
            GoalPostType.NEW -> {
                binding.nameText.text = context.getString(R.string.new_goal_created)
            }
        }

        binding.descriptionText.text = description
        binding.dateText.text = date

        if (footnote != null) binding.footnoteText.text = footnote
        else binding.footnoteText.visibility = GONE
    }
}
