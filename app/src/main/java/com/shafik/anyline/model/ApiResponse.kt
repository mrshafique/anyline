package com.shafik.anyline.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiResponse(
	@field:SerializedName("result")
	val result: String? = null
)