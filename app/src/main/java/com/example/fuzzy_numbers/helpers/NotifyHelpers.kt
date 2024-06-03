package com.example.fuzzy_numbers.helpers

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class NotifyHelpers {

    companion object{
        fun showSnackBarNotify(view: View, message: String, color: Int){
            val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction("Action", null)
            val sbView = snackBar.view
            sbView.setBackgroundColor(ContextCompat.getColor(view.context, color));
            snackBar.show()
        }
    }
}