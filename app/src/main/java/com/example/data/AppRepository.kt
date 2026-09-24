package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AppRepository(private val database: AppDatabase) {

    val allVolunteers: Flow<List<VolunteerEntity>> = database.volunteerDao().getAllVolunteers()
    val volunteerCount: Flow<Int> = database.volunteerDao().getVolunteerCount()
    val allCommunityNeeds: Flow<List<CommunityNeedEntity>> = database.communityNeedDao().getAllNeeds()
    val allNews: Flow<List<NewsEntity>> = database.newsDao().getAllNews()
    val allRsvps: Flow<List<EventRsvpEntity>> = database.eventRsvpDao().getAllRsvps()
    val allBeneficiaryRequests: Flow<List<BeneficiaryRequestEntity>> = database.beneficiaryRequestDao().getAllRequests()
    val beneficiaryRequestCount: Flow<Int> = database.beneficiaryRequestDao().getRequestCount()

    suspend fun registerVolunteer(volunteer: VolunteerEntity): Long {
        return database.volunteerDao().insertVolunteer(volunteer)
    }

    suspend fun reportCommunityNeed(need: CommunityNeedEntity): Long {
        return database.communityNeedDao().insertNeed(need)
    }

    suspend fun updateNeedStatus(id: Long, newStatus: String) {
        database.communityNeedDao().updateStatus(id, newStatus)
    }

    suspend fun submitBeneficiaryRequest(request: BeneficiaryRequestEntity): Long {
        return database.beneficiaryRequestDao().insertRequest(request)
    }

    suspend fun updateBeneficiaryRequest(request: BeneficiaryRequestEntity) {
        database.beneficiaryRequestDao().updateRequest(request)
    }

    suspend fun updateBeneficiaryOfficeReview(
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
    ) {
        database.beneficiaryRequestDao().updateOfficeReview(
            id = id,
            beneficiaryId = beneficiaryId,
            fieldVerificationRequired = fieldVerificationRequired,
            verificationOfficer = verificationOfficer,
            verificationDate = verificationDate,
            recommendation = recommendation,
            assistanceApproved = assistanceApproved,
            modeOfAssistance = modeOfAssistance,
            projectName = projectName,
            verifiedBy = verifiedBy,
            approvedBy = approvedBy,
            status = status
        )
    }

    suspend fun deleteBeneficiaryRequest(id: Long) {
        database.beneficiaryRequestDao().deleteRequestById(id)
    }

    suspend fun addNews(news: NewsEntity): Long {
        return database.newsDao().insertNews(news)
    }

    suspend fun deleteNews(id: Long) {
        database.newsDao().deleteNewsById(id)
    }

    suspend fun rsvpEvent(rsvp: EventRsvpEntity): Long {
        return database.eventRsvpDao().insertRsvp(rsvp)
    }

    suspend fun seedInitialDataIfEmpty() {
        val existingNews = database.newsDao().getAllNews().first()
        if (existingNews.isEmpty()) {
            database.newsDao().insertAll(AppDataStore.INITIAL_NEWS)
        }

        val existingRequests = database.beneficiaryRequestDao().getAllRequests().first()
        if (existingRequests.isEmpty()) {
            AppDataStore.INITIAL_BENEFICIARY_REQUESTS.forEach { req ->
                database.beneficiaryRequestDao().insertRequest(req)
            }
        }
    }
}
