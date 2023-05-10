package ru.madmax.vktestcase2.presentation

data class FileItemState(
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val type: String = "",
    val isFolder: Boolean = false,
    val isChanged: Boolean = false,
    val size: String = "",
    val children: List<FileItemState>? = null
)
