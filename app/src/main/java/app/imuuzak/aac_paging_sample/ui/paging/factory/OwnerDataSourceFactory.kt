package app.imuuzak.aac_paging_sample.ui.paging.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.ui.paging.datasource.OwnerDataSource
import kotlinx.coroutines.CoroutineScope

class OwnerDataSourceFactory(private val scope: CoroutineScope): DataSource.Factory<Int, Owner>() {
    private val _dataSourceLiveData = MutableLiveData<OwnerDataSource>()
    val dataSourceLiveData: LiveData<OwnerDataSource> = _dataSourceLiveData

    override fun create(): DataSource<Int, Owner> {
        val ownerDataSource = OwnerDataSource(scope)

        _dataSourceLiveData.postValue(ownerDataSource)

        return ownerDataSource
    }
}