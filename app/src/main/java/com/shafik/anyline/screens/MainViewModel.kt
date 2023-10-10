package com.shafik.anyline.screens

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.shafik.anyline.model.ApiResponse
import com.shafik.anyline.util.MyExtensionFunctions.printToLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.UUID


class MainViewModel : ViewModel(), KoinComponent {
    val isButtonEnable = MutableLiveData<Boolean>()
    private val mainRepo: MainRepo by inject()
    private val context: Context by inject()
    val stateFlowLog = MutableStateFlow<String?>(null)
    lateinit var strUuid: String

    /**
     * - To initiate the API call, start by generating a random UUID (Universally Unique Identifier) which will be required for the request.
     * - While the API request is being executed, it's important to provide a responsive user interface. Disable the relevant button to prevent further user interaction and show a progress bar to indicate that the operation is in progress.
     * - Once all asset images have been successfully uploaded through the API, re-enable the button to allow users to take further action.
     * - Upload images one by one
     * @return String
     * @author Shafik Shaikh
     * */
    fun uploadImages() {
        viewModelScope.launch(Dispatchers.IO) {
            stateFlowLog.emit(null)
            strUuid = UUID.randomUUID().toString()
            isButtonEnable.postValue(false)

            stateFlowLog.emit("Image uploading started\nUuid: $strUuid\n")
            for (strImageName in context.assets.list("")!!) {
                if (strImageName.endsWith(".jpg"))
                    uploadImage(strImageName = strImageName)
            }
            finalizeApi()
            isButtonEnable.postValue(true)
        }
    }

    /**
     * Once all images are uploaded then execute this function to finish the process
     * @author Shafik Shaikh
     * */
    private suspend fun finalizeApi() {
        val callResp = mainRepo.finalizeApi(strUuid = strUuid)
        val resp = callResp.execute()
        if (resp.code() == 200)
            stateFlowLog.emit("Finish => ${resp.body()?.result.toString()}\n")
        else {
            //error
            val errorBody = resp.errorBody()
            val errorMessage = Gson()
                .fromJson(errorBody?.string(), ApiResponse::class.java)
            stateFlowLog.emit("Finish => ${errorMessage.result.toString()}\n")
        }
    }

    /**
     * - Convert image into byte array and pass in API request
     * - If we get error like 418 then again execute same function until we get success response
     * @author Shafik Shaikh
     * */
    private suspend fun uploadImage(strImageName: String) {
        val byteArrayImage = getImageByteArray(strImageName = strImageName)
        val callResp = mainRepo.uploadImage(
            strUuid = strUuid,
            byteArrayImage = byteArrayImage,
            strFileName = strImageName
        )
        val resp = callResp.execute()

        //successfully uploaded image
        if (resp.code() == 200)
            stateFlowLog.emit("$strImageName => ${resp.body()?.result.toString()}\n")
        //error to upload image
        else {
            //error
            val errorBody = resp.errorBody()
            val errorMessage = Gson()
                .fromJson(errorBody?.string(), ApiResponse::class.java)
            stateFlowLog.emit("$strImageName => ${errorMessage.result.toString()}\n")
            uploadImage(strImageName = strImageName)
        }
    }

    /**
     * Convert image which is in asset folder into byte array
     * @return ByteArray
     * @author Shafik Shaikh
     * */
    private fun getImageByteArray(strImageName: String): ByteArray {
        "getImageByteArray: strImageName: $strImageName".printToLog("mainVm")
        val inputStream: InputStream = context.assets.open(strImageName)

        val buffer = ByteArray(8192)
        var bytesRead: Int
        val output = ByteArrayOutputStream()
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
        return output.toByteArray()
    }
}