package com.furkan.mobiversitechallenge.common.extension

import android.view.View

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.clickListener(onClick: () -> Unit) {
    this.setOnClickListener {
        onClick()
    }
}