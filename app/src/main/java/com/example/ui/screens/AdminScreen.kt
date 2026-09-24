package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.DeltaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    var adminTab by remember { mutableStateOf(0) } // 0: Post Announcement, 1: Volunteer Roster, 2: Community Needs, 3: Beneficiary Requests

    val volunteers by viewModel.volunteers.collectAsState()
    val needs by viewModel.communityNeeds.collectAsState()
    val beneficiaryRequests by viewModel.beneficiaryRequests.collectAsState()
    var selectedBenForReview by remember { mutableStateOf<com.example.data.BeneficiaryRequestEntity?>(null) }
    val adminTitleEn by viewModel.adminTitleEn.collectAsState()
    val adminTitleTa by viewModel.adminTitleTa.collectAsState()
    val adminContentEn by viewModel.adminContentEn.collectAsState()
    val adminContentTa by viewModel.adminContentTa.collectAsState()
    val adminCategory by viewModel.adminCategory.collectAsState()
    val adminIsUrgent by viewModel.adminIsUrgent.collectAsState()
    val adminMessage by viewModel.adminMessage.collectAsState()

    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf("Announcement", "Emergency Alert", "Project Update", "Weather", "Event")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("admin_screen"),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
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
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(text = "⚙️", fontSize = 32.sp)
                        Column {
                            Text(
                                text = if (isTamil) "நிர்வாக ஒருங்கிணைப்பு தளம்" else "Trust Admin & Field Desk",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = if (isTamil) "அறிவிப்புகள், தன்னார்வலர்கள் & சமுதாயக் கோரிக்கைகள்" else "Coordinator dashboard for Delta operations",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

        // Tabs
        item {
            TabRow(
                selectedTabIndex = adminTab,
                containerColor = Color.Transparent,
                divider = {}
            ) {
                Tab(
                    selected = adminTab == 0,
                    onClick = { adminTab = 0 },
                    text = { Text(if (isTamil) "அறிவிப்பு" else "Post Update") }
                )
                Tab(
                    selected = adminTab == 1,
                    onClick = { adminTab = 1 },
                    text = { Text("${if (isTamil) "தன்னார்வலர்கள்" else "Volunteers"} (${volunteers.size})") }
                )
                Tab(
                    selected = adminTab == 2,
                    onClick = { adminTab = 2 },
                    text = { Text("${if (isTamil) "கோரிக்கைகள்" else "Needs"} (${needs.size})") }
                )
                Tab(
                    selected = adminTab == 3,
                    onClick = { adminTab = 3 },
                    text = { Text("${if (isTamil) "பயனாளிகள்" else "Beneficiaries"} (${beneficiaryRequests.size})") }
                )
            }
        }

        when (adminTab) {
            0 -> {
                // Post Announcement Form
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = if (isTamil) "புதிய அறிவிப்பை வெளியிடுக" else "Broadcast New Announcement",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            adminMessage?.let { msg ->
                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = msg,
                                        modifier = Modifier.padding(10.dp),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            OutlinedTextField(
                                value = adminTitleEn,
                                onValueChange = { viewModel.adminTitleEn.value = it },
                                label = { Text("Title (English)") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            OutlinedTextField(
                                value = adminTitleTa,
                                onValueChange = { viewModel.adminTitleTa.value = it },
                                label = { Text("தலைப்பு (தமிழ்)") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            ExposedDropdownMenuBox(
                                expanded = categoryExpanded,
                                onExpandedChange = { categoryExpanded = !categoryExpanded }
                            ) {
                                OutlinedTextField(
                                    value = adminCategory,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Category") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = categoryExpanded,
                                    onDismissRequest = { categoryExpanded = false }
                                ) {
                                    categories.forEach { cat ->
                                        DropdownMenuItem(
                                            text = { Text(cat) },
                                            onClick = {
                                                viewModel.adminCategory.value = cat
                                                categoryExpanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            OutlinedTextField(
                                value = adminContentEn,
                                onValueChange = { viewModel.adminContentEn.value = it },
                                label = { Text("Detailed Content (English)") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                maxLines = 4
                            )

                            OutlinedTextField(
                                value = adminContentTa,
                                onValueChange = { viewModel.adminContentTa.value = it },
                                label = { Text("விவரங்கள் (தமிழ்)") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                maxLines = 4
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { viewModel.adminIsUrgent.value = !adminIsUrgent }
                            ) {
                                Checkbox(
                                    checked = adminIsUrgent,
                                    onCheckedChange = { viewModel.adminIsUrgent.value = it }
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isTamil) "முக்கிய அவசர எச்சரிக்கையாக வெளியிடு (Emergency Alert)" else "Mark as High-Priority Urgent Alert",
                                    color = if (adminIsUrgent) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                                    fontWeight = if (adminIsUrgent) FontWeight.Bold else FontWeight.Normal
                                )
                            }

                            Button(
                                onClick = { viewModel.addAdminNews() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(if (isTamil) "வெளியிடு (Publish Update)" else "Publish Update")
                            }
                        }
                    }
                }
            }
            1 -> {
                // Volunteers roster
                if (volunteers.isEmpty()) {
                    item {
                        Text(
                            text = if (isTamil) "பதிவு செய்யப்பட்ட தன்னார்வலர்கள் இல்லை" else "No volunteers registered yet",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    items(volunteers) { vol ->
                        VolunteerItemCard(volunteer = vol, isTamil = isTamil)
                    }
                }
            }
            2 -> {
                // Community Needs Roster
                if (needs.isEmpty()) {
                    item {
                        Text(
                            text = if (isTamil) "சமுதாயக் கோரிக்கைகள் எதுவும் இல்லை" else "No community needs submitted yet",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    items(needs) { need ->
                        CommunityNeedItemCard(
                            need = need,
                            isTamil = isTamil,
                            onStatusChange = { newStatus ->
                                viewModel.updateNeedStatus(need.id, newStatus)
                            }
                        )
                    }
                }
            }
            3 -> {
                // Beneficiary Requests Roster
                if (beneficiaryRequests.isEmpty()) {
                    item {
                        Text(
                            text = if (isTamil) "பயனாளி உதவி கோரிக்கைகள் எதுவும் சமர்ப்பிக்கப்படவில்லை" else "No beneficiary assistance requests submitted yet",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    items(beneficiaryRequests, key = { it.id }) { req ->
                        AdminBeneficiaryCard(
                            request = req,
                            isTamil = isTamil,
                            onReviewClick = {
                                selectedBenForReview = req
                            },
                            onDeleteClick = {
                                viewModel.deleteBeneficiaryRequest(req.id)
                            }
                        )
                    }
                }
            }
        }
    }

    selectedBenForReview?.let { req ->
        OfficeAppraisalDialog(
            request = req,
            isTamil = isTamil,
            onDismiss = { selectedBenForReview = null },
            onSave = { updated ->
                viewModel.updateBeneficiaryOfficeReview(
                    id = updated.id,
                    beneficiaryId = updated.beneficiaryId,
                    fieldVerificationRequired = updated.fieldVerificationRequired,
                    verificationOfficer = updated.verificationOfficer,
                    verificationDate = updated.verificationDate,
                    recommendation = updated.recommendation,
                    assistanceApproved = updated.assistanceApproved,
                    modeOfAssistance = updated.modeOfAssistance,
                    projectName = updated.projectName,
                    verifiedBy = updated.verifiedBy,
                    approvedBy = updated.approvedBy,
                    status = updated.status
                )
                selectedBenForReview = null
            }
        )
    }
}

@Composable
fun AdminBeneficiaryCard(
    request: com.example.data.BeneficiaryRequestEntity,
    isTamil: Boolean,
    onReviewClick: () -> Unit,
    onDeleteClick: () -> Unit
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
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = request.formNo,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "ID: ${request.beneficiaryId.ifBlank { "Not Assigned" }}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    color = statusColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, statusColor.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = request.status,
                        color = statusColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                    )
                }
            }

            Text(
                text = "${request.fullName} (${request.ageOrDob}, ${request.gender})",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(
                text = "📍 ${request.villageTown}, ${request.district} • 📞 ${request.mobileNumber}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Assistance: ${request.assistanceTypes}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Req: ₹${request.assistanceRequestedFromTrust}",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp
                )
                if (request.assistanceApproved.isNotBlank()) {
                    Text(
                        text = "Approved: ₹${request.assistanceApproved}",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32),
                        fontSize = 14.sp
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Button(
                    onClick = onReviewClick,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.RateReview, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (isTamil) "அலுவலக மதிப்பீடு" else "Office Appraisal", fontSize = 13.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfficeAppraisalDialog(
    request: com.example.data.BeneficiaryRequestEntity,
    isTamil: Boolean,
    onDismiss: () -> Unit,
    onSave: (com.example.data.BeneficiaryRequestEntity) -> Unit
) {
    var beneficiaryId by remember { mutableStateOf(request.beneficiaryId) }
    var fieldVerificationRequired by remember { mutableStateOf(request.fieldVerificationRequired) }
    var verificationOfficer by remember { mutableStateOf(request.verificationOfficer) }
    var verificationDate by remember { mutableStateOf(request.verificationDate.ifBlank { "24/09/2026" }) }
    var recommendation by remember { mutableStateOf(request.recommendation.ifBlank { "Eligible" }) }
    var assistanceApproved by remember { mutableStateOf(request.assistanceApproved) }
    var modeOfAssistance by remember { mutableStateOf(request.modeOfAssistance.ifBlank { "Material" }) }
    var projectName by remember { mutableStateOf(request.projectName.ifBlank { "Delta Volunteers Welfare Initiative" }) }
    var verifiedBy by remember { mutableStateOf(request.verifiedBy.ifBlank { "G. Prabu (Chief Coordinator)" }) }
    var approvedBy by remember { mutableStateOf(request.approvedBy.ifBlank { "Trustee Board, Delta Volunteers Trust" }) }
    var status by remember { mutableStateOf(request.status) }

    val recommendationOptions = listOf("Under Review", "Eligible", "Not Eligible", "Further Verification")
    val modeOptions = listOf("Material", "Direct Service", "Bank Transfer", "Other")
    val statusOptions = listOf("Submitted", "Under Verification", "Eligible", "Approved", "Completed", "Rejected")

    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isTamil) "அலுவலக பயன்பாட்டிற்கு மட்டும்" else "FOR OFFICE USE ONLY",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${request.formNo} • ${request.fullName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        OutlinedTextField(
                            value = beneficiaryId,
                            onValueChange = { beneficiaryId = it },
                            label = { Text("Beneficiary ID (பயனாளி எண்)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Field Verification Required (கள ஆய்வு தேவையா?):",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(1f)
                            )
                            Switch(
                                checked = fieldVerificationRequired,
                                onCheckedChange = { fieldVerificationRequired = it }
                            )
                        }
                    }

                    item {
                        OutlinedTextField(
                            value = verificationOfficer,
                            onValueChange = { verificationOfficer = it },
                            label = { Text("Verification Officer (சரிபார்ப்பு அலுவலர்)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = verificationDate,
                            onValueChange = { verificationDate = it },
                            label = { Text("Verification Date (சரிபார்ப்பு தேதி)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    item {
                        Text(text = "Recommendation (பரிந்துரை):", style = MaterialTheme.typography.labelMedium)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            recommendationOptions.take(2).forEach { opt ->
                                FilterChip(
                                    selected = recommendation == opt,
                                    onClick = { recommendation = opt },
                                    label = { Text(opt, fontSize = 11.sp) }
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            recommendationOptions.drop(2).forEach { opt ->
                                FilterChip(
                                    selected = recommendation == opt,
                                    onClick = { recommendation = opt },
                                    label = { Text(opt, fontSize = 11.sp) }
                                )
                            }
                        }
                    }

                    item {
                        OutlinedTextField(
                            value = assistanceApproved,
                            onValueChange = { assistanceApproved = it },
                            label = { Text("Assistance Approved (அனுமதிக்கப்பட்ட உதவி ₹)") },
                            leadingIcon = { Text("₹", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 12.dp)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    item {
                        Text(text = "Mode of Assistance (உதவி வழங்கும் முறை):", style = MaterialTheme.typography.labelMedium)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            modeOptions.forEach { opt ->
                                FilterChip(
                                    selected = modeOfAssistance == opt,
                                    onClick = { modeOfAssistance = opt },
                                    label = { Text(opt, fontSize = 10.sp) }
                                )
                            }
                        }
                    }

                    item {
                        OutlinedTextField(
                            value = projectName,
                            onValueChange = { projectName = it },
                            label = { Text("Project / Programme Name (திட்டத்தின் பெயர்)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = verifiedBy,
                            onValueChange = { verifiedBy = it },
                            label = { Text("Verified By (சரிபார்த்தவர்)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = approvedBy,
                            onValueChange = { approvedBy = it },
                            label = { Text("Approved By (ஒப்புதல் அளித்தவர்)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    item {
                        Text(text = "Application Status (நிலை):", style = MaterialTheme.typography.labelMedium)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            statusOptions.take(3).forEach { st ->
                                FilterChip(
                                    selected = status == st,
                                    onClick = { status = st },
                                    label = { Text(st, fontSize = 10.sp) }
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            statusOptions.drop(3).forEach { st ->
                                FilterChip(
                                    selected = status == st,
                                    onClick = { status = st },
                                    label = { Text(st, fontSize = 10.sp) }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (isTamil) "ரத்து" else "Cancel")
                    }
                    Button(
                        onClick = {
                            val updated = request.copy(
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
                            onSave(updated)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (isTamil) "சேமிக்கவும்" else "Save Appraisal")
                    }
                }
            }
        }
    }
}

