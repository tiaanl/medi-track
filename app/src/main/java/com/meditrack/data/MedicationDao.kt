package com.meditrack.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MedicationDao {

    @Insert
    suspend fun insert(medication: Medication)

    @Update
    suspend fun update(medication: Medication)

    @Query("SELECT * FROM medications ORDER BY name ASC")
    fun all(): LiveData<List<Medication>>
}
