package com.evertonprdo.budgetwise.data.utils

import java.util.Date

object DateHelper {
    fun Date.parseToUnix() =
        this.time / 1000

    fun parseUnixToDate(unix: Long) =
        Date(unix * 1000)
}