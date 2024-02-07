package com.codelife.hospital

object InputUtils {
    fun readInt(prompt: String, errorMessage: String): Int {
        println(prompt)
        while (true) {
            val input = readLine()
            try {
                return input!!.toInt()
            } catch (e: NumberFormatException) {
                println(errorMessage)
            }
        }
    }

    fun readString(prompt: String, errorMessage: String): String {
        println(prompt)
        while (true) {
            val input = readLine()
            try {
                return input.toString()
            } catch (e: NumberFormatException) {
                println(errorMessage)
            }
        }
    }

// Additional utility functions can be added here.



}
