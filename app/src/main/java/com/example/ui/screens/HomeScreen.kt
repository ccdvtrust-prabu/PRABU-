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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppDataStore
import com.example.data.NewsEntity
import com.example.model.FocusArea
import com.example.model.StatCard
import com.example.ui.AppScreen
import com.example.ui.components.DeltaOfficialLogoCard
import com.example.ui.components.DonationBankCard
import com.example.ui.components.JointInitiativeBannerCard
import com.example.ui.components.OfficialContactCard
import com.example.ui.components.ThirukkuralQuoteCard

@Composable
fun HomeScreen(
    isTamil: Boolean,
    newsList: List<NewsEntity>,
    onNavigate: (AppScreen) -> Unit,
    onSelectFocusArea: (FocusArea) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Hero Section with Gradient
        item {
            HeroBanner(
                isTamil = isTamil,
                onVolunteerClick = { onNavigate(AppScreen.VOLUNTEER) },
                onProjectsClick = { onNavigate(AppScreen.PROJECTS) },
                onReportClick = { onNavigate(AppScreen.REPORT_NEED) }
            )
        }

        // Official Delta Volunteers Trust Logo Card
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                DeltaOfficialLogoCard(isTamil = isTamil)
            }
        }

        // Joint Initiative with Priyam Trust & NDSO
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                JointInitiativeBannerCard(isTamil = isTamil)
            }
        }

        // Sacred Thirukkural Guidance Quote
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                ThirukkuralQuoteCard(isTamil = isTamil)
            }
        }

        // Live Alerts / Urgent News ticker if any
        val urgentNews = newsList.filter { it.isUrgent }
        if (urgentNews.isNotEmpty()) {
            item {
                UrgentAlertCard(news = urgentNews.first(), isTamil = isTamil) {
                    onNavigate(AppScreen.NEWS)
                }
            }
        }

        // Impact Stats Counters
        item {
            SectionHeader(
                title = if (isTamil) "நமது கூட்டுத் தாக்கம்" else "Our Collective Impact",
                subtitle = if (isTamil) "டெல்டா மாவட்டங்களில் களப்பணி" else "Action Across Tamil Nadu Delta"
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(AppDataStore.STATS) { stat ->
                    StatCardItem(stat = stat, isTamil = isTamil)
                }
            }
        }

        // Quick Primary Actions Cards
        item {
            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader(
                title = if (isTamil) "விரைவு சேவைகள்" else "Quick Actions",
                subtitle = if (isTamil) "சமூகத்தில் மாற்றத்தை ஏற்படுத்த" else "Get involved and report issues"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickActionCard(
                    title = if (isTamil) "தன்னார்வலர் சேர்க்கை" else "Volunteer With Us",
                    iconEmoji = "🤝",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.weight(1f),
                    testTag = "home_volunteer_btn",
                    onClick = { onNavigate(AppScreen.VOLUNTEER) }
                )
                QuickActionCard(
                    title = if (isTamil) "தேவை / புகார் பதிவு" else "Report Issue",
                    iconEmoji = "📢",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.weight(1f),
                    testTag = "home_report_btn",
                    onClick = { onNavigate(AppScreen.REPORT_NEED) }
                )
                QuickActionCard(
                    title = if (isTamil) "பேரிடர் பாதுகாப்பு" else "Disaster Guide",
                    iconEmoji = "🌊",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = Modifier.weight(1f),
                    testTag = "home_disaster_btn",
                    onClick = { onNavigate(AppScreen.DISASTER) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Prominent Beneficiary Assistance Request Form Banner
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { onNavigate(AppScreen.BENEFICIARY_FORM) }
                    .testTag("home_beneficiary_form_banner")
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "📝", fontSize = 24.sp)
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isTamil) "பயனாளி உதவி கோரும் விண்ணப்பம்" else "Beneficiary Assistance Form",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = if (isTamil) "கல்வி, குடிநீர், மருத்துவம் & வாழ்வாதார உதவி பெற விண்ணப்பிக்க" else "Official form for Education, Water, Medical & Livelihood aid",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Open Form",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        // Focus Areas Grid Highlight
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = if (isTamil) "கவனக் குவிப்பு பகுதிகள்" else "Our Focus Areas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (isTamil) "8 முக்கிய செயல்பாட்டுத் தளங்கள்" else "8 core pillars of action",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                TextButton(onClick = { onNavigate(AppScreen.FOCUS_AREAS) }) {
                    Text(if (isTamil) "அனைத்தும் >" else "View All >")
                }
            }
        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AppDataStore.FOCUS_AREAS.take(4).forEach { area ->
                    FocusAreaHomeCard(
                        area = area,
                        isTamil = isTamil,
                        onClick = { onSelectFocusArea(area) }
                    )
                }
            }
        }

        // Latest Announcements / News
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = if (isTamil) "சமீபத்திய அறிவிப்புகள்" else "Latest Announcements",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (isTamil) "திட்ட நிகழ்வுகள் & எச்சரிக்கைகள்" else "NGO updates & alerts",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                TextButton(onClick = { onNavigate(AppScreen.NEWS) }) {
                    Text(if (isTamil) "அனைத்தும் >" else "More >")
                }
            }
        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                newsList.take(3).forEach { news ->
                    HomeNewsItem(news = news, isTamil = isTamil) {
                        onNavigate(AppScreen.NEWS)
                    }
                }
            }
        }

        // Trust Mission Quote Banner
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🌾",
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isTamil) "“ஒப்புரவறிதல் – மக்கள் நலம், பேரிடர் மேலாண்மை & சுற்றுச்சூழல் இயக்கம்”"
                        else "“People's Initiative for a Better Delta • Together We Serve The Delta”",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isTamil) "டெல்டா தன்னார்வலர்கள் அறக்கட்டளை வேதாரண்யம், நாகப்பட்டினம், திருவாரூர், தஞ்சாவூர், மயிலாடுதுறை மாவட்டங்களில் பொதுமக்களுடன் இணைந்து களப்பணியாற்றுகிறது."
                        else "Delta Volunteers Trust works directly on the ground across Vedaranyam, Nagapattinam, Tiruvarur, Thanjavur, and Mayiladuthurai districts.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Head Office Contact Card on Home Screen
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                OfficialContactCard(isTamil = isTamil)
            }
        }
    }
}

@Composable
fun HeroBanner(
    isTamil: Boolean,
    onVolunteerClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onReportClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0369A1),
                        Color(0xFF0284C7),
                        Color(0xFF0D9488)
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.2f),
                    modifier = Modifier.size(52.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "🌍", fontSize = 26.sp)
                    }
                }
                Column {
                    Text(
                        text = if (isTamil) AppDataStore.TRUST_NAME_TA else AppDataStore.TRUST_NAME,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isTamil) "வேதாரண்யம் • நாகப்பட்டினம்" else "Vedaranyam • Nagapattinam",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFE600)
                        )
                        Text(
                            text = " • ${AppDataStore.REGD_NO}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFBCEBFC)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = if (isTamil) "பேரிடர் மேலாண்மை, பாதுகாப்பான குடிநீர், சூழலியல் மற்றும் கல்விக்காக டெல்டா மாவட்டங்களில் அர்ப்பணிப்புடன் களப்பணியாற்றும் தன்னார்வ இயக்கம்."
                else "Community-based NGO driving disaster response, drinking water security, coastal resilience, and education across Tamil Nadu Delta.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.95f),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Three Primary Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onVolunteerClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF22C55E),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("hero_volunteer_btn")
                ) {
                    Text(
                        text = if (isTamil) "இணைய" else "Volunteer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }

                FilledTonalButton(
                    onClick = onProjectsClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color.White.copy(alpha = 0.25f),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("hero_projects_btn")
                ) {
                    Text(
                        text = if (isTamil) "திட்டங்கள்" else "Projects",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }

                OutlinedButton(
                    onClick = onReportClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFFED7AA)
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFED7AA)),
                    modifier = Modifier
                        .weight(1.1f)
                        .testTag("hero_report_btn")
                ) {
                    Text(
                        text = if (isTamil) "புகார் பதிவு" else "Report Issue",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun UrgentAlertCard(
    news: NewsEntity,
    isTamil: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Alert",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isTamil) "முக்கிய எச்சரிக்கை" else "EMERGENCY ALERT",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "• ${news.dateStr}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
                Text(
                    text = if (isTamil) news.titleTa else news.titleEn,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "View",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun StatCardItem(stat: StatCard, isTamil: Boolean) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.width(135.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stat.iconEmoji, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stat.number,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = if (isTamil) stat.labelTa else stat.labelEn,
                style = MaterialTheme.typography.labelSmall,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    iconEmoji: String,
    color: Color,
    modifier: Modifier = Modifier,
    testTag: String,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = color,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .testTag(testTag)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = iconEmoji, fontSize = 28.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Composable
fun FocusAreaHomeCard(
    area: FocusArea,
    isTamil: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = area.iconEmoji, fontSize = 28.sp)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isTamil) area.titleTa else area.titleEn,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (isTamil) area.taglineTa else area.taglineEn,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
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
fun HomeNewsItem(
    news: NewsEntity,
    isTamil: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = news.category,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = news.dateStr,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isTamil) news.titleTa else news.titleEn,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
