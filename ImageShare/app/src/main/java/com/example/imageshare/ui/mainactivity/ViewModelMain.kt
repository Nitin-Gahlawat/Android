package com.example.imageshare.ui.mainactivity

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageshare.di.EditProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(private var instanceApi: Retrofit) : ViewModel() {


    private fun getPathFromUri(context: Context, uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri!!, projection, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val filePath = cursor.getString(columnIndex)
            cursor.close()
            return filePath
        }
        return null
    }


    fun sendDataToServer(img: Uri, context: Context) {
        val imageFile = getPathFromUri(context, img)?.let { File(it) }
        val apiSend: EditProfile = instanceApi.create(EditProfile::class.java)

        val hashMap = HashMap<String, RequestBody>()
        hashMap["username"] = "test123".toRequestsBody()
        hashMap["email"] = "test45652@gmail.com".toRequestsBody()
        hashMap["country"] = "test".toRequestsBody()
        hashMap["occupation"] = "test".toRequestsBody()
        hashMap["gender"] = "test".toRequestsBody()
        hashMap["dob"] = "2025-1-1".toRequestsBody()
        hashMap["phone_no"] = "1232412331".toRequestsBody()
        hashMap["interests[]"] = "Sleeping,Eating".toRequestsBody()
        hashMap["hobbies[]"] = "test,asdas,abcabc".toRequestsBody()
        hashMap["is_advice"] = "0".toRequestsBody()
        hashMap["about"] = "asdgasdhvsadj".toRequestsBody()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiSend.editProfileNew(
                    map = hashMap,
                    profilePic = imageFile.createMultipart(
                        fileName = "profile_pic"
                    )
                )

            } catch (exception: Exception) {
                Log.d("newerror","this is a exception ${exception}")
                exception.printStackTrace()
            }
        }
    }

    private fun String.toRequestsBody(): RequestBody {
        return this.toRequestBody("*/*".toMediaTypeOrNull())
    }

    private fun File?.createMultipart(
        fileName: String
    ): MultipartBody.Part {
        return if (this != null) {
            val requestBody = this.asRequestBody("*/*".toMediaTypeOrNull())
            return MultipartBody.Part.createFormData(fileName, this.name, requestBody)
        } else {
            val requestBody = "".toRequestBody("*/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(fileName, null, requestBody)
        }
    }

}
