package com.meditrack.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MedicationRepository

    val all: LiveData<List<Medication>>

    init {
        val medicationDao = MedicationDatabase.getInstance(application).medicationDao()
        repository = MedicationRepository(medicationDao)
        all = repository.all
    }

    fun addMedication(medication: Medication) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMedication(medication)
        }
    }

    fun updateMedication(medication: Medication) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMedication(medication)
        }
    }

    fun deleteMedication(medication: Medication) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedication(medication)
        }
    }
}
