package app.imuuzak.aac_paging_sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.aac_paging_sample.R
import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.databinding.OwnerListItemBinding
import app.imuuzak.aac_paging_sample.ui.viewmodel.OwnerListViewModel

class OwnerPagedListAdapter(private val viewModel: OwnerListViewModel) :
    PagedListAdapter<Owner, OwnerPagedListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<OwnerListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.owner_list_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.let {
            getItem(position)?.let { owner ->
                it.owner = owner
                it.viewModel = viewModel
                it.executePendingBindings()
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Owner>() {
            override fun areItemsTheSame(oldItem: Owner, newItem: Owner): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Owner, newItem: Owner): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: OwnerListItemBinding) : RecyclerView.ViewHolder(binding.root)
}