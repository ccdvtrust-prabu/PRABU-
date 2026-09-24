package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "volunteers")
data class VolunteerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val registrationCode: String,
    val name: String,
    val mobileNumber: String,
    val email: String,
    val district: String,
    val taluk: String,
    val village: String,
    val areasOfInterest: String,
    val skills: String,
    val availability: String,
    val emergencyExperience: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "community_needs")
data class CommunityNeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val referenceId: String,
    val name: String,
    val contactNumber: String,
    val district: String,
    val location: String,
    val issueCategory: String,
    val description: String,
    val urgencyLevel: String,
    val photoNote: String = "",
    val status: String = "Submitted", // "Submitted", "Under Verification", "Team Assigned", "Resolved"
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "news_updates")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titleEn: String,
    val titleTa: String,
    val contentEn: String,
    val contentTa: String,
    val category: String, // "Announcement", "Alert", "Project Update", "Volunteer Call", "Training"
    val dateStr: String,
    val isUrgent: Boolean = false,
    val author: String = "Delta Volunteers Trust",
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "event_rsvps")
data class EventRsvpEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val eventId: String,
    val eventTitle: String,
    val volunteerName: String,
    val volunteerPhone: String,
    val district: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "beneficiary_requests")
data class BeneficiaryRequestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val formNo: String,
    val formDate: String,
    
    // 1. Applicant / Beneficiary Details
    val fullName: String,
    val guardianName: String,
    val ageOrDob: String,
    val gender: String, // Male, Female, Other
    val mobileNumber: String,
    val alternateContact: String = "",
    val idProofNumber: String = "", // Aadhaar / ID Proof No.
    val address: String,
    val villageTown: String,
    val panchayat: String = "",
    val taluk: String = "",
    val district: String,
    val pinCode: String,

    // 2. Family & Socio-Economic Details
    val maritalStatus: String = "", // Single, Married, Widowed, Deserted, Other
    val familyMembersCount: String = "",
    val childrenCount: String = "",
    val occupation: String = "",
    val monthlyIncome: String = "", // Approx. Monthly Family Income: ₹
    val housingStatus: String = "", // Own, Rental, Temporary, Other
    val hasGovtAssistance: Boolean = false, // Any existing government assistance
    val govtAssistanceDetails: String = "",

    // 3. Assistance Requested
    val assistanceTypes: String, // Comma-separated: Education, Safe Drinking Water, Sanitation / Hygiene, Health / Medical Assistance, Food / Essential Needs, Disaster Relief, Livelihood Support, Disability / Special Needs, Women & Child Support, Elderly Support, Environmental / Community Support, Other
    val otherAssistanceType: String = "",
    val requestProblemDetails: String,
    val urgency: String = "Normal", // Normal, Urgent, Emergency

    // 4. Financial Requirement
    val estimatedAssistanceRequired: String = "", // ₹
    val applicantContribution: String = "", // ₹
    val assistanceRequestedFromTrust: String = "", // ₹

    // 5. Supporting Documents
    val supportingDocuments: String = "", // Comma-separated: Identity Proof, Address Proof, Income / BPL / Eligibility Proof, Medical Document, School / College Document, Photograph, Other
    val otherDocumentDetails: String = "",

    // 6. Declaration & Consent
    val isDeclarationAgreed: Boolean = true,
    val applicantSignatureName: String,
    val declarationDate: String,
    val declarationPlace: String,

    // FOR OFFICE USE ONLY
    val beneficiaryId: String = "",
    val fieldVerificationRequired: Boolean = true,
    val verificationOfficer: String = "",
    val verificationDate: String = "",
    val recommendation: String = "Under Review", // Under Review, Eligible, Not Eligible, Further Verification
    val assistanceApproved: String = "", // ₹
    val modeOfAssistance: String = "", // Material, Direct Service, Bank Transfer, Other
    val projectName: String = "", // Project / Programme Name
    val verifiedBy: String = "",
    val approvedBy: String = "",

    val status: String = "Submitted", // Submitted, Under Verification, Eligible, Approved, Rejected, Completed
    val timestamp: Long = System.currentTimeMillis()
)
