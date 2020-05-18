package app.imuuzak.aac_paging_sample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.imuuzak.aac_paging_sample.data.Owner

class OwnerDetailViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _webUrl = MutableLiveData<String>()
    val webUrl: LiveData<String> = _webUrl

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    fun setOwner(owner: Owner) {
        _name.value = owner.login
        _webUrl.value = owner.htmlUrl
        _image.value = owner.avatarUrl
    }
}