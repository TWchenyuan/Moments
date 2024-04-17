package com.thoughtworks.moments.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.data.network.MomentAPI
import com.thoughtworks.moments.data.toMoment

class MomentSource(private val momentAPI: MomentAPI) : PagingSource<Int, Moment>() {
  override fun getRefreshKey(state: PagingState<Int, Moment>): Int? {
    return null
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Moment> {
    return try {
      val nextPage = params.key ?: 1
      val moments = momentAPI.listMoments().mapNotNull { it.toMoment() }
      LoadResult.Page(
        data = moments,
        prevKey = if (nextPage == 1) null else nextPage - 1,
        nextKey = if (nextPage < 100) nextPage + 1 else null
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
}
