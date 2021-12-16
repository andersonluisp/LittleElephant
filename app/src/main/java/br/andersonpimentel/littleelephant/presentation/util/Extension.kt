package br.andersonpimentel.littleelephant.presentation.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.skydoves.balloon.Balloon

fun ImageView.load(image: Any){
    Glide.with(context)
        .load(image)
        .into(this)
}

fun Balloon.showToolTip(view: View){
    val viewPosition = IntArray(2)
    view.getLocationOnScreen(viewPosition)
    if (viewPosition[1] > 510){
        this.showAlignTop(view)
    } else {
        this.showAlignBottom(view)
    }
}