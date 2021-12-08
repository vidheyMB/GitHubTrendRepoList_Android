package com.example.testgithubapp.network.repository

import android.util.Log
import android.widget.Toast
import com.example.testgithubapp.ApplicationClass
import com.example.testgithubapp.network.Internet
import com.example.testgithubapp.network.InternetLoader
import retrofit2.Response

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String): T? {

        return when (val result = apiOutput(call, error)) {
            // return API response
            is Output.Success -> result.output
            // return error msg
            is Output.Error -> {
                // print error
                Log.e("Error", "The $error and the ${result.exception}")
                // return null
                null
            }
        }
    }

    private suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>,
        error: String
    ): Output<T> {

        // check internet connection before API call
        return if (Internet.isNetworkConnected()) {

            // Progress loader show
            loader(true)

            // call the network API request
            val response = call.invoke()

            // Progress loader hide
            loader(false)

            // 200 -> Ok
            return if (response.isSuccessful) {

                if (response.body() != null)
                    Output.Success(response.body()!!)
                else
                    Output.Error(Exception(response.errorBody().toString()))

            } else {
                if(ApplicationClass.mCurrentActivity != null) {
                    ApplicationClass.mCurrentActivity!!.runOnUiThread(Runnable {
                        Toast.makeText(ApplicationClass.mCurrentActivity, "Something went wrong try again later !! \n code : ${response.code()}", Toast.LENGTH_LONG).show()
                    })
                }

                Output.Error(Exception("OOps ... Something went wrong due to  ${response.code()} : ${response.raw()}"))
            }

        } else {

            if(ApplicationClass.mCurrentActivity != null) {
                ApplicationClass.mCurrentActivity!!.runOnUiThread(Runnable {
                    Toast.makeText(ApplicationClass.mCurrentActivity, "Check Internet Connection !!!", Toast.LENGTH_LONG).show()
                })
            }

            Output.Error(Exception("OOps ... Check Internet connection"))
        }

    }

    // Load progress dialog
    private fun loader(isLoad: Boolean) {
        if(ApplicationClass.mCurrentActivity != null) {
            if (isLoad) {
                // Loader display
                ApplicationClass.mCurrentActivity!!.runOnUiThread(Runnable { InternetLoader.showDialog(ApplicationClass.mCurrentActivity!!) })
            } else {
                // Loader hide
                ApplicationClass.mCurrentActivity!!.runOnUiThread(Runnable { InternetLoader.dismissDialog() })
            }
        }
    }

}