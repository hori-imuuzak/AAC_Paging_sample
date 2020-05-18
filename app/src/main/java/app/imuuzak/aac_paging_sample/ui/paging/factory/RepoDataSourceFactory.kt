package app.imuuzak.aac_paging_sample.ui.paging.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.data.Repo
import app.imuuzak.aac_paging_sample.ui.paging.datasource.RepoDataSource
import kotlinx.coroutines.CoroutineScope

class RepoDataSourceFactory(
    private val scope: CoroutineScope,
    private val owner: Owner
): DataSource.Factory<Int, Repo>() {
    private val _dataSourceLiveData = MutableLiveData<RepoDataSource>()
    val dataSourceLiveData: LiveData<RepoDataSource> = _dataSourceLiveData

    override fun create(): DataSource<Int, Repo> {
        val repoDataSource = RepoDataSource(scope, owner)

        _dataSourceLiveData.postValue(repoDataSource)

        return repoDataSource
    }
}