package app.imuuzak.aac_paging_sample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.aac_paging_sample.R
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.databinding.ActivityOwnerListBinding
import app.imuuzak.aac_paging_sample.ui.adapter.OwnerPagedListAdapter
import app.imuuzak.aac_paging_sample.ui.viewmodel.OwnerListViewModel

class OwnerListActivity : AppCompatActivity() {

    private lateinit var ownerPagedListAdapter: OwnerPagedListAdapter
    private val viewModel: OwnerListViewModel by lazy(LazyThreadSafetyMode.NONE) {
        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ViewModelProvider(this, factory).get(OwnerListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityOwnerListBinding>(
            this,
            R.layout.activity_owner_list
        )

        binding.lifecycleOwner = this

        ownerPagedListAdapter = OwnerPagedListAdapter(viewModel)

        viewModel.bindEvent(object: OwnerListViewModel.UIEvent {
            override fun onClickOwner(owner: Owner) {
                OwnerDetailActivity.start(this@OwnerListActivity, owner)
            }
        })

        binding.viewModel = viewModel
        binding.recyclerView.adapter = ownerPagedListAdapter
        binding.recyclerView.setHasFixedSize(true)

        observe()
    }

    private fun observe() {
        viewModel.ownerPagedList.removeObservers(this)
        viewModel.ownerPagedList.observe(this, Observer {
            ownerPagedListAdapter.submitList(it)
        })
    }
}
