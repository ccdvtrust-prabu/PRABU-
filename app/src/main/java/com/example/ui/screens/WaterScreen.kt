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
import com.example.model.WaterTopic
import com.example.ui.DeltaViewModel

@Composable
fun WaterScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    val selectedTopic by viewModel.selectedWaterTopic.collectAsState()

    var activeTab by remember { mutableStateOf(0) } // 0: Key Topics, 1: TDS & Salinity Guide, 2: School Water Protocol

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("water_screen"),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
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
                            Text(text = "💧", fontSize = 32.sp)
                            Column {
                                Text(
                                    text = if (isTamil) "டெல்டா குடிநீர் பாதுகாப்பு & தரம்" else "Water Security & Quality",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = if (isTamil) "உயிர் காக்கும் தூய குடிநீர் இயக்கம்" else "Safe Water, Oorani & Groundwater Protection",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTamil) "டெல்டாவில் கடல்நீர் உட்புகுதல் மற்றும் நன்னீர் மாசுபாடு தடுப்பு, பள்ளி மாணவர்களுக்கான தூய குடிநீர், மற்றும் மழைநீர் சேகரிப்பு குறித்த அறிவியல் பூர்வ வழிகாட்டிகள்."
                            else "Preventing salinity intrusion, ensuring zero water-borne diseases in schools, and desilting historical Ooranis across Cauvery tail-end districts.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Tab bar
            item {
                TabRow(
                    selectedTabIndex = activeTab,
                    containerColor = Color.Transparent,
                    divider = {}
                ) {
                    Tab(
                        selected = activeTab == 0,
                        onClick = { activeTab = 0 },
                        text = { Text(if (isTamil) "முக்கிய தலைப்புகள்" else "Water Topics") }
                    )
                    Tab(
                        selected = activeTab == 1,
                        onClick = { activeTab = 1 },
                        text = { Text(if (isTamil) "TDS & உப்புத்தன்மை" else "TDS Scale") }
                    )
                    Tab(
                        selected = activeTab == 2,
                        onClick = { activeTab = 2 },
                        text = { Text(if (isTamil) "பள்ளி குடிநீர்" else "School Safety") }
                    )
                }
            }

            when (activeTab) {
                0 -> {
                    items(AppDataStore.WATER_TOPICS) { topic ->
                        WaterTopicCard(
                            topic = topic,
                            isTamil = isTamil,
                            onClick = { viewModel.selectWaterTopic(topic) }
                        )
                    }
                }
                1 -> {
                    // Interactive TDS scale card
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = if (isTamil) "குடிநீர் TDS மற்றும் உப்புத்தன்மை வழிகாட்டி" else "Drinking Water TDS & Salinity Benchmark",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = if (isTamil) "BIS 10500 இந்திய தர நிர்ணய அளவீடுகள் (mg/L அல்லது ppm)"
                                    else "Bureau of Indian Standards (BIS 10500) parameters",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                val tdsLevels = listOf(
                                    Triple("< 300 ppm", if (isTamil) "சிறந்தது (Excellent)" else "Excellent Taste & Mineral balance", Color(0xFF16A34A)),
                                    Triple("300 - 600 ppm", if (isTamil) "ஏற்றுக்கொள்ளத்தக்கது (Good)" else "Good for domestic drinking", Color(0xFF0284C7)),
                                    Triple("600 - 900 ppm", if (isTamil) "சுமார் (Fair)" else "Acceptable if no alternate source", Color(0xFFD97706)),
                                    Triple("> 1200 ppm", if (isTamil) "குடிக்க உகந்தது அல்ல (Unacceptable)" else "Unfit for drinking - Saline/brackish", Color(0xFFDC2626))
                                )

                                tdsLevels.forEach { (level, desc, col) ->
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = col.copy(alpha = 0.12f),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = level,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = col,
                                                fontSize = 15.sp
                                            )
                                            Text(
                                                text = desc,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 12.sp,
                                                color = col
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = if (isTamil) "💡 குறிப்பு: கடலோர நாகப்பட்டினம் மற்றும் மயிலாடுதுறை பகுதிகளில் ஆழ்துளை கிணறுகளில் 2000 ppm-க்கு மேல் உப்புத்தன்மை காணப்படுகிறது. அங்கு மழைநீர் சேகரிப்பு ஊரணிகளே பாதுகாப்பான தீர்வு."
                                    else "💡 Delta Insight: Coastal aquifers in Nagapattinam/Mayiladuthurai often exceed 2,000 ppm due to sea-water intrusion. Traditional surface water Ooranis with slow-sand filters remain the most sustainable solution.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }
                }
                2 -> {
                    // School Drinking Water Safety Protocol
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = if (isTamil) "பள்ளி குடிநீர் பாதுகாப்பு நெறிமுறை" else "School Drinking Water Safety Protocol",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                val schoolPointsEn = listOf(
                                    "Monthly testing of overhead tank water for E. coli, coliform bacteria and residual chlorine.",
                                    "Periodic deep cleaning and sanitization of water storage tanks with bleaching powder every 15 days.",
                                    "Dedicated child-friendly handwashing and drinking tap stations with zero water stagnation.",
                                    "Installation of biosand filters or gravity filters in government schools lacking RO maintenance.",
                                    "Formation of 'Student Water Champions' club to audit tap leakages and educate peers."
                                )
                                val schoolPointsTa = listOf(
                                    "பள்ளி மேல்நிலைத் தொட்டிகளில் ஈ.கோலை பாக்டீரியா மற்றும் குளோரின் அளவை மாதம் ஒருமுறை பரிசோதித்தல்.",
                                    "ஒவ்வொரு 15 நாட்களுக்கு ஒருமுறை குடிநீர் தொட்டிகளை முழுமையாக கழுவி பிளீச்சிங் பவுடர் இட்டு தூய்மைப்படுத்துதல்.",
                                    "மாணவர்கள் சிரமமின்றி பயன்படுத்தும் வகையில் முறையான கழிவுநீர் வடிகால் வசதியுடன் கூடிய குடிநீர் குழாய்கள்.",
                                    "மின்சார தட்டுப்பாடு உள்ள பள்ளிகளில் பயோ-மணல் (Bio-sand) வடிகட்டிகளை நிறுவுதல்.",
                                    "பள்ளி மாணவர்களிடையே தண்ணீர் சேமிப்பு மற்றும் சுகாதார பழக்கங்களை உருவாக்க 'மாணவர் நீர் காவலர்கள்' குழு அமைத்தல்."
                                )

                                val list = if (isTamil) schoolPointsTa else schoolPointsEn
                                list.forEachIndexed { i, p ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text(text = "${i + 1}. ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                        Text(
                                            text = p,
                                            style = MaterialTheme.typography.bodyMedium,
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

        // Selected Topic Dialog
        selectedTopic?.let { topic ->
            WaterTopicDetailDialog(
                topic = topic,
                isTamil = isTamil,
                onDismiss = { viewModel.selectWaterTopic(null) }
            )
        }
    }
}

@Composable
fun WaterTopicCard(
    topic: WaterTopic,
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
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(50.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = topic.iconEmoji, fontSize = 26.sp)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isTamil) topic.titleTa else topic.titleEn,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (isTamil) topic.summaryTa else topic.summaryEn,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
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
fun WaterTopicDetailDialog(
    topic: WaterTopic,
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
                        Text(text = topic.iconEmoji, fontSize = 28.sp)
                        Text(
                            text = if (isTamil) topic.titleTa else topic.titleEn,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = if (isTamil) topic.summaryTa else topic.summaryEn,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (isTamil) "முக்கிய நடைமுறை குறிப்புகள்:" else "Practical Guidelines:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                val tips: List<String> = if (isTamil) topic.practicalTipsTa else topic.practicalTipsEn
                for (tip in tips) {
                    Row(
                        modifier = Modifier.padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(text = "💧 ", fontSize = 12.sp)
                        Text(
                            text = tip,
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
                    Text(if (isTamil) "முடிந்தது" else "Close")
                }
            }
        }
    }
}
