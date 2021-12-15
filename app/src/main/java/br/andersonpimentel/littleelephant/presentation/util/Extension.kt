package br.andersonpimentel.littleelephant.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(image: Any){
    Glide.with(context)
        .load(image)
        .into(this)
}