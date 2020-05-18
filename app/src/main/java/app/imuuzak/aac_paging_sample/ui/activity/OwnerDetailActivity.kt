package app.imuuzak.aac_paging_sample.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.aac_paging_sample.R
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.data.Repo
import app.imuuzak.aac_paging_sample.databinding.ActivityOwnerDetailBinding
import app.imuuzak.aac_paging_sample.ui.adapter.RepoPagedListAdapter
import app.imuuzak.aac_paging_sample.ui.viewmodel.OwnerDetailViewModel
import app.imuuzak.aac_paging_sample.ui.viewmodel.RepoListViewModel

class OwnerDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_KEY_OWNER = "OwnerDetailActivity.owner"
        fun start(from: Context, owner: Owner) {
            val intent = Intent(from, OwnerDetailActivity::class.java)
            intent.putExtra(EXTRA_KEY_OWNER, owner)
            from.startActivity(intent)
        }
    }

    private lateinit var repoPagedListAdapter: RepoPagedListAdapter
    private val ownerDetailViewModel: OwnerDetailViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this).get(OwnerDetailViewModel::class.java)
    }
    private val repoListViewModel: RepoListViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this).get(RepoListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val owner = intent.getSerializableExtra(EXTRA_KEY_OWNER) as Owner

        val binding = DataBindingUtil.setContentView<ActivityOwnerDetailBinding>(
            this,
            R.layout.activity_owner_detail
        )

        binding.lifecycleOwner = this

        repoPagedListAdapter = RepoPagedListAdapter(repoListViewModel)

        ownerDetailViewModel.setOwner(owner)
        repoListViewModel.setOwner(owner)

        binding.viewModel = ownerDetailViewModel
        binding.repoRecyclerView.adapter = repoPagedListAdapter
        binding.repoRecyclerView.setHasFixedSize(true)

        repoListViewModel.bindEvent(object: RepoListViewModel.UIEvent {
            override fun onClickRepo(repo: Repo) {
            }
        })

        observe()
    }

    private fun observe() {
        repoListViewModel.repoPagedList?.removeObservers(this)
        repoListViewModel.repoPagedList?.observe(this, Observer {
            repoPagedListAdapter.submitList(it)
        })
    }
}
