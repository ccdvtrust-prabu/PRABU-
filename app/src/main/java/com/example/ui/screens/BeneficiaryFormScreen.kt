package com.example.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.AppDataStore
import com.example.data.BeneficiaryRequestEntity
import com.example.ui.DeltaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeneficiaryFormScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(0) } // 0: Fill Form, 1: Track Requests

    val beneficiaryRequests by viewModel.beneficiaryRequests.collectAsState()
    val submittedItem by viewModel.benSubmittedRequest.collectAsState()
    val isSubmitting by viewModel.benIsSubmitting.collectAsState()
    val formError by viewModel.benFormError.collectAsState()

    // Form fields
    val formNo by viewModel.benFormNo.collectAsState()
    val formDate by viewModel.benFormDate.collectAsState()
    val fullName by viewModel.benFullName.collectAsState()
    val guardianName by viewModel.benGuardianName.collectAsState()
    val ageOrDob by viewModel.benAgeOrDob.collectAsState()
    val gender by viewModel.benGender.collectAsState()
    val mobileNumber by viewModel.benMobileNumber.collectAsState()
    val alternateContact by viewModel.benAlternateContact.collectAsState()
    val idProofNumber by viewModel.benIdProofNumber.collectAsState()
    val address by viewModel.benAddress.collectAsState()
    val villageTown by viewModel.benVillageTown.collectAsState()
    val panchayat by viewModel.benPanchayat.collectAsState()
    val taluk by viewModel.benTaluk.collectAsState()
    val district by viewModel.benDistrict.collectAsState()
    val pinCode by viewModel.benPinCode.collectAsState()

    val maritalStatus by viewModel.benMaritalStatus.collectAsState()
    val familyMembersCount by viewModel.benFamilyMembersCount.collectAsState()
    val childrenCount by viewModel.benChildrenCount.collectAsState()
    val occupation by viewModel.benOccupation.collectAsState()
    val monthlyIncome by viewModel.benMonthlyIncome.collectAsState()
    val housingStatus by viewModel.benHousingStatus.collectAsState()
    val hasGovtAssistance by viewModel.benHasGovtAssistance.collectAsState()
    val govtAssistanceDetails by viewModel.benGovtAssistanceDetails.collectAsState()

    val selectedAssistanceTypes by viewModel.benAssistanceTypes.collectAsState()
    val otherAssistanceType by viewModel.benOtherAssistanceType.collectAsState()
    val problemDetails by viewModel.benProblemDetails.collectAsState()
    val urgency by viewModel.benUrgency.collectAsState()

    val estimatedAmount by viewModel.benEstimatedAmount.collectAsState()
    val applicantContribution by viewModel.benApplicantContribution.collectAsState()
    val requestedAmount by viewModel.benRequestedAmount.collectAsState()

    val supportingDocs by viewModel.benSupportingDocs.collectAsState()
    val otherDocDetails by viewModel.benOtherDocDetails.collectAsState()

    val isDeclarationAgreed by viewModel.benIsDeclarationAgreed.collectAsState()
    val signatureName by viewModel.benSignatureName.collectAsState()
    val declarationDate by viewModel.benDeclarationDate.collectAsState()
    val declarationPlace by viewModel.benDeclarationPlace.collectAsState()

    var districtExpanded by remember { mutableStateOf(false) }
    var maritalExpanded by remember { mutableStateOf(false) }

    val genderOptions = listOf(
        Pair("Male", if (isTamil) "ஆண்" else "Male"),
        Pair("Female", if (isTamil) "பெண்" else "Female"),
        Pair("Other", if (isTamil) "பிறர்" else "Other")
    )

    val housingOptions = listOf(
        Pair("Own", if (isTamil) "சொந்த வீடு" else "Own"),
        Pair("Rental", if (isTamil) "வாடகை" else "Rental"),
        Pair("Temporary", if (isTamil) "தற்காலிக குடிசை" else "Temporary"),
        Pair("Other", if (isTamil) "பிற" else "Other")
    )

    val urgencyOptions = listOf(
        Pair("Normal", if (isTamil) "சாதாரண" else "Normal"),
        Pair("Urgent", if (isTamil) "அவசரம்" else "Urgent"),
        Pair("Emergency", if (isTamil) "அவசரகால நிவாரணம்" else "Emergency")
    )

    val maritalOptions = listOf(
        Pair("Single", if (isTamil) "திருமணமாகாதவர்" else "Single"),
        Pair("Married", if (isTamil) "திருமணமானவர்" else "Married"),
        Pair("Widowed", if (isTamil) "விதவை / கணவனை இழந்தவர்" else "Widowed"),
        Pair("Deserted", if (isTamil) "கைவிடப்பட்டவர் / பிரிந்தவர்" else "Deserted / Divorced"),
        Pair("Other", if (isTamil) "பிற" else "Other")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("beneficiary_form_screen"),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Trust & Form Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = "📋", fontSize = 24.sp)
                                }
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isTamil) "பயனாளி உதவி கோரும் படிவம்" else "Beneficiary Assistance Request Form",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = if (isTamil) AppDataStore.TRUST_NAME_TA else AppDataStore.TRUST_NAME,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = if (isTamil)
                                "கல்வி, குடிநீர், சுகாதாரம், மருத்துவம், உணவு, பேரிடர் மற்றும் வாழ்வாதார உதவிக்கான அதிகாரப்பூர்வ விண்ணப்பப் படிவம். விண்ணப்பம் பெறப்பட்டவுடன் அறக்கட்டளை களப்பணியாளர் நேரில் சரிபார்த்து உதவி வழங்குவார்."
                            else
                                "Official application for Education, Drinking Water, Sanitation, Health, Food, Disaster Relief, and Livelihood assistance. Field coordinators verify all submissions before approval.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f), RoundedCornerShape(10.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Form No: $formNo",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Date: $formDate",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // Tabs for Form vs Track Applications
            item {
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.Transparent,
                    divider = {}
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = {
                            Text(
                                text = if (isTamil) "விண்ணப்பப் படிவம்" else "Application Form",
                                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = {
                            Text(
                                text = "${if (isTamil) "கோரிக்கைகள் நிலை" else "Track Status"} (${beneficiaryRequests.size})",
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            if (selectedTab == 0) {
                // Error message if validation fails
                formError?.let { err ->
                    item {
                        Surface(
                            color = MaterialTheme.colorScheme.errorContainer,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Error,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = err,
                                    color = MaterialTheme.colorScheme.onErrorContainer,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                // ==================== SECTION 1 ====================
                item {
                    SectionCard(
                        sectionNumber = "1",
                        titleEn = "Applicant / Beneficiary Details",
                        titleTa = "விண்ணப்பதாரர் / பயனாளி விவரங்கள்"
                    ) {
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = { viewModel.benFullName.value = it },
                            label = { Text(if (isTamil) "முழுப் பெயர் *" else "Full Name *") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                            modifier = Modifier.fillMaxWidth().testTag("ben_full_name"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = guardianName,
                            onValueChange = { viewModel.benGuardianName.value = it },
                            label = { Text(if (isTamil) "தந்தை / தாய் / பாதுகாவலர் பெயர்" else "Father / Mother / Guardian Name") },
                            leadingIcon = { Icon(Icons.Default.FamilyRestroom, contentDescription = null) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = ageOrDob,
                                onValueChange = { viewModel.benAgeOrDob.value = it },
                                label = { Text(if (isTamil) "வயது / பிறந்த தேதி" else "Age / Date of Birth") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )

                            // Gender Chips
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isTamil) "பாலினம்:" else "Gender:",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    genderOptions.forEach { (key, label) ->
                                        FilterChip(
                                            selected = gender == key,
                                            onClick = { viewModel.benGender.value = key },
                                            label = { Text(label, fontSize = 11.sp) }
                                        )
                                    }
                                }
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = mobileNumber,
                                onValueChange = { viewModel.benMobileNumber.value = it },
                                label = { Text(if (isTamil) "கைபேசி எண் *" else "Mobile Number *") },
                                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                                modifier = Modifier.weight(1f).testTag("ben_mobile"),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = alternateContact,
                                onValueChange = { viewModel.benAlternateContact.value = it },
                                label = { Text(if (isTamil) "மாற்று தொடர்பு எண்" else "Alternate Contact") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        OutlinedTextField(
                            value = idProofNumber,
                            onValueChange = { viewModel.benIdProofNumber.value = it },
                            label = { Text(if (isTamil) "ஆதார் / அடையாள அட்டை எண்" else "Aadhaar / ID Proof No.") },
                            leadingIcon = { Icon(Icons.Default.Badge, contentDescription = null) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = address,
                            onValueChange = { viewModel.benAddress.value = it },
                            label = { Text(if (isTamil) "முழு முகவரி" else "Address") },
                            leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 2
                        )

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = villageTown,
                                onValueChange = { viewModel.benVillageTown.value = it },
                                label = { Text(if (isTamil) "கிராமம் / ஊர் *" else "Village / Town *") },
                                modifier = Modifier.weight(1f).testTag("ben_village"),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = panchayat,
                                onValueChange = { viewModel.benPanchayat.value = it },
                                label = { Text(if (isTamil) "ஊராட்சி" else "Panchayat") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = taluk,
                                onValueChange = { viewModel.benTaluk.value = it },
                                label = { Text(if (isTamil) "வட்டம் (Taluk)" else "Taluk") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = pinCode,
                                onValueChange = { viewModel.benPinCode.value = it },
                                label = { Text(if (isTamil) "அஞ்சல் குறியீடு (PIN)" else "PIN Code") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        // District selector
                        ExposedDropdownMenuBox(
                            expanded = districtExpanded,
                            onExpandedChange = { districtExpanded = !districtExpanded }
                        ) {
                            OutlinedTextField(
                                value = district,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(if (isTamil) "மாவட்டம்" else "District") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = districtExpanded) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = districtExpanded,
                                onDismissRequest = { districtExpanded = false }
                            ) {
                                AppDataStore.DELTA_DISTRICTS.forEach { d ->
                                    DropdownMenuItem(
                                        text = { Text(d) },
                                        onClick = {
                                            viewModel.benDistrict.value = d
                                            districtExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                // ==================== SECTION 2 ====================
                item {
                    SectionCard(
                        sectionNumber = "2",
                        titleEn = "Family & Socio-Economic Details",
                        titleTa = "குடும்பம் & சமூகப் பொருளாதார விவரங்கள்"
                    ) {
                        // Marital status
                        ExposedDropdownMenuBox(
                            expanded = maritalExpanded,
                            onExpandedChange = { maritalExpanded = !maritalExpanded }
                        ) {
                            OutlinedTextField(
                                value = maritalOptions.firstOrNull { it.first == maritalStatus }?.second ?: maritalStatus,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(if (isTamil) "திருமண நிலை" else "Marital Status") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = maritalExpanded) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = maritalExpanded,
                                onDismissRequest = { maritalExpanded = false }
                            ) {
                                maritalOptions.forEach { (key, label) ->
                                    DropdownMenuItem(
                                        text = { Text(label) },
                                        onClick = {
                                            viewModel.benMaritalStatus.value = key
                                            maritalExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = familyMembersCount,
                                onValueChange = { viewModel.benFamilyMembersCount.value = it },
                                label = { Text(if (isTamil) "குடும்ப உறுப்பினர்கள்" else "Family Members") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = childrenCount,
                                onValueChange = { viewModel.benChildrenCount.value = it },
                                label = { Text(if (isTamil) "குழந்தைகள் எண்ணிக்கை" else "Number of Children") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = occupation,
                                onValueChange = { viewModel.benOccupation.value = it },
                                label = { Text(if (isTamil) "தொழில்" else "Occupation") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = monthlyIncome,
                                onValueChange = { viewModel.benMonthlyIncome.value = it },
                                label = { Text(if (isTamil) "மாத வருமானம் ₹" else "Monthly Income ₹") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        // Housing status chips
                        Column {
                            Text(
                                text = if (isTamil) "குடியிருப்பு நிலை (Housing Status):" else "Housing Status:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                housingOptions.forEach { (key, label) ->
                                    FilterChip(
                                        selected = housingStatus == key,
                                        onClick = { viewModel.benHousingStatus.value = key },
                                        label = { Text(label, fontSize = 11.sp) }
                                    )
                                }
                            }
                        }

                        // Existing Government assistance
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (isTamil) "அரசு உதவி ஏதேனும் பெறுகிறீர்களா?" else "Any existing government assistance?",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Switch(
                                        checked = hasGovtAssistance,
                                        onCheckedChange = { viewModel.benHasGovtAssistance.value = it }
                                    )
                                }
                                if (hasGovtAssistance) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    OutlinedTextField(
                                        value = govtAssistanceDetails,
                                        onValueChange = { viewModel.benGovtAssistanceDetails.value = it },
                                        label = { Text(if (isTamil) "ஆம் எனில் அதன் விவரம்" else "If yes, please provide details") },
                                        modifier = Modifier.fillMaxWidth(),
                                        singleLine = true
                                    )
                                }
                            }
                        }
                    }
                }

                // ==================== SECTION 3 ====================
                item {
                    SectionCard(
                        sectionNumber = "3",
                        titleEn = "Assistance Requested",
                        titleTa = "கோரப்படும் உதவி வகை"
                    ) {
                        Text(
                            text = if (isTamil) "தேவைப்படும் உதவி வகையை தேர்வு செய்யவும் (பலவற்றை தேர்ந்தெடுக்கலாம்):"
                            else "Select Type of Assistance Requested (Check all that apply):",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        // 12 Checkbox Options
                        AppDataStore.BENEFICIARY_ASSISTANCE_OPTIONS.forEach { opt ->
                            val isChecked = selectedAssistanceTypes.contains(opt.key)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { viewModel.toggleBenAssistanceType(opt.key) }
                                    .padding(vertical = 4.dp)
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { viewModel.toggleBenAssistanceType(opt.key) }
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = opt.iconEmoji, fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = if (isTamil) opt.titleTa else opt.titleEn,
                                        fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                    if (isTamil) {
                                        Text(text = opt.titleEn, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            }
                        }

                        if (selectedAssistanceTypes.contains("Other")) {
                            OutlinedTextField(
                                value = otherAssistanceType,
                                onValueChange = { viewModel.benOtherAssistanceType.value = it },
                                label = { Text(if (isTamil) "பிற உதவி வகை விவரம்" else "Other Assistance Details") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = problemDetails,
                            onValueChange = { viewModel.benProblemDetails.value = it },
                            label = { Text(if (isTamil) "கோரிக்கை / பிரச்சினையின் விரிவான விவரம் *" else "Details of Request / Problem *") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                                .testTag("ben_problem_details"),
                            maxLines = 5
                        )

                        // Urgency chips
                        Column {
                            Text(
                                text = if (isTamil) "அவசரத்தன்மை (Urgency):" else "Urgency Level:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                urgencyOptions.forEach { (key, label) ->
                                    val isSelected = urgency == key
                                    val color = when (key) {
                                        "Emergency" -> MaterialTheme.colorScheme.error
                                        "Urgent" -> Color(0xFFE65100)
                                        else -> MaterialTheme.colorScheme.primary
                                    }
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { viewModel.benUrgency.value = key },
                                        label = { Text(label, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                                        border = if (isSelected) BorderStroke(1.5.dp, color) else null
                                    )
                                }
                            }
                        }
                    }
                }

                // ==================== SECTION 4 ====================
                item {
                    SectionCard(
                        sectionNumber = "4",
                        titleEn = "Financial Requirement",
                        titleTa = "நிதித் தேவை விவரங்கள்"
                    ) {
                        OutlinedTextField(
                            value = estimatedAmount,
                            onValueChange = {
                                viewModel.benEstimatedAmount.value = it
                                viewModel.autoCalculateRequestedFromTrust()
                            },
                            label = { Text(if (isTamil) "மதிப்பிடப்பட்ட உதவித் தொகை (₹)" else "Estimated Assistance Required (₹)") },
                            leadingIcon = { Text("₹", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp)) },
                            modifier = Modifier.fillMaxWidth().testTag("ben_estimated_amount"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = applicantContribution,
                            onValueChange = {
                                viewModel.benApplicantContribution.value = it
                                viewModel.autoCalculateRequestedFromTrust()
                            },
                            label = { Text(if (isTamil) "விண்ணப்பதாரர் பங்களிப்பு, ஏதேனும் இருப்பின் (₹)" else "Applicant's Contribution, if any (₹)") },
                            leadingIcon = { Text("₹", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = requestedAmount,
                            onValueChange = { viewModel.benRequestedAmount.value = it },
                            label = { Text(if (isTamil) "அறக்கட்டளையிடம் கோரப்படும் உதவி (₹)" else "Assistance Requested from Trust (₹)") },
                            leadingIcon = { Text("₹", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp)) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                focusedLabelColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.fillMaxWidth().testTag("ben_requested_amount"),
                            singleLine = true
                        )
                    }
                }

                // ==================== SECTION 5 ====================
                item {
                    SectionCard(
                        sectionNumber = "5",
                        titleEn = "Supporting Documents",
                        titleTa = "இணைக்கப்படும் சான்றாவணங்கள்"
                    ) {
                        Text(
                            text = if (isTamil) "விண்ணப்பத்துடன் இணைக்க உள்ள ஆவணங்களை டிக் செய்யவும்:"
                            else "Check documents to be submitted with this request:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        AppDataStore.SUPPORTING_DOC_OPTIONS.forEach { doc ->
                            val isChecked = supportingDocs.contains(doc.key)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { viewModel.toggleBenSupportingDoc(doc.key) }
                                    .padding(vertical = 3.dp)
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { viewModel.toggleBenSupportingDoc(doc.key) }
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = if (isTamil) doc.labelTa else doc.labelEn,
                                        fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 13.sp
                                    )
                                    if (isTamil) {
                                        Text(text = doc.labelEn, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            }
                        }

                        if (supportingDocs.contains("Other")) {
                            OutlinedTextField(
                                value = otherDocDetails,
                                onValueChange = { viewModel.benOtherDocDetails.value = it },
                                label = { Text(if (isTamil) "பிற ஆவண விவரம்" else "Other Document Details") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }
                    }
                }

                // ==================== SECTION 6 ====================
                item {
                    SectionCard(
                        sectionNumber = "6",
                        titleEn = "Declaration & Consent",
                        titleTa = "உறுதிமொழி & ஒப்புதல்"
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (isTamil)
                                        "\"இவ்விண்ணப்பத்தில் என்னால் வழங்கப்பட்டுள்ள தகவல்கள் அனைத்தும் எனது முழு அறிவிற்கும் உண்மைக்கும் உட்பட்டு சரியானவை என உறுதியளிக்கிறேன். இக்கோரிக்கையை சமர்ப்பிப்பது தானாகவே உதவி கிடைப்பதை உத்தரவாதப்படுத்தாது என்பதை நான் புரிந்துகொள்கிறேன். அறக்கட்டளையினர் இத்தகவல்களை கள ஆய்வு மூலம் சரிபார்க்கவும், பயனாளி மதிப்பீடு, திட்ட செயலாக்கம், கண்காணிப்பு மற்றும் அறிக்கையிடலுக்காகப் பயன்படுத்தவும் முழு மனதுடன் சம்மதிக்கிறேன்.\""
                                    else
                                        "\"I declare that the information provided in this form is true and correct to the best of my knowledge. I understand that submission of this request does not automatically guarantee assistance. I consent to the Trust verifying the information and using the required details for beneficiary assessment, programme implementation, monitoring and reporting.\"",
                                    style = MaterialTheme.typography.bodySmall,
                                    lineHeight = 18.sp,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.benIsDeclarationAgreed.value = !isDeclarationAgreed }
                                .padding(vertical = 4.dp)
                        ) {
                            Checkbox(
                                checked = isDeclarationAgreed,
                                onCheckedChange = { viewModel.benIsDeclarationAgreed.value = it }
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isTamil) "மேற்கண்ட உறுதிமொழியை முழுமையாக ஒப்புக்கொள்கிறேன் *"
                                else "I agree to the declaration and give my consent *",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        OutlinedTextField(
                            value = signatureName,
                            onValueChange = { viewModel.benSignatureName.value = it },
                            label = { Text(if (isTamil) "விண்ணப்பதாரர் கையொப்பம் / பெயர் *" else "Beneficiary / Applicant Signature / Name *") },
                            leadingIcon = { Icon(Icons.Default.Draw, contentDescription = null) },
                            modifier = Modifier.fillMaxWidth().testTag("ben_signature"),
                            singleLine = true
                        )

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = declarationDate,
                                onValueChange = { viewModel.benDeclarationDate.value = it },
                                label = { Text(if (isTamil) "தேதி (Date)" else "Date") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = declarationPlace,
                                onValueChange = { viewModel.benDeclarationPlace.value = it },
                                label = { Text(if (isTamil) "இடம் (Place)" else "Place") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }
                    }
                }

                // ==================== FOR OFFICE USE ONLY PREVIEW ====================
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Surface(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "OFFICIAL",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Text(
                                    text = if (isTamil) "அலுவலக பயன்பாட்டிற்கு மட்டும் (FOR OFFICE USE ONLY)" else "FOR OFFICE USE ONLY",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = if (isTamil)
                                    "இப்பகுதி அறக்கட்டளையின் கள ஆய்வாளர் மற்றும் நிர்வாகிகளால் நேரில் சரிபார்க்கப்பட்டு பூர்த்தி செய்யப்படும். பயனாளி எண், கள ஆய்வு அறிக்கை, பரிந்துரை, அனுமதிக்கப்பட்ட உதவித்தொகை மற்றும் திட்டம் ஆகியவற்றை நிர்வாகப் போர்ட்டலில் காணலாம்."
                                else
                                    "This section will be verified and completed by Delta Volunteers Trust Field Officers upon physical verification. Beneficiary ID, recommendation, approved assistance, and mode are managed via the Trust Admin Portal.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                // Submit & Reset Buttons
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = {
                                viewModel.submitBeneficiaryForm()
                            },
                            enabled = !isSubmitting,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("ben_submit_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            if (isSubmitting) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    strokeWidth = 2.dp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Text(
                                text = if (isTamil) "விண்ணப்பத்தை சமர்ப்பிக்கவும்" else "Submit Assistance Request Form",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        OutlinedButton(
                            onClick = { viewModel.resetBeneficiaryForm() },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (isTamil) "படிவத்தை மீட்டமைக்க (Clear / Reset)" else "Reset Form")
                        }
                    }
                }
            } else {
                // ==================== TAB 1: TRACK APPLICATIONS ====================
                if (beneficiaryRequests.isEmpty()) {
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "📭", fontSize = 40.sp)
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = if (isTamil) "விண்ணப்பங்கள் எதுவும் சமர்ப்பிக்கப்படவில்லை" else "No Beneficiary Requests Found",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (isTamil) "மேலே உள்ள படிவத்தை பூர்த்தி செய்து சமர்ப்பிக்கவும்." else "Fill out the application form tab to register a new assistance request.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                } else {
                    item {
                        Text(
                            text = if (isTamil) "சமர்ப்பிக்கப்பட்ட கோரிக்கைகள் (${beneficiaryRequests.size})" else "Submitted Applications (${beneficiaryRequests.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    items(beneficiaryRequests, key = { it.id }) { req ->
                        BeneficiaryRequestItemCard(
                            request = req,
                            isTamil = isTamil,
                            onShare = {
                                shareRequestSummary(context, req)
                            }
                        )
                    }
                }
            }
        }
    }

    // Success dialog when form is submitted
    submittedItem?.let { req ->
        Dialog(onDismissRequest = { viewModel.benSubmittedRequest.value = null }) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFF2E7D32),
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isTamil) "விண்ணப்பம் வெற்றிகரமாக பதிவு செய்யப்பட்டது!" else "Request Submitted Successfully!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Form No:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Text(text = req.formNo, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                            }
                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Beneficiary ID:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Text(text = req.beneficiaryId, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Applicant:", fontSize = 12.sp)
                                Text(text = req.fullName, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                            }
                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Assistance:", fontSize = 12.sp)
                                Text(text = req.assistanceTypes.take(25) + if (req.assistanceTypes.length > 25) "..." else "", fontSize = 12.sp)
                            }
                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Requested Amount:", fontSize = 12.sp)
                                Text(text = "₹${req.assistanceRequestedFromTrust}", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isTamil)
                            "உங்கள் விண்ணப்பம் பெறப்பட்டது. அறக்கட்டளை களப்பணியாளர் விரைவில் உங்களை தொடர்புகொண்டு நேரில் சரிபார்ப்பார். உதவிக்கு: 9943515879"
                        else
                            "Your assistance request has been recorded. A trust verification coordinator will contact you shortly for field verification. Helpline: +91 99435 15879",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { shareRequestSummary(context, req) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (isTamil) "பகிர்" else "Share")
                        }
                        Button(
                            onClick = {
                                viewModel.benSubmittedRequest.value = null
                                selectedTab = 1
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(if (isTamil) "முடிந்தது" else "Done")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionCard(
    sectionNumber: String,
    titleEn: String,
    titleTa: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = sectionNumber,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 14.sp
                        )
                    }
                }
                Column {
                    Text(
                        text = "$sectionNumber. $titleEn",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = titleTa,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            content()
        }
    }
}

@Composable
fun BeneficiaryRequestItemCard(
    request: BeneficiaryRequestEntity,
    isTamil: Boolean,
    onShare: () -> Unit
) {
    val statusColor = when (request.status) {
        "Approved" -> Color(0xFF2E7D32)
        "Under Verification" -> Color(0xFFE65100)
        "Eligible" -> Color(0xFF0284C7)
        "Rejected" -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            // Header: Form No and Status Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = request.formNo,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Beneficiary ID: ${request.beneficiaryId.ifBlank { "Pending" }}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    color = statusColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, statusColor.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = request.status,
                        color = statusColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

            // Applicant Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = request.fullName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    if (request.guardianName.isNotBlank()) {
                        Text(
                            text = "c/o ${request.guardianName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "${request.villageTown}, ${request.district}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "₹${request.assistanceRequestedFromTrust}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Requested",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Assistance Type & Problem
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Types: ${request.assistanceTypes}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = request.requestProblemDetails,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Office review info if available
            if (request.recommendation.isNotBlank() && request.recommendation != "Under Review" || request.assistanceApproved.isNotBlank()) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "Office Assessment:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        if (request.recommendation.isNotBlank()) {
                            Text(text = "Recommendation: ${request.recommendation}", fontSize = 12.sp)
                        }
                        if (request.assistanceApproved.isNotBlank()) {
                            Text(text = "Approved Assistance: ₹${request.assistanceApproved}", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        if (request.modeOfAssistance.isNotBlank()) {
                            Text(text = "Mode: ${request.modeOfAssistance}", fontSize = 12.sp)
                        }
                        if (request.projectName.isNotBlank()) {
                            Text(text = "Project: ${request.projectName}", fontSize = 12.sp)
                        }
                        if (request.verifiedBy.isNotBlank()) {
                            Text(text = "Verified By: ${request.verifiedBy}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            // Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Date: ${request.formDate}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                IconButton(onClick = onShare) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

fun shareRequestSummary(context: Context, req: BeneficiaryRequestEntity) {
    val text = """
        *DELTA VOLUNTEERS TRUST*
        *Beneficiary Assistance Request Form*
        ------------------------------------------
        Form No: ${req.formNo}
        Date: ${req.formDate}
        Beneficiary ID: ${req.beneficiaryId}
        Status: ${req.status}
        
        1. APPLICANT DETAILS:
        Name: ${req.fullName}
        Guardian: ${req.guardianName}
        Age/DOB: ${req.ageOrDob} | Gender: ${req.gender}
        Mobile: ${req.mobileNumber}
        Address: ${req.address}, ${req.villageTown}, ${req.district} - ${req.pinCode}
        
        2. SOCIO-ECONOMIC:
        Occupation: ${req.occupation} | Income: ₹${req.monthlyIncome}/month
        Housing: ${req.housingStatus} | Family: ${req.familyMembersCount}
        
        3. ASSISTANCE REQUESTED:
        Type: ${req.assistanceTypes}
        Problem: ${req.requestProblemDetails}
        Urgency: ${req.urgency}
        
        4. FINANCIAL REQUIREMENT:
        Estimated: ₹${req.estimatedAssistanceRequired}
        Applicant Contribution: ₹${req.applicantContribution}
        Requested from Trust: ₹${req.assistanceRequestedFromTrust}
        
        Head Office: 46, West Street, Vedaranyam - 614810
        Contact: 9943515879 | Email: exn.prabu@gmail.com
    """.trimIndent()

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Beneficiary Request: ${req.formNo}")
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Share Request"))
}
