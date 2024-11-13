package ru.practicum.android.diploma.common

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import ru.practicum.android.diploma.util.ImageAndTextHelper
import ru.practicum.android.diploma.util.invisible
import ru.practicum.android.diploma.util.visible

class ImageAndTextHelperImpl : ImageAndTextHelper {
    override fun setImageAndText(
        context: Context,
        imageView: ImageView,
        textView: TextView,
        imageResId: Int,
        text: String
    ) {
        imageView.setImageResource(imageResId)
        textView.text = text
    }

    override fun setGroupVisibility(group: Group, isVisible: Boolean) {
        group.let { if (isVisible) it.visible() else it.invisible() }
    }
}
