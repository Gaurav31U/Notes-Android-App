package com.demonic.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAll()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }
    suspend fun delete(id: Int) {
        noteDao.delete(id)
    }
    suspend fun delete(text: String) {
        noteDao.delete(text)
    }
    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
    suspend fun update(id: Int, text: String) {
        noteDao.update(id, text)
    }
    suspend fun get(id: Int): Note {
        return noteDao.get(id)
    }
    suspend fun search(text: String): List<Note> {
        return noteDao.search(text)
    }
    suspend fun deleteAll() {
        noteDao.deleteAll()
    }
    suspend fun count(): Int {
        return noteDao.count()
    }
}
