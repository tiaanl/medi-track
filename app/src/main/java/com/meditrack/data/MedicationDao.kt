package com.meditrack.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicationDao {

    @Insert
    suspend fun insert(medication: Medication)

    @Query("SELECT * FROM medications ORDER BY name ASC")
    fun all(): LiveData<List<Medication>>
}
