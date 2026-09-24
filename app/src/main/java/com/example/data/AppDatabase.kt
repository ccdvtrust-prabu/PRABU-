package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        VolunteerEntity::class,
        CommunityNeedEntity::class,
        NewsEntity::class,
        EventRsvpEntity::class,
        BeneficiaryRequestEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun volunteerDao(): VolunteerDao
    abstract fun communityNeedDao(): CommunityNeedDao
    abstract fun newsDao(): NewsDao
    abstract fun eventRsvpDao(): EventRsvpDao
    abstract fun beneficiaryRequestDao(): BeneficiaryRequestDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "delta_volunteers.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
