package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

enum class AppScreen {
    HOME,
    ABOUT,
    FOCUS_AREAS,
    VOLUNTEER,
    REPORT_NEED,
    BENEFICIARY_FORM,
    DISASTER,
    WATER,
    PROJECTS,
    EVENTS,
    GALLERY,
    NEWS,
    CONTACT,
    ADMIN
}

class DeltaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = AppRepository(db)
        viewModelScope.launch {
            repository.seedInitialDataIfEmpty()
        }
    }

    // Language state: true = Tamil (தமிழ்), false = English
    private val _isTamil = MutableStateFlow(false)
    val isTamil: StateFlow<Boolean> = _isTamil.asStateFlow()

    fun toggleLanguage() {
        _isTamil.value = !_isTamil.value
    }

    fun setLanguage(tamil: Boolean) {
        _isTamil.value = tamil
    }

    // Navigation screen
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    // Flows from database
    val volunteers: StateFlow<List<VolunteerEntity>> = repository.allVolunteers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val volunteerCount: StateFlow<Int> = repository.volunteerCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val communityNeeds: StateFlow<List<CommunityNeedEntity>> = repository.allCommunityNeeds
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val newsList: StateFlow<List<NewsEntity>> = repository.allNews
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val eventRsvps: StateFlow<List<EventRsvpEntity>> = repository.allRsvps
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val beneficiaryRequests: StateFlow<List<BeneficiaryRequestEntity>> = repository.allBeneficiaryRequests
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val beneficiaryRequestCount: StateFlow<Int> = repository.beneficiaryRequestCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Selection dialog states
    private val _selectedFocusArea = MutableStateFlow<FocusArea?>(null)
    val selectedFocusArea: StateFlow<FocusArea?> = _selectedFocusArea.asStateFlow()

    fun selectFocusArea(area: FocusArea?) {
        _selectedFocusArea.value = area
    }

    private val _selectedDisasterGuide = MutableStateFlow<DisasterGuide?>(null)
    val selectedDisasterGuide: StateFlow<DisasterGuide?> = _selectedDisasterGuide.asStateFlow()

    fun selectDisasterGuide(guide: DisasterGuide?) {
        _selectedDisasterGuide.value = guide
    }

    private val _selectedWaterTopic = MutableStateFlow<WaterTopic?>(null)
    val selectedWaterTopic: StateFlow<WaterTopic?> = _selectedWaterTopic.asStateFlow()

    fun selectWaterTopic(topic: WaterTopic?) {
        _selectedWaterTopic.value = topic
    }

    private val _selectedProject = MutableStateFlow<ProjectItem?>(null)
    val selectedProject: StateFlow<ProjectItem?> = _selectedProject.asStateFlow()

    fun selectProject(project: ProjectItem?) {
        _selectedProject.value = project
    }

    // Emergency Kit checklist states (saved in memory during session)
    private val _checkedKitItems = MutableStateFlow<Set<String>>(
        setOf("kit_1", "kit_2", "kit_4", "kit_6") // default essentials checked
    )
    val checkedKitItems: StateFlow<Set<String>> = _checkedKitItems.asStateFlow()

    fun toggleKitItem(itemId: String) {
        val current = _checkedKitItems.value.toMutableSet()
        if (current.contains(itemId)) {
            current.remove(itemId)
        } else {
            current.add(itemId)
        }
        _checkedKitItems.value = current
    }

    // Gallery filter
    private val _galleryCategory = MutableStateFlow("All")
    val galleryCategory: StateFlow<String> = _galleryCategory.asStateFlow()

    fun setGalleryCategory(cat: String) {
        _galleryCategory.value = cat
    }

    // Volunteer Form State
    var volName = MutableStateFlow("")
    var volMobile = MutableStateFlow("")
    var volEmail = MutableStateFlow("")
    var volDistrict = MutableStateFlow(AppDataStore.DELTA_DISTRICTS.first())
    var volTaluk = MutableStateFlow("")
    var volVillage = MutableStateFlow("")
    var volInterests = MutableStateFlow<Set<String>>(emptySet())
    var volSkills = MutableStateFlow("")
    var volAvailability = MutableStateFlow("Weekends / தேவைப்படும் போது")
    var volEmergencyExp = MutableStateFlow("Yes / ஆம்")
    var volunteerRegisteredCode = MutableStateFlow<String?>(null)
    var isVolunteerSubmitting = MutableStateFlow(false)
    var volunteerFormError = MutableStateFlow<String?>(null)

    fun toggleInterest(interest: String) {
        val current = volInterests.value.toMutableSet()
        if (current.contains(interest)) {
            current.remove(interest)
        } else {
            current.add(interest)
        }
        volInterests.value = current
    }

    fun submitVolunteerRegistration() {
        if (volName.value.isBlank()) {
            volunteerFormError.value = if (_isTamil.value) "தயவுசெய்து உங்கள் பெயரை உள்ளிடவும்" else "Please enter your name"
            return
        }
        if (volMobile.value.isBlank() || volMobile.value.length < 10) {
            volunteerFormError.value = if (_isTamil.value) "சரியான 10 இலக்க தொலைபேசி எண்ணை உள்ளிடவும்" else "Please enter a valid 10-digit mobile number"
            return
        }

        volunteerFormError.value = null
        isVolunteerSubmitting.value = true

        viewModelScope.launch {
            val code = "DVT-${(1000..9999).random()}"
            val entity = VolunteerEntity(
                registrationCode = code,
                name = volName.value.trim(),
                mobileNumber = volMobile.value.trim(),
                email = volEmail.value.trim(),
                district = volDistrict.value,
                taluk = volTaluk.value.trim(),
                village = volVillage.value.trim(),
                areasOfInterest = volInterests.value.joinToString(", "),
                skills = volSkills.value.trim(),
                availability = volAvailability.value,
                emergencyExperience = volEmergencyExp.value
            )
            repository.registerVolunteer(entity)
            volunteerRegisteredCode.value = code
            isVolunteerSubmitting.value = false

            // Reset inputs
            volName.value = ""
            volMobile.value = ""
            volEmail.value = ""
            volTaluk.value = ""
            volVillage.value = ""
            volSkills.value = ""
            volInterests.value = emptySet()
        }
    }

    fun dismissVolunteerSuccessDialog() {
        volunteerRegisteredCode.value = null
    }

    // Community Need Form State
    var needName = MutableStateFlow("")
    var needContact = MutableStateFlow("")
    var needDistrict = MutableStateFlow(AppDataStore.DELTA_DISTRICTS.first())
    var needLocation = MutableStateFlow("")
    var needCategory = MutableStateFlow("Drinking water problem (குடிநீர் பிரச்சினை)")
    var needDescription = MutableStateFlow("")
    var needUrgency = MutableStateFlow("High / அவசரம்")
    var needPhotoAttached = MutableStateFlow(false)
    var needSubmittedRef = MutableStateFlow<String?>(null)
    var isNeedSubmitting = MutableStateFlow(false)
    var needFormError = MutableStateFlow<String?>(null)

    fun submitCommunityNeed() {
        if (needName.value.isBlank()) {
            needFormError.value = if (_isTamil.value) "பெயரை உள்ளிடவும்" else "Please enter your name"
            return
        }
        if (needContact.value.isBlank()) {
            needFormError.value = if (_isTamil.value) "தொடர்பு எண்ணை உள்ளிடவும்" else "Please enter contact number"
            return
        }
        if (needLocation.value.isBlank()) {
            needFormError.value = if (_isTamil.value) "இடத்தின் முகவரி அல்லது கிராம பெயரை உள்ளிடவும்" else "Please specify the location or village"
            return
        }
        if (needDescription.value.isBlank()) {
            needFormError.value = if (_isTamil.value) "பிரச்சினையின் விவரங்களை குறிப்பிடவும்" else "Please describe the issue"
            return
        }

        needFormError.value = null
        isNeedSubmitting.value = true

        viewModelScope.launch {
            val refId = "REQ-DVT-${(1000..9999).random()}"
            val entity = CommunityNeedEntity(
                referenceId = refId,
                name = needName.value.trim(),
                contactNumber = needContact.value.trim(),
                district = needDistrict.value,
                location = needLocation.value.trim(),
                issueCategory = needCategory.value,
                description = needDescription.value.trim(),
                urgencyLevel = needUrgency.value,
                photoNote = if (needPhotoAttached.value) "Site photo attached (1 file)" else "",
                status = "Submitted"
            )
            repository.reportCommunityNeed(entity)
            needSubmittedRef.value = refId
            isNeedSubmitting.value = false

            // Reset
            needName.value = ""
            needContact.value = ""
            needLocation.value = ""
            needDescription.value = ""
            needPhotoAttached.value = false
        }
    }

    fun dismissNeedSuccessDialog() {
        needSubmittedRef.value = null
    }

    fun updateNeedStatus(id: Long, newStatus: String) {
        viewModelScope.launch {
            repository.updateNeedStatus(id, newStatus)
        }
    }

    // Admin News Creation
    var adminTitleEn = MutableStateFlow("")
    var adminTitleTa = MutableStateFlow("")
    var adminContentEn = MutableStateFlow("")
    var adminContentTa = MutableStateFlow("")
    var adminCategory = MutableStateFlow("Announcement")
    var adminIsUrgent = MutableStateFlow(false)
    var adminMessage = MutableStateFlow<String?>(null)

    fun addAdminNews() {
        if (adminTitleEn.value.isBlank() && adminTitleTa.value.isBlank()) {
            adminMessage.value = "Please enter announcement title"
            return
        }

        viewModelScope.launch {
            val date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())
            val news = NewsEntity(
                titleEn = if (adminTitleEn.value.isNotBlank()) adminTitleEn.value.trim() else adminTitleTa.value.trim(),
                titleTa = if (adminTitleTa.value.isNotBlank()) adminTitleTa.value.trim() else adminTitleEn.value.trim(),
                contentEn = if (adminContentEn.value.isNotBlank()) adminContentEn.value.trim() else adminContentTa.value.trim(),
                contentTa = if (adminContentTa.value.isNotBlank()) adminContentTa.value.trim() else adminContentEn.value.trim(),
                category = adminCategory.value,
                dateStr = date,
                isUrgent = adminIsUrgent.value
            )
            repository.addNews(news)
            adminMessage.value = "Update posted successfully!"

            // Clear
            adminTitleEn.value = ""
            adminTitleTa.value = ""
            adminContentEn.value = ""
            adminContentTa.value = ""
            adminIsUrgent.value = false
        }
    }

    // Event RSVP State
    private val _rsvpEvent = MutableStateFlow<EventItem?>(null)
    val rsvpEvent: StateFlow<EventItem?> = _rsvpEvent.asStateFlow()

    var rsvpName = MutableStateFlow("")
    var rsvpPhone = MutableStateFlow("")
    var rsvpDistrict = MutableStateFlow(AppDataStore.DELTA_DISTRICTS.first())
    var rsvpSuccessMessage = MutableStateFlow<String?>(null)

    fun openRsvpDialog(event: EventItem) {
        _rsvpEvent.value = event
        rsvpSuccessMessage.value = null
    }

    fun closeRsvpDialog() {
        _rsvpEvent.value = null
        rsvpSuccessMessage.value = null
        rsvpName.value = ""
        rsvpPhone.value = ""
    }

    fun submitRsvp() {
        val event = _rsvpEvent.value ?: return
        if (rsvpName.value.isBlank() || rsvpPhone.value.isBlank()) return

        viewModelScope.launch {
            val rsvp = EventRsvpEntity(
                eventId = event.id,
                eventTitle = event.titleEn,
                volunteerName = rsvpName.value.trim(),
                volunteerPhone = rsvpPhone.value.trim(),
                district = rsvpDistrict.value
            )
            repository.rsvpEvent(rsvp)
            rsvpSuccessMessage.value = if (_isTamil.value) {
                "நிகழ்வு பதிவு வெற்றிகரமாக முடிந்தது! விவரங்கள் SMS மூலம் அனுப்பப்படும்."
            } else {
                "Event RSVP Confirmed! Confirmation will be sent to your mobile."
            }
        }
    }

    // Beneficiary Assistance Request Form State
    val benFormNo = MutableStateFlow("DVT-BEN-${(1000..9999).random()}")
    val benFormDate = MutableStateFlow(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()))

    // 1. Applicant Details
    val benFullName = MutableStateFlow("")
    val benGuardianName = MutableStateFlow("")
    val benAgeOrDob = MutableStateFlow("")
    val benGender = MutableStateFlow("Male") // Male, Female, Other
    val benMobileNumber = MutableStateFlow("")
    val benAlternateContact = MutableStateFlow("")
    val benIdProofNumber = MutableStateFlow("")
    val benAddress = MutableStateFlow("")
    val benVillageTown = MutableStateFlow("")
    val benPanchayat = MutableStateFlow("")
    val benTaluk = MutableStateFlow("")
    val benDistrict = MutableStateFlow(AppDataStore.DELTA_DISTRICTS.first())
    val benPinCode = MutableStateFlow("")

    // 2. Family & Socio-Economic Details
    val benMaritalStatus = MutableStateFlow("Married") // Single, Married, Widowed, Deserted, Other
    val benFamilyMembersCount = MutableStateFlow("")
    val benChildrenCount = MutableStateFlow("")
    val benOccupation = MutableStateFlow("")
    val benMonthlyIncome = MutableStateFlow("")
    val benHousingStatus = MutableStateFlow("Own") // Own, Rental, Temporary, Other
    val benHasGovtAssistance = MutableStateFlow(false)
    val benGovtAssistanceDetails = MutableStateFlow("")

    // 3. Assistance Requested
    val benAssistanceTypes = MutableStateFlow<Set<String>>(emptySet())
    val benOtherAssistanceType = MutableStateFlow("")
    val benProblemDetails = MutableStateFlow("")
    val benUrgency = MutableStateFlow("Normal") // Normal, Urgent, Emergency

    // 4. Financial Requirement
    val benEstimatedAmount = MutableStateFlow("")
    val benApplicantContribution = MutableStateFlow("")
    val benRequestedAmount = MutableStateFlow("")

    // 5. Supporting Documents
    val benSupportingDocs = MutableStateFlow<Set<String>>(emptySet())
    val benOtherDocDetails = MutableStateFlow("")

    // 6. Declaration & Consent
    val benIsDeclarationAgreed = MutableStateFlow(true)
    val benSignatureName = MutableStateFlow("")
    val benDeclarationDate = MutableStateFlow(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()))
    val benDeclarationPlace = MutableStateFlow("Vedaranyam")

    val benIsSubmitting = MutableStateFlow(false)
    val benFormError = MutableStateFlow<String?>(null)
    val benSubmittedRequest = MutableStateFlow<BeneficiaryRequestEntity?>(null)

    fun toggleBenAssistanceType(type: String) {
        val current = benAssistanceTypes.value.toMutableSet()
        if (current.contains(type)) current.remove(type) else current.add(type)
        benAssistanceTypes.value = current
    }

    fun toggleBenSupportingDoc(doc: String) {
        val current = benSupportingDocs.value.toMutableSet()
        if (current.contains(doc)) current.remove(doc) else current.add(doc)
        benSupportingDocs.value = current
    }

    fun autoCalculateRequestedFromTrust() {
        val est = benEstimatedAmount.value.filter { it.isDigit() }.toLongOrNull() ?: 0L
        val cont = benApplicantContribution.value.filter { it.isDigit() }.toLongOrNull() ?: 0L
        val requested = (est - cont).coerceAtLeast(0L)
        if (est > 0) {
            benRequestedAmount.value = requested.toString()
        }
    }

    fun resetBeneficiaryForm() {
        benFormNo.value = "DVT-BEN-${(1000..9999).random()}"
        benFormDate.value = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        benFullName.value = ""
        benGuardianName.value = ""
        benAgeOrDob.value = ""
        benGender.value = "Male"
        benMobileNumber.value = ""
        benAlternateContact.value = ""
        benIdProofNumber.value = ""
        benAddress.value = ""
        benVillageTown.value = ""
        benPanchayat.value = ""
        benTaluk.value = ""
        benDistrict.value = AppDataStore.DELTA_DISTRICTS.first()
        benPinCode.value = ""
        benMaritalStatus.value = "Married"
        benFamilyMembersCount.value = ""
        benChildrenCount.value = ""
        benOccupation.value = ""
        benMonthlyIncome.value = ""
        benHousingStatus.value = "Own"
        benHasGovtAssistance.value = false
        benGovtAssistanceDetails.value = ""
        benAssistanceTypes.value = emptySet()
        benOtherAssistanceType.value = ""
        benProblemDetails.value = ""
        benUrgency.value = "Normal"
        benEstimatedAmount.value = ""
        benApplicantContribution.value = ""
        benRequestedAmount.value = ""
        benSupportingDocs.value = emptySet()
        benOtherDocDetails.value = ""
        benIsDeclarationAgreed.value = true
        benSignatureName.value = ""
        benDeclarationDate.value = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        benDeclarationPlace.value = "Vedaranyam"
        benFormError.value = null
        benSubmittedRequest.value = null
    }

    fun submitBeneficiaryForm(onSuccess: (() -> Unit)? = null) {
        if (benFullName.value.isBlank()) {
            benFormError.value = if (_isTamil.value) "பயனாளி முழுப் பெயரை உள்ளிடவும்" else "Please enter Applicant / Beneficiary Full Name"
            return
        }
        if (benMobileNumber.value.isBlank() || benMobileNumber.value.length < 10) {
            benFormError.value = if (_isTamil.value) "சரியான 10 இலக்க மொபைல் எண்ணை உள்ளிடவும்" else "Please enter a valid 10-digit mobile number"
            return
        }
        if (benVillageTown.value.isBlank()) {
            benFormError.value = if (_isTamil.value) "கிராமம் / ஊர் பெயரை உள்ளிடவும்" else "Please enter Village / Town"
            return
        }
        if (benAssistanceTypes.value.isEmpty() && benOtherAssistanceType.value.isBlank()) {
            benFormError.value = if (_isTamil.value) "குறைந்தது ஒரு உதவி வகையை தேர்வு செய்யவும்" else "Please select at least one type of assistance requested"
            return
        }
        if (benProblemDetails.value.isBlank()) {
            benFormError.value = if (_isTamil.value) "கோரிக்கை / பிரச்சினையின் விவரங்களை குறிப்பிடவும்" else "Please provide details of your request / problem"
            return
        }
        if (!benIsDeclarationAgreed.value) {
            benFormError.value = if (_isTamil.value) "உறுதிமொழி மற்றும் ஒப்புதலை ஏற்றுக்கொள்ளவும்" else "Please accept the Declaration & Consent"
            return
        }

        benFormError.value = null
        benIsSubmitting.value = true

        viewModelScope.launch {
            val autoBeneficiaryId = "BEN-${SimpleDateFormat("yyMM", Locale.getDefault()).format(Date())}-${(100..999).random()}"
            val request = BeneficiaryRequestEntity(
                formNo = benFormNo.value.ifBlank { "DVT-BEN-${(1000..9999).random()}" },
                formDate = benFormDate.value.ifBlank { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()) },
                fullName = benFullName.value.trim(),
                guardianName = benGuardianName.value.trim(),
                ageOrDob = benAgeOrDob.value.trim(),
                gender = benGender.value,
                mobileNumber = benMobileNumber.value.trim(),
                alternateContact = benAlternateContact.value.trim(),
                idProofNumber = benIdProofNumber.value.trim(),
                address = benAddress.value.trim(),
                villageTown = benVillageTown.value.trim(),
                panchayat = benPanchayat.value.trim(),
                taluk = benTaluk.value.trim(),
                district = benDistrict.value,
                pinCode = benPinCode.value.trim(),
                maritalStatus = benMaritalStatus.value,
                familyMembersCount = benFamilyMembersCount.value.trim(),
                childrenCount = benChildrenCount.value.trim(),
                occupation = benOccupation.value.trim(),
                monthlyIncome = benMonthlyIncome.value.trim(),
                housingStatus = benHousingStatus.value,
                hasGovtAssistance = benHasGovtAssistance.value,
                govtAssistanceDetails = benGovtAssistanceDetails.value.trim(),
                assistanceTypes = benAssistanceTypes.value.joinToString(", "),
                otherAssistanceType = benOtherAssistanceType.value.trim(),
                requestProblemDetails = benProblemDetails.value.trim(),
                urgency = benUrgency.value,
                estimatedAssistanceRequired = benEstimatedAmount.value.trim(),
                applicantContribution = benApplicantContribution.value.trim(),
                assistanceRequestedFromTrust = benRequestedAmount.value.trim(),
                supportingDocuments = benSupportingDocs.value.joinToString(", "),
                otherDocumentDetails = benOtherDocDetails.value.trim(),
                isDeclarationAgreed = benIsDeclarationAgreed.value,
                applicantSignatureName = benSignatureName.value.ifBlank { benFullName.value }.trim(),
                declarationDate = benDeclarationDate.value.ifBlank { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()) },
                declarationPlace = benDeclarationPlace.value.ifBlank { "Vedaranyam" }.trim(),
                beneficiaryId = autoBeneficiaryId,
                fieldVerificationRequired = true,
                recommendation = "Under Review",
                status = "Submitted"
            )

            val insertedId = repository.submitBeneficiaryRequest(request)
            val savedRequest = request.copy(id = insertedId)
            benSubmittedRequest.value = savedRequest
            benIsSubmitting.value = false
            onSuccess?.invoke()
        }
    }

    fun updateBeneficiaryOfficeReview(
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
        viewModelScope.launch {
            repository.updateBeneficiaryOfficeReview(
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
    }

    fun deleteBeneficiaryRequest(id: Long) {
        viewModelScope.launch {
            repository.deleteBeneficiaryRequest(id)
        }
    }

    // Emergency SOS Dialog
    private val _showSosDialog = MutableStateFlow(false)
    val showSosDialog: StateFlow<Boolean> = _showSosDialog.asStateFlow()

    fun openSosDialog() {
        _showSosDialog.value = true
    }

    fun closeSosDialog() {
        _showSosDialog.value = false
    }
}
