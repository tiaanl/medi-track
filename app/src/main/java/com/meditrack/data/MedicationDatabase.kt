package com.meditrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Medication::class], version = 1)
abstract class MedicationDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: MedicationDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MedicationDatabase {
            val temp = instance
            if (temp != null) {
                return temp
            }

            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicationDatabase::class.java,
                    "medication_database"
                ).build()

                instance = newInstance
                return newInstance
            }
        }
    }

    abstract fun medicationDao(): MedicationDao
}
