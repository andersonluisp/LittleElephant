package br.andersonpimentel.littleelephant.map.util

import android.view.View
import android.widget.ImageView
import br.andersonpimentel.littleelephant.map.databinding.TooltipLayoutBinding
import com.bumptech.glide.Glide
import com.skydoves.balloon.Balloon

fun ImageView.load(image: Any){
    Glide.with(context)
        .load(image)
        .into(this)
}

fun Balloon.showToolTip(tooltip:View, anchorView: View){
    val viewPosition = IntArray(2)
    anchorView.getLocationOnScreen(viewPosition)
    val tooltipHeight = tooltip.layoutParams.height
    if (viewPosition[1] > tooltipHeight + anchorView.layoutParams.height){
        this.showAlignTop(anchorView)
    } else {
        this.showAlignBottom(anchorView)
    }
}

fun TooltipLayoutBinding.setMessage(message: String){
    this.tvMessage.text = message
}