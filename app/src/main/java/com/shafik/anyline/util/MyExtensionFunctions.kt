package com.shafik.anyline.util

import android.util.Log

object MyExtensionFunctions {
    /**
     * Print logs
     * @author Shafik Shaikh
     * */
    fun Any?.printToLog(tag: String = "DEBUG_LOG") {
        Log.e(tag, toString())
    }
}