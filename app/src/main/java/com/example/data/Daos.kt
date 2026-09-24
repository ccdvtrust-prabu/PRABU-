package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VolunteerDao {
    @Query("SELECT * FROM volunteers ORDER BY timestamp DESC")
    fun getAllVolunteers(): Flow<List<VolunteerEntity>>

    @Query("SELECT COUNT(*) FROM volunteers")
    fun getVolunteerCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVolunteer(volunteer: VolunteerEntity): Long

    @Query("SELECT * FROM volunteers WHERE id = :id LIMIT 1")
    suspend fun getVolunteerById(id: Long): VolunteerEntity?
}

@Dao
interface CommunityNeedDao {
    @Query("SELECT * FROM community_needs ORDER BY timestamp DESC")
    fun getAllNeeds(): Flow<List<CommunityNeedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNeed(need: CommunityNeedEntity): Long

    @Update
    suspend fun updateNeed(need: CommunityNeedEntity)

    @Query("SELECT * FROM community_needs WHERE id = :id LIMIT 1")
    suspend fun getNeedById(id: Long): CommunityNeedEntity?

    @Query("UPDATE community_needs SET status = :newStatus WHERE id = :id")
    suspend fun updateStatus(id: Long, newStatus: String)
}

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_updates ORDER BY timestamp DESC")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsList: List<NewsEntity>)

    @Query("DELETE FROM news_updates WHERE id = :id")
    suspend fun deleteNewsById(id: Long)
}

@Dao
interface EventRsvpDao {
    @Query("SELECT * FROM event_rsvps ORDER BY timestamp DESC")
    fun getAllRsvps(): Flow<List<EventRsvpEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRsvp(rsvp: EventRsvpEntity): Long

    @Query("SELECT COUNT(*) FROM event_rsvps WHERE eventId = :eventId")
    fun getRsvpCountForEvent(eventId: String): Flow<Int>
}

@Dao
interface BeneficiaryRequestDao {
    @Query("SELECT * FROM beneficiary_requests ORDER BY timestamp DESC")
    fun getAllRequests(): Flow<List<BeneficiaryRequestEntity>>

    @Query("SELECT * FROM beneficiary_requests WHERE id = :id LIMIT 1")
    suspend fun getRequestById(id: Long): BeneficiaryRequestEntity?

    @Query("SELECT * FROM beneficiary_requests WHERE formNo = :formNo LIMIT 1")
    suspend fun getRequestByFormNo(formNo: String): BeneficiaryRequestEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: BeneficiaryRequestEntity): Long

    @Update
    suspend fun updateRequest(request: BeneficiaryRequestEntity)

    @Query("DELETE FROM beneficiary_requests WHERE id = :id")
    suspend fun deleteRequestById(id: Long)

    @Query("SELECT COUNT(*) FROM beneficiary_requests")
    fun getRequestCount(): Flow<Int>

    @Query("""
        UPDATE beneficiary_requests SET 
            beneficiaryId = :beneficiaryId,
            fieldVerificationRequired = :fieldVerificationRequired,
            verificationOfficer = :verificationOfficer,
            verificationDate = :verificationDate,
            recommendation = :recommendation,
            assistanceApproved = :assistanceApproved,
            modeOfAssistance = :modeOfAssistance,
            projectName = :projectName,
            verifiedBy = :verifiedBy,
            approvedBy = :approvedBy,
            status = :status
        WHERE id = :id
    """)
    suspend fun updateOfficeReview(
        id: Long,
        beneficiaryId: String,
        fieldVerificationRequired: Boolean,
        verificationOfficer: String,
        verificationDate: String,
        recommendation: String,
        assistanceApproved: String,
        modeOfAssistance: String,
        projectName: String,
        verifiedBy: String,
        approvedBy: String,
        status: String
    )
}

