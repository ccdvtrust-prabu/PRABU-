package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.AppDataStore
import com.example.data.CommunityNeedEntity
import com.example.ui.DeltaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportNeedScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    val needName by viewModel.needName.collectAsState()
    val needContact by viewModel.needContact.collectAsState()
    val needDistrict by viewModel.needDistrict.collectAsState()
    val needLocation by viewModel.needLocation.collectAsState()
    val needCategory by viewModel.needCategory.collectAsState()
    val needDescription by viewModel.needDescription.collectAsState()
    val needUrgency by viewModel.needUrgency.collectAsState()
    val needPhotoAttached by viewModel.needPhotoAttached.collectAsState()
    val submittedRef by viewModel.needSubmittedRef.collectAsState()
    val isSubmitting by viewModel.isNeedSubmitting.collectAsState()
    val formError by viewModel.needFormError.collectAsState()
    val reportedNeeds by viewModel.communityNeeds.collectAsState()

    var districtExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    val categories = listOf(
        "💧 Drinking water problem (குடிநீர் பிரச்சினை)",
        "🌊 Disaster-related issue (பேரிடர் / வெள்ள பாதிப்பு)",
        "🌱 Environmental issue (சுற்றுச்சூழல் மாசுபாடு / கழிவு)",
        "🏫 School/community infrastructure issue (பள்ளி / சமுதாய உட்கட்டமைப்பு)",
        "🌳 Waterbody issue (ஏரி / குளம் தூர்வாருதல் / ஆக்கிரமிப்பு)",
        "🌾 Other community needs (பிற சமுதாயத் தேவைகள்)"
    )

    val urgencyOptions = listOf(
        "Normal (சாதாரண தேவை)",
        "High / Urgent (அவசர நடவடிக்கை தேவை)",
        "Critical / SOS (உடனடி பேரிடர் மீட்பு)"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("report_need_screen"),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = "📢", fontSize = 32.sp)
                            Column {
                                Text(
                                    text = if (isTamil) "சமுதாய தேவை / புகார் பதிவு" else "Report Community Need / Issue",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Text(
                                    text = if (isTamil) "கிராம தேவைகளை Trust கவனத்திற்கு கொண்டு வாருங்கள்" else "Grassroots issue reporting platform",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTamil) "உங்கள் ஊரில் குடிநீர் தட்டுப்பாடு, உடைந்த குளக்கரைகள், பள்ளி பராமரிப்பு தேவைகள் அல்லது புயல் சேதங்களை உடனடியாக பதிவு செய்யுங்கள். எங்கள் களத் தன்னார்வலர்கள் சரிபார்த்து உரிய தீர்வு காண உதவுவார்கள்."
                            else "Report local drinking water crises, silted ponds, damaged flood bunds, or school infrastructure needs directly to Delta Volunteers Trust coordinators.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Error banner if any
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
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // Form Card
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(
                            text = if (isTamil) "புகார் / தேவை பதிவு விவரங்கள்" else "Issue Submission Form",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        // Name
                        OutlinedTextField(
                            value = needName,
                            onValueChange = { viewModel.needName.value = it },
                            label = { Text(if (isTamil) "உங்கள் பெயர் *" else "Your Name *") },
                            placeholder = { Text(if (isTamil) "பெயர்" else "Enter full name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("need_name_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
                        )

                        // Contact Number
                        OutlinedTextField(
                            value = needContact,
                            onValueChange = { viewModel.needContact.value = it },
                            label = { Text(if (isTamil) "தொடர்பு தொலைபேசி எண் *" else "Contact Phone Number *") },
                            placeholder = { Text("e.g. 9842455100") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("need_contact_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) }
                        )

                        // District Dropdown
                        ExposedDropdownMenuBox(
                            expanded = districtExpanded,
                            onExpandedChange = { districtExpanded = !districtExpanded }
                        ) {
                            OutlinedTextField(
                                value = needDistrict,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(if (isTamil) "மாவட்டம் (District) *" else "District *") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = districtExpanded) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                                    .testTag("need_district_select")
                            )
                            ExposedDropdownMenu(
                                expanded = districtExpanded,
                                onDismissRequest = { districtExpanded = false }
                            ) {
                                AppDataStore.DELTA_DISTRICTS.forEach { district ->
                                    DropdownMenuItem(
                                        text = { Text(district) },
                                        onClick = {
                                            viewModel.needDistrict.value = district
                                            districtExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // Location / Village / Landmark
                        OutlinedTextField(
                            value = needLocation,
                            onValueChange = { viewModel.needLocation.value = it },
                            label = { Text(if (isTamil) "கிராமம் / குறிப்பிட்ட இடம் / அடையாளம் *" else "Specific Location / Village / Landmark *") },
                            placeholder = { Text(if (isTamil) "எ.கா. காவேரி நதிக்கரை, ஆவூர் கிராமம்" else "e.g. Cauvery canal bund, Avoor village") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("need_location_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Place, contentDescription = null) }
                        )

                        // Issue Category Dropdown
                        ExposedDropdownMenuBox(
                            expanded = categoryExpanded,
                            onExpandedChange = { categoryExpanded = !categoryExpanded }
                        ) {
                            OutlinedTextField(
                                value = needCategory,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(if (isTamil) "பிரச்சினை வகை (Category) *" else "Issue Category *") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                                    .testTag("need_category_select")
                            )
                            ExposedDropdownMenu(
                                expanded = categoryExpanded,
                                onDismissRequest = { categoryExpanded = false }
                            ) {
                                categories.forEach { cat ->
                                    DropdownMenuItem(
                                        text = { Text(cat) },
                                        onClick = {
                                            viewModel.needCategory.value = cat
                                            categoryExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // Description
                        OutlinedTextField(
                            value = needDescription,
                            onValueChange = { viewModel.needDescription.value = it },
                            label = { Text(if (isTamil) "பிரச்சினையின் விவரம் (Description) *" else "Detailed Description *") },
                            placeholder = { Text(if (isTamil) "பிரச்சினையை விரிவாக விளக்குங்கள்..." else "Explain the issue, number of affected families, urgency...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .testTag("need_desc_input"),
                            maxLines = 5
                        )

                        // Urgency Level
                        Text(
                            text = if (isTamil) "அவசர நிலை (Urgency Level)" else "Urgency Level",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            urgencyOptions.forEach { opt ->
                                val isSelected = needUrgency == opt
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable { viewModel.needUrgency.value = opt }
                                ) {
                                    Text(
                                        text = opt.substringBefore(" "),
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp)
                                    )
                                }
                            }
                        }

                        // Photo Upload Simulated Toggle
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.needPhotoAttached.value = !needPhotoAttached
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(
                                        imageVector = if (needPhotoAttached) Icons.Default.CheckCircle else Icons.Default.AddPhotoAlternate,
                                        contentDescription = "Photo",
                                        tint = if (needPhotoAttached) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Column {
                                        Text(
                                            text = if (isTamil) "புகைப்படம் இணைக்க (Photo Upload)" else "Attach Site Photo",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = if (needPhotoAttached) {
                                                if (isTamil) "1 புகைப்படம் இணைக்கப்பட்டுள்ளது (Photo_01.jpg)" else "1 photo attached (Site_inspection.jpg)"
                                            } else {
                                                if (isTamil) "தளப் புகைப்படத்தை தேர்ந்தெடுக்க தட்டவும்" else "Tap to attach field inspection photo"
                                            },
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Switch(
                                    checked = needPhotoAttached,
                                    onCheckedChange = { viewModel.needPhotoAttached.value = it }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Submit Button
                        Button(
                            onClick = { viewModel.submitCommunityNeed() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("submit_need_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            enabled = !isSubmitting
                        ) {
                            if (isSubmitting) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                Icon(Icons.Default.Send, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isTamil) "கோரிக்கையை அனுப்புக (Submit Need)" else "Submit Community Need",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            // Reported Needs List & Status Tracking
            item {
                Text(
                    text = if (isTamil) "பதிவு செய்யப்பட்ட சமுதாயக் கோரிக்கைகள் (${reportedNeeds.size})"
                    else "Submitted Community Needs (${reportedNeeds.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            if (reportedNeeds.isEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "📝", fontSize = 32.sp)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (isTamil) "இதுவரை கோரிக்கைகள் பதிவு செய்யப்படவில்லை" else "No community issues submitted yet",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(reportedNeeds) { need ->
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

        // Submission Success Dialog
        submittedRef?.let { ref ->
            Dialog(onDismissRequest = { viewModel.dismissNeedSuccessDialog() }) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = if (isTamil) "கோரிக்கை பதிவு செய்யப்பட்டது!" else "Report Submitted!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (isTamil) "உங்கள் பகுதி சமுதாய தேவை வெற்றிகரமாக பெறப்பட்டது. எங்கள் களக் குழுவினர் விரைவில் ஆய்வு மேற்கொள்வர்."
                            else "Your community need has been registered with Delta Volunteers Trust. Local coordinators will review and initiate action.",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "Ref ID: $ref",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { viewModel.dismissNeedSuccessDialog() },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(if (isTamil) "சரி" else "OK")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CommunityNeedItemCard(
    need: CommunityNeedEntity,
    isTamil: Boolean,
    onStatusChange: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = need.referenceId,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Status Badge
                val statusColor = when (need.status) {
                    "Resolved" -> Color(0xFF16A34A)
                    "Team Assigned" -> Color(0xFF0284C7)
                    "Under Verification" -> Color(0xFFD97706)
                    else -> MaterialTheme.colorScheme.secondary
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = statusColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = need.status,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = statusColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = need.issueCategory,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "${need.location}, ${need.district}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = need.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (need.photoNote.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = need.photoNote,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.padding(vertical = 4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Reported by: ${need.name}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Quick Status progress simulation toggle
                TextButton(
                    onClick = {
                        val nextStatus = when (need.status) {
                            "Submitted" -> "Under Verification"
                            "Under Verification" -> "Team Assigned"
                            "Team Assigned" -> "Resolved"
                            else -> "Submitted"
                        }
                        onStatusChange(nextStatus)
                    }
                ) {
                    Text(
                        text = if (isTamil) "நிலையை மாற்று >" else "Advance Status >",
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
