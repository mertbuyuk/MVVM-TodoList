package com.mb.todolistmvvm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.mb.todolistmvvm.ui.tasks.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {


    fun getAllTask(task : String,sortOrder: SortOrder,hide:Boolean) =
        when(sortOrder){
            SortOrder.BY_DATE -> getTaskSortedByDate(task,hide)
            SortOrder.BY_NAME -> getTaskSortedByName(task,hide)
        }

    @Query("SELECT * FROM task WHERE (`check` != :hide OR `check`=0) AND task LIKE '%' || :task || '%' ORDER BY important DESC,task")
    fun getTaskSortedByName(task: String,hide : Boolean) : Flow<List<Task>>

    @Query("SELECT * FROM task WHERE (`check` != :hide OR `check`=0) AND task LIKE '%' || :task || '%' ORDER BY important DESC,created")
    fun getTaskSortedByDate(task : String, hide : Boolean) : Flow<List<Task>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)


}