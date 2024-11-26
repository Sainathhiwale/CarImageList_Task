package com.examen.carimagetask.data.utils

import android.graphics.Color
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

object AppConstants {
    const val BASE_URL = "https://674467b4b4e2e04abea21549.mockapi.io/"
    const val AMOUNT_REQUIRED ="Please enter the valid Amount!"
    const val REQUIRED_MSG ="The Car price should not Empty!"
    const val CAR_NAME = "Please enter the Car name"
    const val CAR_BRAND = "Please enter the Car brand name!"
    //validation for enter amount
    fun hasValidAmountForTextView(textView: TextView): Boolean {
        val text = textView.text.toString().trim()
        textView.error = null
        textView.isFocusable = true

        if (text.isEmpty()) {
            textView.error = AMOUNT_REQUIRED
            return false
        }

        if (!text.matches(Regex("\\d+(\\.\\d+)?"))) {
            textView.error = "Invalid amount. Please enter a valid number."
            return false
        }

        val amount = text.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            textView.error = "Invalid amount. Please enter a positive number."
            return false
        }

        return true
    }

    fun hasEditTextCarName(editText: EditText): Boolean {
        val text = editText.text.toString().trim()
        if (text.isBlank()) {
            editText.error = CAR_NAME
        } else {
            editText.error = null
        }
        return text.isNotBlank()
    }
    fun hasEditTextCarBrand(editText: EditText): Boolean {
        val text = editText.text.toString().trim()
        if (text.isBlank()) {
            editText.error = CAR_BRAND
        } else {
            editText.error = null
        }
        return text.isNotBlank()
    }

    fun showSnackBar(view: View, message: String, color: Int) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(color) // set background color
        val textView = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(Color.BLACK) // set text color to white (or any other color that contrasts with the background)
        snackBar.show()
    }
}