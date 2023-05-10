package ru.madmax.vktestcase2.data.repository

import android.os.Environment
import ru.madmax.vktestcase2.data.util.OrderType
import ru.madmax.vktestcase2.data.util.SortProperty
import ru.madmax.vktestcase2.domain.model.FileModel
import ru.madmax.vktestcase2.domain.repository.FileRepository
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes

class FileRepositoryImpl : FileRepository {

    override fun getAllFiles(sortProperty: SortProperty): List<FileModel> {
        val file = Environment.getExternalStorageDirectory()
        return getAllFilesRecurs(file, sortProperty)
    }

    private fun getAllFilesRecurs(
        currentLocation: File,
        sortProperty: SortProperty
    ): List<FileModel> {
        val list = mutableListOf<FileModel>()
        currentLocation.listFiles()?.forEach { file ->
            if (!file.isDirectory) {
                val attr = Files
                    .readAttributes(
                        file.toPath(),
                        BasicFileAttributes::class.java
                    )
                list.add(
                    FileModel(
                        id = 0,
                        name = file.name,
                        isFolder = false,
                        date = attr.creationTime().toMillis(),
                        type = file.extension,
                        size = attr.size(),
                        children = null
                    )
                )
            } else {
                val childList = getAllFilesRecurs(file, sortProperty)
                val attr = Files
                    .readAttributes(
                        file.toPath(),
                        BasicFileAttributes::class.java
                    )
                list.add(
                    FileModel(
                        id = 0,
                        name = file.name,
                        isFolder = true,
                        date = attr.creationTime().toMillis(),
                        type = file.extension,
                        size = attr.size(),
                        children = childList
                    )
                )
            }
        }
        when (sortProperty) {
            is SortProperty.Date -> {
                when (sortProperty.orderType) {
                    OrderType.Ascending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenBy { it.date })
                    OrderType.Descending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenByDescending { it.date })
                }
            }

            is SortProperty.Extension -> {
                when (sortProperty.orderType) {
                    OrderType.Ascending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenBy { it.type })

                    OrderType.Descending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenByDescending { it.type })
                }
            }

            is SortProperty.Name -> {
                when (sortProperty.orderType) {
                    OrderType.Ascending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenBy { it.name })

                    OrderType.Descending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenByDescending { it.name })
                }
            }

            is SortProperty.Size -> {
                when (sortProperty.orderType) {
                    OrderType.Ascending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenBy { it.size })

                    OrderType.Descending -> list.sortWith(compareBy<FileModel> { !it.isFolder }.thenByDescending { it.size })
                }
            }
        }
        return list
    }
}