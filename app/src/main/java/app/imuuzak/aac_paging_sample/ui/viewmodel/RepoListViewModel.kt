package app.imuuzak.aac_paging_sample.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.data.Repo
import app.imuuzak.aac_paging_sample.ui.paging.factory.RepoDataSourceFactory

class RepoListViewModel : ViewModel() {
    companion object {
        private const val PAGE_SIZE = 10
        private const val ENABLE_PLACEHOLDER = true
        private const val INITIAL_LOAD_SIZE_HINT = 10
        private const val PREFETCH_DISTANCE = 8
    }

    private var _repoPagedList: LiveData<PagedList<Repo>>? = null
    val repoPagedList
        get() = _repoPagedList

    fun repoName(repo: Repo) = repo.name
    fun repoUrl(repo: Repo) = repo.htmlUrl

    var uiEvent: UIEvent? = null
    fun bindEvent(uiEvent: UIEvent) {
        this.uiEvent = uiEvent
    }

    fun onClickRepo(repo: Repo) {
        uiEvent?.onClickRepo(repo)
    }

    fun setOwner(owner: Owner) {
        if (_repoPagedList != null) {
            return
        }

        val dataSourceFactory = RepoDataSourceFactory(viewModelScope, owner)
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDER)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .build()

        _repoPagedList = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
            .build()
    }

    interface UIEvent {
        fun onClickRepo(repo: Repo)
    }
}