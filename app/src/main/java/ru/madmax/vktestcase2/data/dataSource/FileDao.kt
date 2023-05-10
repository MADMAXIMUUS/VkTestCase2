package ru.madmax.vktestcase2.data.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.madmax.vktestcase2.data.dataSource.entity.FIleEntity

@Dao
interface FileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFiles(fIleEntity: FIleEntity)

}