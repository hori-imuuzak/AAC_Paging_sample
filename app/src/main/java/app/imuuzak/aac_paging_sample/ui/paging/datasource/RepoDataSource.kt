package app.imuuzak.aac_paging_sample.ui.paging.datasource

import androidx.paging.ItemKeyedDataSource
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.data.Repo
import app.imuuzak.aac_paging_sample.service.GithubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RepoDataSource(
    private val scope: CoroutineScope,
    private val owner: Owner
) : ItemKeyedDataSource<Int, Repo>() {
    private val githubService = GithubService()

    override fun getKey(item: Repo): Int {
        return item.id
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Repo>
    ) {
        scope.launch {
            val repos = githubService.getRepos(owner, 1, params.requestedLoadSize)
            callback.onResult(repos)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Repo>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Repo>) {
        scope.launch {
            val repos = githubService.getRepos(owner, params.key, params.requestedLoadSize)
            callback.onResult(repos)
        }
    }
}