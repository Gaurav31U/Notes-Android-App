package com.demonic.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(note: Note)

    suspend fun delete(note: Note){
        delete(note.id)
    }

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM notes WHERE text = :text")
    suspend fun delete(text: String)

    @Query("UPDATE notes SET text = :text WHERE id = :id")
    suspend fun update(id: Int, text: String)

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun get(id: Int): Note

    @Query("SELECT * FROM notes WHERE text LIKE :text")
    suspend fun search(text: String): List<Note>

    @Query("DELETE FROM notes")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM notes")
    suspend fun count(): Int

    @Query("SELECT * FROM notes ORDER BY id DESC LIMIT 1")
    suspend fun getLast(): Note

    @Query("SELECT * FROM notes ORDER BY id ASC LIMIT 1")
    suspend fun getFirst(): Note

    @Query("SELECT * FROM notes ORDER BY id DESC LIMIT :limit")
    suspend fun getLast(limit: Int): List<Note>

    @Query("SELECT * FROM notes ORDER BY id ASC LIMIT :limit")
    suspend fun getFirst(limit: Int): List<Note>

    @Query("SELECT * FROM notes WHERE id > :id ORDER BY id ASC LIMIT :limit")
    suspend fun getNext(id: Int, limit: Int): List<Note>

    @Query("SELECT * FROM notes WHERE id < :id ORDER BY id DESC LIMIT :limit")
    suspend fun getPrevious(id: Int, limit: Int): List<Note>

    @Query("SELECT * FROM notes WHERE id = (SELECT MAX(id) FROM notes)")
    suspend fun getMax(): Note

    @Query("SELECT * FROM notes WHERE id = (SELECT MIN(id) FROM notes)")
    suspend fun getMin(): Note

    @Query("SELECT * FROM notes WHERE id = (SELECT id FROM notes ORDER BY id DESC LIMIT 1 OFFSET :offset)")
    suspend fun getNext(offset: Int): Note

    @Query("SELECT * FROM notes WHERE id = (SELECT id FROM notes ORDER BY id ASC LIMIT 1 OFFSET :offset)")
    suspend fun getPrevious(offset: Int): Note

}