package app.imuuzak.aac_paging_sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.aac_paging_sample.R
import app.imuuzak.aac_paging_sample.data.Repo
import app.imuuzak.aac_paging_sample.databinding.RepoListItemBinding
import app.imuuzak.aac_paging_sample.ui.viewmodel.RepoListViewModel

class RepoPagedListAdapter(private val viewModel: RepoListViewModel) :
    PagedListAdapter<Repo, RepoPagedListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<RepoListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.repo_list_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.let {
            getItem(position)?.let { repo ->
                it.repo = repo
                it.viewModel = viewModel
                it.executePendingBindings()
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: RepoListItemBinding) : RecyclerView.ViewHolder(binding.root)
}