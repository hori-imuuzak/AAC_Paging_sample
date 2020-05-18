package app.imuuzak.aac_paging_sample.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.ui.paging.callback.OwnerListBoundaryCallback
import app.imuuzak.aac_paging_sample.ui.paging.datasource.OwnerDataSource
import app.imuuzak.aac_paging_sample.ui.paging.factory.OwnerDataSourceFactory

class OwnerListViewModel: ViewModel() {
    companion object {
        private const val PAGE_SIZE = 30
        private const val ENABLE_PLACEHOLDER = true
        private const val INITIAL_LOAD_SIZE_HINT = 40
        private const val PREFETCH_DISTANCE = 20
    }

    private var _ownerPagedList: LiveData<PagedList<Owner>>
    private var liveDataSource: LiveData<OwnerDataSource>

    val ownerPagedList
        get() = _ownerPagedList

    fun image(owner: Owner) = owner.avatarUrl
    fun name(owner: Owner) = owner.login
    fun webUrl(owner: Owner) = owner.htmlUrl

    init {
        val dataSourceFactory = OwnerDataSourceFactory(viewModelScope)
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDER)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .build()

        _ownerPagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
            .setBoundaryCallback(OwnerListBoundaryCallback())
            .build()
        liveDataSource = dataSourceFactory.dataSourceLiveData
    }
}