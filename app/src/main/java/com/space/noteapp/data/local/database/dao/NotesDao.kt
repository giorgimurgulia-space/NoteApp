package com.space.noteapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.noteapp.data.local.database.entitiy.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("delete from notes where id = :id")
    suspend fun deleteNote(id: Int)

    @Query("select * from notes")
    fun getNotesFlow(): Flow<List<NoteEntity>>

    @Query("select * from notes where id = :id")
    suspend fun selectById(id: Int): List<NoteEntity>
}
