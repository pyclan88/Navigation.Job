package ru.practicum.android.diploma.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group

interface ImageAndTextHelper {

    fun setImageAndText(
        context: Context,
        imageView: ImageView,
        textView: TextView,
        imageResId: Int,
        text: String
    )

    fun setGroupVisibility(group: Group, isVisible: Boolean)

}
