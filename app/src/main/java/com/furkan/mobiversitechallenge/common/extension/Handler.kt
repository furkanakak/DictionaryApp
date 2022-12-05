package com.furkan.mobiversitechallenge.common.extension

import android.os.Handler

fun Handler.postDelayed(delayMillis: Long, action: () -> Unit) {
    postDelayed(Runnable(action), delayMillis)
}