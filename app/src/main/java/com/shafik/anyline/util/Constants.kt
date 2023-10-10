package com.shafik.anyline.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull

object Constants {
    private const val MIME_IMAGE_JPG = "image/jpeg"
    val mediaTypeImageJpg = MIME_IMAGE_JPG.toMediaTypeOrNull()!!
}