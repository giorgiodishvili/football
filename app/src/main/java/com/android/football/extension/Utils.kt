package com.android.football.extension

import android.annotation.SuppressLint
import android.app.Dialog
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
    val format = SimpleDateFormat("yyyy.MM.dd")
    return format.format(date)
}


fun Dialog.setUp(binding: ViewBinding, color: Int, height: Int) {

    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(binding.root)
    window!!.setBackgroundDrawableResource(color)
    val lp = WindowManager.LayoutParams()
    lp.copyFrom(window!!.attributes)
    lp.width = WindowManager.LayoutParams.MATCH_PARENT
    lp.height = height
    lp.gravity = Gravity.CENTER
    window!!.attributes = lp
}

fun View.changeVisibility(visible: Boolean){
    this.visibility = if(!visible) GONE else VISIBLE
}