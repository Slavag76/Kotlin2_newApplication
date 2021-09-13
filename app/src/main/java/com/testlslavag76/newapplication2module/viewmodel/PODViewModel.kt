package com.testlslavag76.newapplication2module.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testlslavag76.newapplication2module.BuildConfig
import com.testlslavag76.newapplication2module.repository.PODRetrofitImpl
import com.testlslavag76.newapplication2module.repository.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PODViewModel(private val liveDataToObserve: MutableLiveData<PODData> = MutableLiveData(),
                   private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()):ViewModel() {
    fun getLiveData():LiveData<PODData>{
        return liveDataToObserve
    }

    fun sendServerRequest(){
        liveDataToObserve.postValue(PODData.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            //
        }else{
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(
                object : Callback<PODServerResponseData>{
                    override fun onResponse(
                        call: Call<PODServerResponseData>,
                        response: Response<PODServerResponseData>
                    ) {
                        if(response.isSuccessful && response.body()!=null){
                            liveDataToObserve.postValue(PODData.Success(response.body() as PODServerResponseData))
                        }else{
                            Log.e("Succsess", "отлично!");
                        }
                    }

                    override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                        Log.d("failure ", t.toString());
                    }

                }
            )
        }
    }
}