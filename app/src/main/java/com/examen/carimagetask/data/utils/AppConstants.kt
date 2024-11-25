package com.examen.carimagetask.data.utils

import android.widget.TextView

object AppConstants {
    const val BASE_URL = "https://674467b4b4e2e04abea21549.mockapi.io/"
    const val AMOUNT_REQUIRED ="Please enter the valid Amount!"
    const val REQUIRED_MSG ="The Total Amount should not Empty!"
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
}