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
import com.example.model.DisasterGuide
import com.example.ui.DeltaViewModel

@Composable
fun DisasterScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    val selectedGuide by viewModel.selectedDisasterGuide.collectAsState()
    val checkedKitItems by viewModel.checkedKitItems.collectAsState()

    var activeTab by remember { mutableStateOf(0) } // 0: Guides, 1: Emergency Kit Checklist, 2: Vulnerable Care

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("disaster_screen"),
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
                            Text(text = "🌊", fontSize = 32.sp)
                            Column {
                                Text(
                                    text = if (isTamil) "பேரிடர் மேலாண்மை & தயார்நிலை" else "Disaster Preparedness Guide",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                                Text(
                                    text = if (isTamil) "டெல்டா மக்களுக்கு உயிர்காக்கும் வழிகாட்டிகள்" else "Life-saving awareness for Delta families",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTamil) "கஜா புயல் போன்ற தீவிரப் புயல்கள், பெருவெள்ளம், வெயில் கொடுமை மற்றும் அவசரகால முன்னெச்சரிக்கைகள் குறித்த முழுமையான வழிகாட்டுதல்கள்."
                            else "Standard operating procedures and preventive actions during cyclones, floods, heatwaves, and emergency evacuation in Cauvery Delta.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Tab Selector
            item {
                TabRow(
                    selectedTabIndex = activeTab,
                    containerColor = Color.Transparent,
                    divider = {}
                ) {
                    Tab(
                        selected = activeTab == 0,
                        onClick = { activeTab = 0 },
                        text = { Text(if (isTamil) "வழிகாட்டிகள்" else "Hazards") }
                    )
                    Tab(
                        selected = activeTab == 1,
                        onClick = { activeTab = 1 },
                        text = { Text(if (isTamil) "அவசர பெட்டி" else "Emergency Kit") }
                    )
                    Tab(
                        selected = activeTab == 2,
                        onClick = { activeTab = 2 },
                        text = { Text(if (isTamil) "பாதுகாப்பு" else "Vulnerable Care") }
                    )
                }
            }

            when (activeTab) {
                0 -> {
                    // Disaster Scenario Cards
                    items(AppDataStore.DISASTER_GUIDES) { guide ->
                        DisasterGuideCard(
                            guide = guide,
                            isTamil = isTamil,
                            onClick = { viewModel.selectDisasterGuide(guide) }
                        )
                    }
                }
                1 -> {
                    // Emergency Kit Interactive Checklist
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(text = "🎒", fontSize = 26.sp)
                                    Column {
                                        Text(
                                            text = if (isTamil) "அவசர தயார்நிலை பெட்டி (Emergency Kit)" else "Emergency Survival Kit Checklist",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "${checkedKitItems.size} / ${AppDataStore.EMERGENCY_KIT_ITEMS.size} " +
                                                    if (isTamil) "பொருட்கள் தயார்" else "items packed",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                AppDataStore.EMERGENCY_KIT_ITEMS.forEach { item ->
                                    val isChecked = checkedKitItems.contains(item.id)
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { viewModel.toggleKitItem(item.id) }
                                            .padding(vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = isChecked,
                                            onCheckedChange = { viewModel.toggleKitItem(item.id) }
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(text = item.iconEmoji, fontSize = 20.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = if (isTamil) item.nameTa else item.nameEn,
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal
                                            )
                                            Text(
                                                text = if (isTamil) item.importanceTa else item.importanceEn,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                2 -> {
                    // Vulnerable Group Protection & Safe Shelter
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = if (isTamil) "முதியோர், மாற்றுத்திறனாளிகள், குழந்தைகள் & கர்ப்பிணிகளுக்கான சிறப்பு பாதுகாப்பு"
                                    else "Special Protection for Vulnerable Groups",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                val carePointsEn = listOf(
                                    "Prioritize evacuation of bedridden elderly, pregnant mothers and newborns 24 hours before cyclone landfall.",
                                    "Keep 10-day buffer supply of chronic medicines (BP, diabetes, cardiac, insulin) sealed in waterproof pouches.",
                                    "Assign 2 designated youth volunteers per street to physically assist persons with disabilities during flood alerts.",
                                    "Ensure cyclone relief centres have accessible ground-floor rooms, clean breast-feeding privacy areas, and warm blankets.",
                                    "Always attach an emergency wristband or ID tag with guardian contact to small children."
                                )
                                val carePointsTa = listOf(
                                    "புயல் அல்லது வெள்ளம் தொடங்குவதற்கு 24 மணி நேரத்திற்கு முன்பே முதியவர்கள், கர்ப்பிணிகள், பச்சிளம் குழந்தைகளை பாதுகாப்பான முகாமுக்கு அழைத்துச் செல்ல வேண்டும்.",
                                    "நீரிழிவு, இரத்த அழுத்தம், இதய நோயாளிகளின் தொடர் மருந்துகளை 10 நாட்களுக்கு முன்னதாகவே நீர்புகா பைகளில் பத்திரப்படுத்தவும்.",
                                    "ஒவ்வொரு தெருவிலும் உள்ள மாற்றுத்திறனாளிகளை அவசர நேரத்தில் கைத்தாங்கலாக மீட்க 2 தன்னார்வல இளைஞர்களை முன்கூட்டியே நியமிக்கவும்.",
                                    "புயல் நிவாரண முகாம்களில் தரைத்தள அறைகள், தாய்மார்களுக்கான பிரத்யேக பாலூட்டும் வசதி மற்றும் கம்பளிகள் இருப்பதை உறுதி செய்க.",
                                    "குழந்தைகளின் கைகளில் பெற்றோர் அல்லது பாதுகாவலரின் தொலைபேசி எண் எழுதிய அடையாள அட்டை அல்லது ரிஸ்ட் பேண்ட் அணிவிக்கவும்."
                                )

                                val list = if (isTamil) carePointsTa else carePointsEn
                                list.forEachIndexed { i, p ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text(
                                            text = "• ",
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 16.sp
                                        )
                                        Text(
                                            text = p,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            lineHeight = 20.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Detailed Disaster Modal
        selectedGuide?.let { guide ->
            DisasterDetailDialog(
                guide = guide,
                isTamil = isTamil,
                onDismiss = { viewModel.selectDisasterGuide(null) }
            )
        }
    }
}

@Composable
fun DisasterGuideCard(
    guide: DisasterGuide,
    isTamil: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.size(52.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = guide.iconEmoji, fontSize = 26.sp)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isTamil) guide.titleTa else guide.titleEn,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (isTamil) guide.subtitleTa else guide.subtitleEn,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Details",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun DisasterDetailDialog(
    guide: DisasterGuide,
    isTamil: Boolean,
    onDismiss: () -> Unit
) {
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
                    .padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(text = guide.iconEmoji, fontSize = 28.sp)
                        Column {
                            Text(
                                text = if (isTamil) guide.titleTa else guide.titleEn,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (isTamil) guide.subtitleTa else guide.subtitleEn,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Do's
                Text(
                    text = if (isTamil) "✅ செய்ய வேண்டியவை (Do's):" else "✅ What To Do (Do's):",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF16A34A),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                val dos: List<String> = if (isTamil) guide.dosTa else guide.dosEn
                for (item in dos) {
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(text = "• ", color = Color(0xFF16A34A), fontWeight = FontWeight.Bold)
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Don'ts
                Text(
                    text = if (isTamil) "❌ தவிர்க்க வேண்டியவை (Don'ts):" else "❌ What NOT To Do (Don'ts):",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                val donts: List<String> = if (isTamil) guide.dontsTa else guide.dontsEn
                for (item in donts) {
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(text = "• ", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isTamil) "புரிந்தது" else "Understood")
                }
            }
        }
    }
}
