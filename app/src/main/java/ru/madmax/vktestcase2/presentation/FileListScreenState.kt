package ru.madmax.vktestcase2.presentation

import ru.madmax.vktestcase2.data.util.SortProperty

data class FileListScreenState(
    val files: List<FileItemState> = emptyList(),
    val sortProperty: SortProperty = SortProperty.Name()
)