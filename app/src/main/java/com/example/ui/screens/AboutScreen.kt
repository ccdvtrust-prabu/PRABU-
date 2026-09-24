package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppDataStore
import com.example.ui.components.DeltaOfficialLogoCard
import com.example.ui.components.DonationBankCard
import com.example.ui.components.JointInitiativeBannerCard
import com.example.ui.components.LeaderProfileCard
import com.example.ui.components.OfficialContactCard
import com.example.ui.components.StatutoryCredentialsCard
import com.example.ui.components.ThirukkuralQuoteCard

@Composable
fun AboutScreen(isTamil: Boolean) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("about_screen"),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Official Logo Card
        item {
            DeltaOfficialLogoCard(isTamil = isTamil)
        }

        // Joint Initiative with Priyam Trust & NDSO
        item {
            JointInitiativeBannerCard(isTamil = isTamil)
        }

        // Sacred Thirukkural Guidance Quote Card
        item {
            ThirukkuralQuoteCard(isTamil = isTamil)
        }

        // Official Trust Charter & 20-Year Legacy
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "🌟", fontSize = 24.sp)
                        Column {
                            Text(
                                text = if (isTamil) "20 ஆண்டுகால சமுதாயப் பணி & 17 UN இலக்குகள்" else "20 Years of Service & 17 UN SDGs",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "United Nations Sustainable Development Goals",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isTamil) AppDataStore.CHARTER_TEXT_TA else AppDataStore.CHARTER_TEXT_EN,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Chief Coordinator / Leadership Profile
        item {
            LeaderProfileCard(isTamil = isTamil)
        }

        // Statutory & Legal Credentials Card (12AA, 80G, FSSAI, DARPAN)
        item {
            StatutoryCredentialsCard(isTamil = isTamil)
        }

        // Trust Bank Account Details for Donations
        item {
            DonationBankCard(isTamil = isTamil)
        }

        // Vision & Mission
        item {
            AboutCard(
                icon = "🎯",
                title = if (isTamil) "நமது நோக்கு (Vision)" else "Our Vision",
                content = if (isTamil) "இயற்கைப் பேரிடர்களைத் தாங்கி நிற்கும் சமுதாயப் பாதுகாப்பு, ஒவ்வொரு கிராமக் குடும்பத்திற்கும் சுகாதாரமான நன்னீர் பாதுகாப்பு, பசுமை படர்ந்த நதிக்கரைகள் மற்றும் தன்னிறைவு பெற்ற சமூகத் தலைமைத்துவத்தைக் கொண்ட ஒரு வளமான தமிழ்நாடு டெல்டா பகுதி."
                else "A resilient, water-secure, and climate-safe Tamil Nadu Delta where every community possesses local leadership, ecological harmony, and zero-casualty disaster preparedness."
            )
        }

        item {
            AboutCard(
                icon = "🚀",
                title = if (isTamil) "நமது பணி இலக்கு (Mission)" else "Our Mission",
                content = if (isTamil) "பொதுமக்கள், இளைஞர்கள், விவசாயிகள், பள்ளிகள் மற்றும் அரசுத் துறைகளை ஒன்றிணைத்து பேரிடர் மீட்புப் பயிற்சிகள், கிராம நீர்நிலைகள் மீட்டெடுப்பு, சதுப்புநில அலையாத்திக் காடுகள் விரிவாக்கம், குடிநீர் தரப் பரிசோதனை மற்றும் விழிப்புணர்வை இடைவிடாது முன்னெடுப்பது."
                else "To connect grassroots citizens, youth, fisherfolk and institutions to deliver actionable disaster response, rural waterbody desilting, coastal mangrove bio-shields, safe school drinking water, and civic issue resolution without barriers."
            )
        }

        // Core Objectives
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
                        Text(text = "📋", fontSize = 24.sp)
                        Text(
                            text = if (isTamil) "அறக்கட்டளையின் முக்கிய நோக்கங்கள்" else "Core Objectives",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    val objectivesEn = listOf(
                        "Connect willing volunteers with localized community needs across Cauvery Delta.",
                        "Establish village-level disaster first-responder brigades for cyclones and floods.",
                        "Eradicate drinking water salinity and bacterial contamination in rural habitations.",
                        "Desilt historical irrigation tanks, oorani ponds, and Cauvery feeder channels.",
                        "Expand coastal mangrove bio-shields along Bay of Bengal shorelines.",
                        "Promote environmental literacy and climate resilience in rural government schools."
                    )
                    val objectivesTa = listOf(
                        "டெல்டா கிராமங்களின் தேவைகளுக்கு ஏற்ப உள்ளூர் தன்னார்வலர்களை ஒருங்கிணைத்தல்.",
                        "புயல், பெருமழையை எதிர்கொள்ள கிராம அளவில் இளைஞர் மீட்புக் குழுக்களை உருவாக்குதல்.",
                        "கிராமப்புற குடிநீரில் உப்புத்தன்மை மற்றும் பாக்டீரியா தொற்றுகளை களைந்து பாதுகாப்பான நீர் வழங்குதல்.",
                        "பழமையான ஊரணிகள், ஏரிகள் மற்றும் காவேரி பாசன வாய்க்கால்களை தூர்வாருதல்.",
                        "கடல் சீற்றத்தை எதிர்கொள்ள கடலோர அலையாத்திக் காடுகளை நடுதல் & பாதுகாத்தல்.",
                        "அரசுப் பள்ளி மாணவர்களிடம் சூழலியல் மற்றும் பேரிடர் பாதுகாப்பு விழிப்புணர்வு ஏற்படுத்துதல்."
                    )

                    val list = if (isTamil) objectivesTa else objectivesEn
                    list.forEachIndexed { index, item ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${index + 1}. ",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Community-Based Approach
        item {
            AboutCard(
                icon = "🤝",
                title = if (isTamil) "சமூக வழி அணுகுமுறை" else "Community-Based Approach",
                content = if (isTamil) "வெளி அமைப்புகளின் உதவிகளை மட்டுமே எதிர்பார்க்காமல், உள்ளூர் மக்களும் இளைஞர்களுமே தங்கள் ஊரின் நீர்நிலைகளை காக்கும் காவலர்களாகவும், புயல் காலத்து மீட்பாளர்களாகவும் செயல்படும் தற்சார்பு மாதிரியை நாங்கள் பின்பற்றுகிறோம். கிராம சபை, சுய உதவிக்குழுக்கள் மற்றும் பள்ளி மாணவர்களை இதில் முன்னிறுத்துகிறோம்."
                else "We believe true resilience is born from within the village. By training local youth, partnering with women self-help groups, and engaging farmers in Gram Sabha dialogues, Delta Volunteers Trust turns beneficiaries into active caretakers of their own land and water."
            )
        }

        // Volunteer Network Across Delta Districts
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
                        Text(text = "🗺️", fontSize = 24.sp)
                        Text(
                            text = if (isTamil) "தன்னார்வலர் பரவல் மண்டலம்" else "Delta Volunteer Network",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isTamil) "எங்கள் தன்னார்வலர் குழுக்கள் பின்வரும் டெல்டா மாவட்டங்களில் செயல்படுகின்றன:"
                        else "Active coordination hubs and rapid response units across:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    AppDataStore.DELTA_DISTRICTS.take(8).forEach { district ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 3.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = district,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // Free & Non-Profit Commitment
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = "🕊️", fontSize = 28.sp)
                    Column {
                        Text(
                            text = if (isTamil) "100% இலவச பொதுநல தளம்" else "Free & Non-Profit Public Service",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Text(
                            text = if (isTamil) "தன்னார்வலர் சேர்க்கை, சமூகப் புகார் பதிவு மற்றும் பேரிடர் வழிகாட்டிகள் அனைவருக்கும் எப்போதும் முற்றிலும் இலவசம். கட்டணம் ஏதுமில்லை."
                            else "No subscription or fee is required. Free volunteer registration, community issue reporting and open disaster guides for all citizens.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }
            }
        }

        // Registered Office Details
        item {
            OfficialContactCard(isTamil = isTamil)
        }
    }
}

@Composable
fun AboutCard(
    icon: String,
    title: String,
    content: String
) {
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
                Text(text = icon, fontSize = 24.sp)
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 22.sp
            )
        }
    }
}
