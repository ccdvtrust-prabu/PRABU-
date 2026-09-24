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
import com.example.model.EventItem
import com.example.ui.DeltaViewModel

@Composable
fun EventsScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    val rsvpEvent by viewModel.rsvpEvent.collectAsState()
    val rsvpName by viewModel.rsvpName.collectAsState()
    val rsvpPhone by viewModel.rsvpPhone.collectAsState()
    val rsvpDistrict by viewModel.rsvpDistrict.collectAsState()
    val rsvpSuccessMessage by viewModel.rsvpSuccessMessage.collectAsState()

    var filterCategory by remember { mutableStateOf("All") }

    val categories = listOf("All", "Disaster Training", "Environment", "Water Security", "Education")

    val filteredEvents = remember(filterCategory) {
        if (filterCategory == "All") {
            AppDataStore.EVENTS
        } else {
            AppDataStore.EVENTS.filter { it.category == filterCategory }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("events_screen"),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = "🗓️", fontSize = 32.sp)
                            Column {
                                Text(
                                    text = if (isTamil) "நிகழ்வுகள் & பயிற்சிகள்" else "Events & Training Programmes",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                                Text(
                                    text = if (isTamil) "தன்னார்வலர் திறனூட்டல் மற்றும் களப்பயிற்சி" else "Disaster mock drills, workshops & campaigns",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTamil) "படகுகள் இயக்கம், முதல் உதவி பயிற்சிகள், பள்ளி சூழல் மன்ற விழாக்கள் மற்றும் கிராம விழிப்புணர்வு முகாம்களில் பங்குபெறுங்கள்."
                            else "Participate in hands-on mock drills, water testing workshops, mangrove planting camps and village meetings across Delta districts.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Category filter chips
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.take(3).forEach { cat ->
                        FilterChip(
                            selected = filterCategory == cat,
                            onClick = { filterCategory = cat },
                            label = { Text(cat) }
                        )
                    }
                }
            }

            // Events List
            items(filteredEvents) { event ->
                EventCardItem(
                    event = event,
                    isTamil = isTamil,
                    onRsvp = { viewModel.openRsvpDialog(event) }
                )
            }
        }

        // RSVP Dialog
        rsvpEvent?.let { event ->
            RsvpDialog(
                event = event,
                isTamil = isTamil,
                name = rsvpName,
                phone = rsvpPhone,
                district = rsvpDistrict,
                successMessage = rsvpSuccessMessage,
                onNameChange = { viewModel.rsvpName.value = it },
                onPhoneChange = { viewModel.rsvpPhone.value = it },
                onDistrictChange = { viewModel.rsvpDistrict.value = it },
                onSubmit = { viewModel.submitRsvp() },
                onDismiss = { viewModel.closeRsvpDialog() }
            )
        }
    }
}

@Composable
fun EventCardItem(
    event: EventItem,
    isTamil: Boolean,
    onRsvp: () -> Unit
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
                        text = event.dateStr,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = event.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (isTamil) event.titleTa else event.titleEn,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${event.venue}, ${event.district}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (isTamil) event.descriptionTa else event.descriptionEn,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isTamil) "இலவச அனுமதி" else "Free Registration",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Button(
                    onClick = onRsvp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("rsvp_btn_${event.id}")
                ) {
                    Text(if (isTamil) "பதிவு செய்க" else "RSVP Now")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RsvpDialog(
    event: EventItem,
    isTamil: Boolean,
    name: String,
    phone: String,
    district: String,
    successMessage: String?,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onDistrictChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onDismiss: () -> Unit
) {
    var districtExpanded by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isTamil) "நிகழ்வு முன்பதிவு" else "Event RSVP",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isTamil) event.titleTa else event.titleEn,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                if (successMessage != null) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFDCFCE7),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF16A34A))
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = successMessage,
                                color = Color(0xFF166534),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isTamil) "முடிந்தது" else "Close")
                    }
                } else {
                    OutlinedTextField(
                        value = name,
                        onValueChange = onNameChange,
                        label = { Text(if (isTamil) "பெயர்" else "Your Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = onPhoneChange,
                        label = { Text(if (isTamil) "தொலைபேசி எண்" else "Mobile Number") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

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
                                        onDistrictChange(d)
                                        districtExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Button(
                        onClick = onSubmit,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        enabled = name.isNotBlank() && phone.isNotBlank()
                    ) {
                        Text(if (isTamil) "முன்பதிவை உறுதி செய்" else "Confirm RSVP")
                    }
                }
            }
        }
    }
}
