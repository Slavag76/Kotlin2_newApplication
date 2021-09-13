package com.testlslavag76.newapplication2module.viewmodel

import com.testlslavag76.newapplication2module.repository.PODServerResponseData

sealed class PODData {
    data class Success(val serverResponseData: PODServerResponseData) : PODData()
    data class Error(val error: Throwable) : PODData()

    //data class Loading(val progress: Int?) : PODData()
    object Loading : PODData()
}