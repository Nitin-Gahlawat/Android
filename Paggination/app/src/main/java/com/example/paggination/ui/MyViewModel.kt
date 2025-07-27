package com.example.paggination.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow


class MyViewModel : ViewModel() {
    private val data: Map<Int, List<MyDataItem>> = (0 until 20).associate { row ->
        row to List(15) {
            MyDataItem(
                id = (1..1000).random(),
                pageNo = -1
            )
        }
    }
    val pagingDataFlow: Flow<PagingData<MyDataItem>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 2
        ),
        pagingSourceFactory = { MyPagingSource(data) }
    ).flow.cachedIn(viewModelScope)


}
