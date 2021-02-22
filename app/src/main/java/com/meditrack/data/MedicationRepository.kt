package com.meditrack.data

import androidx.lifecycle.LiveData

class MedicationRepository(private val medicationDao: MedicationDao) {

    val all: LiveData<List<Medication>> = medicationDao.all()

    suspend fun addMedication(medication: Medication) {
        medicationDao.insert(medication)
    }

    suspend fun updateMedication(medication: Medication) {
        medicationDao.update(medication)
    }
}
