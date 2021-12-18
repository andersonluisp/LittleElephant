package br.andersonpimentel.littleelephant.data.remote.util

import android.content.Context
import android.content.Intent

class SuccessRemoteFetch(private val context: Context) {

    fun sendSuccessMessage(){
        context.sendBroadcast( Intent("ShowToast"))
    }
}