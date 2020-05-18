package app.imuuzak.aac_paging_sample.ui.paging.callback

import androidx.paging.PagedList
import app.imuuzak.aac_paging_sample.data.Owner

class OwnerListBoundaryCallback : PagedList.BoundaryCallback<Owner>() {
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Owner) {
        super.onItemAtEndLoaded(itemAtEnd)
    }

    override fun onItemAtFrontLoaded(itemAtFront: Owner) {
        super.onItemAtFrontLoaded(itemAtFront)
    }
}