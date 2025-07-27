package com.example.callmethodapihit.data

import android.content.Context
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> getData(
    context: Context,
    condition: suspend () -> Response<T>,
    success : (T?) -> Unit,
    error: (String) -> Unit
) {
    try {
        val response: Response<T> =condition()
        if (response.isSuccessful) {
            var body = response.body()
            success.invoke(body)
        }
        else{
            error.invoke(response.code().toString())
        }
    } catch (e: SocketTimeoutException) {
        error.invoke("Connection timed out: ${e.message}")
    } catch (e: UnknownHostException) {
        error.invoke("No internet connection or unknown host: ${e.message}")

    } catch (e: ConnectException) {
        error.invoke("Connection refused: ${e.message}")
    } catch (e: NoRouteToHostException) {
        error.invoke("No route to host: ${e.message}")
    } catch (e: IOException) {
        error.invoke("IOException occurred: ${e.message}")
    } catch (e: Exception) {
        error.invoke("An unexpected error occurred: ${e.message}")
    }
}

