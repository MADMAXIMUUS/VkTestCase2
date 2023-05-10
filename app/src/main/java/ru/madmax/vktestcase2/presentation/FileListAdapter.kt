package ru.madmax.vktestcase2.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.madmax.vktestcase2.R
import ru.madmax.vktestcase2.databinding.FolderFileItemBinding

class FileListAdapter :
    ListAdapter<FileItemState, FileListAdapter.FileViewHolder>(DiffUtilCallback) {

    inner class FileViewHolder(private val binding: FolderFileItemBinding) :
        ViewHolder(binding.root) {

        fun bind(fileItemState: FileItemState) {
            binding.apply {
                fileName.text = fileItemState.name
                if (fileItemState.isFolder) {
                    fileIcon.setImageResource(R.drawable.ic_folder)
                } else {
                    fileIcon.setImageResource(R.drawable.ic_file)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding =
            FolderFileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<FileItemState>() {
        override fun areItemsTheSame(oldItem: FileItemState, newItem: FileItemState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FileItemState, newItem: FileItemState): Boolean {
            return oldItem == newItem
        }

    }

}