package com.doniapr.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.doniapr.moviecatalogue.data.source.remote.ApiResponse
import com.doniapr.moviecatalogue.data.source.remote.StatusResponse
import com.doniapr.moviecatalogue.utils.AppExecutors
import com.doniapr.moviecatalogue.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val executor: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource){

        }
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>){
        val apiResponse = createCall()

        result.addSource(dbSource) {
            result.value = Resource.loading(it)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    executor.diskIO().execute {
                        saveCallResult(response.body)
                        executor.mainThread().execute {
                            result.addSource(loadFromDB()) {
                                result.value = Resource.success(it)
                            }
                        }
                    }
                StatusResponse.EMPTY -> executor.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        result.value = Resource.error(response.message, it)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}