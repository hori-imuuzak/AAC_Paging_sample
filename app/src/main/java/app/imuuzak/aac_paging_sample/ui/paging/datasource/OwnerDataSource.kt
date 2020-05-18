package app.imuuzak.aac_paging_sample.ui.paging.datasource

import androidx.paging.ItemKeyedDataSource
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.service.GithubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class OwnerDataSource(private val scope: CoroutineScope): ItemKeyedDataSource<Int, Owner>() {
    private val githubService = GithubService()

    override fun getKey(item: Owner): Int {
        return item.id
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Owner>) {
        scope.launch {
            val owners = githubService.getOwners(1, params.requestedLoadSize)
            callback.onResult(owners)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Owner>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Owner>) {
        scope.launch {
            val owners = githubService.getOwners(params.key, params.requestedLoadSize)
            callback.onResult(owners)
        }
    }
}