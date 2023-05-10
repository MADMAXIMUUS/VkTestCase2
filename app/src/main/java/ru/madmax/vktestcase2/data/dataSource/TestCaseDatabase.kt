package ru.madmax.vktestcase2.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.madmax.vktestcase2.data.dataSource.entity.FIleEntity

@Database(
    entities = [
        FIleEntity::class
    ], version = 1
)
abstract class TestCaseDatabase : RoomDatabase() {

    abstract val fileDao: FileDao

    companion object {
        const val NAME = "TEST_CASE_DB"
    }
}