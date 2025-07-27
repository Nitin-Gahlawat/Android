package com.example.paggination.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class MyPagingSource(private val apiService: Map<Int, List<MyDataItem>>) :
    PagingSource<Int, MyDataItem>() {


    override fun getRefreshKey(state: PagingState<Int, MyDataItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyDataItem> {
        val page = params.key ?: 1
        return try {

            val data: List<MyDataItem> = apiService[page]!!
            data.forEach {
                it.pageNo = page
            }
            Log.d("helloworld",data.size.toString())

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (data.size == 15) page + 1 else null

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


}