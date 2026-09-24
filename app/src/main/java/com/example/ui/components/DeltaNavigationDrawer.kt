package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppDataStore
import com.example.ui.AppScreen

data class NavSectionItem(
    val screen: AppScreen,
    val titleEn: String,
    val titleTa: String,
    val icon: ImageVector,
    val emoji: String
)

@Composable
fun DeltaDrawerContent(
    currentScreen: AppScreen,
    isTamil: Boolean,
    onNavigate: (AppScreen) -> Unit,
    onCloseDrawer: () -> Unit
) {
    val navItems = listOf(
        NavSectionItem(AppScreen.HOME, "Home", "முகப்பு", Icons.Default.Home, "🏠"),
        NavSectionItem(AppScreen.ABOUT, "About Us", "எங்களை பற்றி", Icons.Default.Info, "ℹ️"),
        NavSectionItem(AppScreen.FOCUS_AREAS, "Our Focus Areas", "கவனக் குவிப்பு பகுதிகள்", Icons.Default.Category, "🎯"),
        NavSectionItem(AppScreen.VOLUNTEER, "Volunteer Registration", "தன்னார்வலர் சேர்க்கை", Icons.Default.PersonAdd, "🤝"),
        NavSectionItem(AppScreen.REPORT_NEED, "Report Community Need", "சமுதாய தேவை பதிவு", Icons.Default.ReportProblem, "📢"),
        NavSectionItem(AppScreen.BENEFICIARY_FORM, "Beneficiary Request Form", "பயனாளி உதவி படிவம்", Icons.Default.Description, "📝"),
        NavSectionItem(AppScreen.DISASTER, "Disaster Preparedness", "பேரிடர் தயார்நிலை", Icons.Default.Shield, "🌊"),
        NavSectionItem(AppScreen.WATER, "Water Security", "குடிநீர் பாதுகாப்பு", Icons.Default.WaterDrop, "💧"),
        NavSectionItem(AppScreen.PROJECTS, "Projects & Impact", "திட்டங்கள் & தாக்கம்", Icons.AutoMirrored.Filled.Assignment, "📋"),
        NavSectionItem(AppScreen.EVENTS, "Events & Training", "நிகழ்வுகள் & பயிற்சிகள்", Icons.Default.Event, "🗓️"),
        NavSectionItem(AppScreen.GALLERY, "Photo Gallery", "புகைப்பட தொகுப்பு", Icons.Default.PhotoLibrary, "🖼️"),
        NavSectionItem(AppScreen.NEWS, "News & Updates", "செய்திகள் & தகவல்கள்", Icons.Default.Campaign, "📰"),
        NavSectionItem(AppScreen.CONTACT, "Contact Us", "தொடர்பு கொள்ள", Icons.Default.ContactPhone, "📞"),
        NavSectionItem(AppScreen.ADMIN, "Admin Portal", "நிர்வாகி பதிவு", Icons.Default.AdminPanelSettings, "⚙️")
    )

    ModalDrawerSheet(
        modifier = Modifier.width(310.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Text(
                                text = "🌍",
                                fontSize = 28.sp,
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                        Column {
                            Text(
                                text = if (isTamil) AppDataStore.TRUST_NAME_TA else AppDataStore.TRUST_NAME,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "வேதாரண்யம் • Vedaranyam",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isTamil) "“ஒப்புரவறிதல் – ${AppDataStore.REGD_NO}”"
                        else "“${AppDataStore.TAGLINE_EN}”",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Navigation Items
            navItems.forEach { item ->
                val selected = currentScreen == item.screen
                NavigationDrawerItem(
                    icon = {
                        Text(text = item.emoji, fontSize = 20.sp)
                    },
                    label = {
                        Text(
                            text = if (isTamil) item.titleTa else item.titleEn,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    selected = selected,
                    onClick = {
                        onNavigate(item.screen)
                        onCloseDrawer()
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                        .testTag("nav_item_${item.screen.name}")
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DeltaBottomNavigation(
    currentScreen: AppScreen,
    isTamil: Boolean,
    onNavigate: (AppScreen) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
    ) {
        val items = listOf(
            Triple(AppScreen.HOME, if (isTamil) "முகப்பு" else "Home", Icons.Default.Home),
            Triple(AppScreen.FOCUS_AREAS, if (isTamil) "பணிகள்" else "Focus", Icons.Default.Category),
            Triple(AppScreen.VOLUNTEER, if (isTamil) "இணைய" else "Volunteer", Icons.Default.PersonAdd),
            Triple(AppScreen.REPORT_NEED, if (isTamil) "புகார்" else "Report", Icons.Default.ReportProblem),
            Triple(AppScreen.DISASTER, if (isTamil) "பாதுகாப்பு" else "Preparedness", Icons.Default.Shield)
        )

        items.forEach { (screen, label, icon) ->
            val selected = currentScreen == screen
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(screen) },
                icon = { Icon(imageVector = icon, contentDescription = label) },
                label = { Text(text = label, maxLines = 1, fontSize = 11.sp) },
                modifier = Modifier.testTag("bottom_nav_${screen.name}")
            )
        }
    }
}
