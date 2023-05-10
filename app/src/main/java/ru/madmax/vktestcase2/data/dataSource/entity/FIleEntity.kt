package ru.madmax.vktestcase2.data.dataSource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FILES")
data class FIleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?=null,
    val hashcode: Int = 0
)
