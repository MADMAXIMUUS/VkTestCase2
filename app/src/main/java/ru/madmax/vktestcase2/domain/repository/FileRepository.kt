package ru.madmax.vktestcase2.domain.repository

import ru.madmax.vktestcase2.data.util.SortProperty
import ru.madmax.vktestcase2.domain.model.FileModel

interface FileRepository {

    fun getAllFiles(
        sortProperty: SortProperty
    ): List<FileModel>
}