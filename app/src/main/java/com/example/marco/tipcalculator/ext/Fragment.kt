@file:Suppress("NOTHING_TO_INLINE", "unused")

// Aliases to other public API.

package com.example.marco.tipcalculator.ext

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

inline fun Fragment.makeSnackBar(@StringRes resId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    view?.let { makeSnackBar(it.resources.getString(resId), duration) }
}

inline fun Fragment.makeSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    view?.let {
        Snackbar.make(it, message, duration).show()
    }
}
