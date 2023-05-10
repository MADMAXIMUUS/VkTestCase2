package ru.madmax.vktestcase2.domain.model

import ru.madmax.vktestcase2.presentation.FileItemState

data class FileModel(
    val id: Int = 0,
    val isFolder: Boolean = false,
    val name: String = "",
    val date: Long = 0,
    val type: String = "",
    val size: Long = 0,
    val children: List<FileModel>? = null
) {
    fun toFileItemState(): FileItemState {
        return FileItemState(
            id = id,
            isFolder = isFolder,
            name = name,
            date = date.toString(),
            type = type,
            size = size.toString(),
            children = children?.map(FileModel::toFileItemState)
        )
    }
}

