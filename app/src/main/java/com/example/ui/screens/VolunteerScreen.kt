package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.data.VolunteerEntity
import com.example.ui.DeltaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    val volName by viewModel.volName.collectAsState()
    val volMobile by viewModel.volMobile.collectAsState()
    val volEmail by viewModel.volEmail.collectAsState()
    val volDistrict by viewModel.volDistrict.collectAsState()
    val volTaluk by viewModel.volTaluk.collectAsState()
    val volVillage by viewModel.volVillage.collectAsState()
    val volInterests by viewModel.volInterests.collectAsState()
    val volSkills by viewModel.volSkills.collectAsState()
    val volAvailability by viewModel.volAvailability.collectAsState()
    val volEmergencyExp by viewModel.volEmergencyExp.collectAsState()
    val registeredCode by viewModel.volunteerRegisteredCode.collectAsState()
    val isSubmitting by viewModel.isVolunteerSubmitting.collectAsState()
    val formError by viewModel.volunteerFormError.collectAsState()
    val volunteers by viewModel.volunteers.collectAsState()

    var districtExpanded by remember { mutableStateOf(false) }

    val interestOptions = listOf(
        "🌊 Disaster Management (பேரிடர் மேலாண்மை)",
        "💧 Drinking Water Security (குடிநீர் பாதுகாப்பு)",
        "🌱 Tree Plantation & Climate (மரக்கன்றுகள் நடுதல்)",
        "🏫 School Education & Eco-Clubs (பள்ளிக் கல்வி)",
        "🌳 Mangrove & Pond Desilting (குளம் & சதுப்புநிலம்)",
        "🚨 Emergency Rescue & First Aid (மீட்பு & முதலுதவி)",
        "♻️ Waste Management & Cleaning (கழிவு மேலாண்மை)",
        "💻 Tech, Media & Admin (ஊடகம் & தொழில்நுட்பம்)"
    )

    val availabilityOptions = listOf(
        "Weekends (வார இறுதி நாட்கள்)",
        "Emergency Callout (அவசர காலங்களில் மட்டும்)",
        "Flexible / Anytime (தேவைப்படும் போது எப்போது வேண்டுமானாலும்)",
        "Part-time Evenings (மாலை நேரங்களில்)"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("volunteer_screen"),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header card
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
                            Text(text = "🤝", fontSize = 32.sp)
                            Column {
                                Text(
                                    text = if (isTamil) "தன்னார்வலர் பதிவு படிவம்" else "Volunteer Registration",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = if (isTamil) "டெல்டா வாலண்டியர்ஸ் டிரஸ்டுடன் இணையுங்கள்" else "Join Delta Volunteers Trust Network",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTamil) "பேரிடர் பாதுகாப்பு, சுற்றுச்சூழல், நீர்நிலைகள் மற்றும் கிராம மேம்பாட்டிற்காக உழைக்க விரும்பும் ஆர்வமுள்ள இளைஞர்கள் மற்றும் பொதுமக்கள் இணையலாம். எவ்வித பதிவுக் கட்டணமும் இல்லை."
                            else "Free community volunteer registration. Be the first line of defense during cyclones, water shortages, and environmental drives in your taluk.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
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

            // Form Section
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
                            text = if (isTamil) "தனிநபர் விவரங்கள்" else "Personal Details",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        // Name
                        OutlinedTextField(
                            value = volName,
                            onValueChange = { viewModel.volName.value = it },
                            label = { Text(if (isTamil) "முழு பெயர் *" else "Full Name *") },
                            placeholder = { Text(if (isTamil) "எ.கா. மு. கவியரசன்" else "e.g. M. Kaviarasan") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("vol_name_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
                        )

                        // Mobile Number
                        OutlinedTextField(
                            value = volMobile,
                            onValueChange = { viewModel.volMobile.value = it },
                            label = { Text(if (isTamil) "தொலைபேசி எண் (Mobile) *" else "Mobile Number *") },
                            placeholder = { Text("e.g. 9443182900") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("vol_mobile_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) }
                        )

                        // Email
                        OutlinedTextField(
                            value = volEmail,
                            onValueChange = { viewModel.volEmail.value = it },
                            label = { Text(if (isTamil) "மின்னஞ்சல் முகவரி" else "Email Address (Optional)") },
                            placeholder = { Text("e.g. volunteer@gmail.com") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("vol_email_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) }
                        )

                        Divider(modifier = Modifier.padding(vertical = 4.dp))

                        Text(
                            text = if (isTamil) "இருப்பிட விவரங்கள்" else "Location Details",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        // District Dropdown
                        ExposedDropdownMenuBox(
                            expanded = districtExpanded,
                            onExpandedChange = { districtExpanded = !districtExpanded }
                        ) {
                            OutlinedTextField(
                                value = volDistrict,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(if (isTamil) "மாவட்டம் (District) *" else "District *") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = districtExpanded) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                                    .testTag("vol_district_select")
                            )
                            ExposedDropdownMenu(
                                expanded = districtExpanded,
                                onDismissRequest = { districtExpanded = false }
                            ) {
                                AppDataStore.DELTA_DISTRICTS.forEach { district ->
                                    DropdownMenuItem(
                                        text = { Text(district) },
                                        onClick = {
                                            viewModel.volDistrict.value = district
                                            districtExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // Taluk / Block
                        OutlinedTextField(
                            value = volTaluk,
                            onValueChange = { viewModel.volTaluk.value = it },
                            label = { Text(if (isTamil) "வட்டம் / ஒன்றியம் (Taluk / Block) *" else "Taluk / Block *") },
                            placeholder = { Text(if (isTamil) "எ.கா. கும்பகோணம் / நன்னிலம்" else "e.g. Kumbakonam / Nannilam") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("vol_taluk_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.LocationCity, contentDescription = null) }
                        )

                        // Village / Town
                        OutlinedTextField(
                            value = volVillage,
                            onValueChange = { viewModel.volVillage.value = it },
                            label = { Text(if (isTamil) "கிராமம் / நகரம் (Village / Town) *" else "Village / Town *") },
                            placeholder = { Text(if (isTamil) "எ.கா. சுவாமிமலை" else "e.g. Swamimalai") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("vol_village_input"),
                            singleLine = true,
                            leadingIcon = { Icon(Icons.Default.Place, contentDescription = null) }
                        )

                        Divider(modifier = Modifier.padding(vertical = 4.dp))

                        // Areas of Interest (Multi-select)
                        Text(
                            text = if (isTamil) "ஆர்வமுள்ள துறைகள் (Areas of Interest)" else "Areas of Interest",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isTamil) "நீங்கள் பங்களிக்க விரும்பும் துறைகளைத் தேர்ந்தெடுக்கவும்:"
                            else "Select fields where you would like to participate:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            interestOptions.forEach { interest ->
                                val isChecked = volInterests.contains(interest)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { viewModel.toggleInterest(interest) }
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = { viewModel.toggleInterest(interest) }
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = interest,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }

                        // Skills
                        OutlinedTextField(
                            value = volSkills,
                            onValueChange = { viewModel.volSkills.value = it },
                            label = { Text(if (isTamil) "திறன்கள் (Skills)" else "Skills & Talents") },
                            placeholder = { Text(if (isTamil) "நீச்சல், வாகனம் ஓட்டுதல், முதலுதவி, கற்பித்தல், முதலியன" else "e.g. Swimming, First aid, Driving, Teaching, Boat handling") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("vol_skills_input"),
                            maxLines = 2
                        )

                        // Availability
                        Text(
                            text = if (isTamil) "பங்களிக்கும் நேரம் (Availability)" else "Availability",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            availabilityOptions.forEach { avail ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { viewModel.volAvailability.value = avail }
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = volAvailability == avail,
                                        onClick = { viewModel.volAvailability.value = avail }
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = avail, style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }

                        // Emergency response experience
                        Text(
                            text = if (isTamil) "முந்தைய பேரிடர் மீட்பு அனுபவம் உண்டா?" else "Prior Emergency / Disaster Response Experience?",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { viewModel.volEmergencyExp.value = "Yes / ஆம்" }
                            ) {
                                RadioButton(
                                    selected = volEmergencyExp.startsWith("Yes"),
                                    onClick = { viewModel.volEmergencyExp.value = "Yes / ஆம்" }
                                )
                                Text(if (isTamil) "ஆம் (Yes)" else "Yes")
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { viewModel.volEmergencyExp.value = "No / இல்லை" }
                            ) {
                                RadioButton(
                                    selected = volEmergencyExp.startsWith("No"),
                                    onClick = { viewModel.volEmergencyExp.value = "No / இல்லை" }
                                )
                                Text(if (isTamil) "இல்லை (No - First Time)" else "No (Eager to learn)")
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Submit Button
                        Button(
                            onClick = { viewModel.submitVolunteerRegistration() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("submit_volunteer_btn"),
                            shape = RoundedCornerShape(12.dp),
                            enabled = !isSubmitting
                        ) {
                            if (isSubmitting) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                Icon(Icons.Default.Check, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isTamil) "பதிவு செய் (Submit Registration)" else "Submit Registration",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            // Existing Registered Volunteers Counter & List
            if (volunteers.isNotEmpty()) {
                item {
                    Text(
                        text = if (isTamil) "சமீபத்தில் பதிவு செய்த தன்னார்வலர்கள் (${volunteers.size})"
                        else "Recently Registered Volunteers (${volunteers.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(volunteers.take(5)) { vol ->
                    VolunteerItemCard(volunteer = vol, isTamil = isTamil)
                }
            }
        }

        // Success Confirmation Dialog
        registeredCode?.let { code ->
            VolunteerSuccessDialog(
                code = code,
                isTamil = isTamil,
                onDismiss = { viewModel.dismissVolunteerSuccessDialog() }
            )
        }
    }
}

@Composable
fun VolunteerItemCard(volunteer: VolunteerEntity, isTamil: Boolean) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.size(42.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = volunteer.name.firstOrNull()?.toString()?.uppercase() ?: "V",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = volunteer.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${volunteer.village}, ${volunteer.district}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = volunteer.registrationCode,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun VolunteerSuccessDialog(
    code: String,
    isTamil: Boolean,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("volunteer_success_dialog")
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
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isTamil) "பதிவு வெற்றிகரமாக முடிந்தது!" else "Registration Successful!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Exact requested message
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isTamil) "“டெல்டா வாலண்டியர்ஸ் டிரஸ்ட்டில் பதிவு செய்ததற்கு நன்றி. எங்கள் குழுவினர் உங்களை விரைவில் தொடர்பு கொள்வார்கள்.”"
                            else "“Thank you for registering with Delta Volunteers Trust. Our team will contact you.”",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Volunteer Code: $code",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isTamil) "நன்றி" else "Continue")
                }
            }
        }
    }
}
