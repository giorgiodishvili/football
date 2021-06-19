package com.android.football.extension

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.Color.BLACK
import android.view.Gravity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.android.football.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground))
        .centerCrop().into(this)
}

@SuppressLint("SimpleDateFormat")
fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM yyyy")
    return format.format(date)
}

fun View.changeVisibility(visible: Boolean){
    this.visibility = if(!visible) GONE else VISIBLE
}