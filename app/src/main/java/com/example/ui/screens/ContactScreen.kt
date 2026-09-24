package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppDataStore
import com.example.ui.components.DeltaOfficialLogoCard
import com.example.ui.components.DonationBankCard
import com.example.ui.components.JointInitiativeBannerCard
import com.example.ui.components.LeaderProfileCard
import com.example.ui.components.StatutoryCredentialsCard

@Composable
fun ContactScreen(isTamil: Boolean) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("contact_screen"),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Official Logo Card
        item {
            DeltaOfficialLogoCard(isTamil = isTamil)
        }

        // Header
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
                        Text(text = "📞", fontSize = 32.sp)
                        Column {
                            Text(
                                text = if (isTamil) "தொடர்பு கொள்ள" else "Contact Delta Volunteers Trust",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = if (isTamil) "தலைமையகம்: வேதாரண்யம், நாகப்பட்டினம் மாவட்டம்" else "Headquarters: Vedaranyam, Nagapattinam District",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isTamil) "பேரிடர் கால அவசர உதவி, களப்பணி தன்னார்வலர்கள் சேர்க்கை அல்லது சமுதாயத் தேவைகளுக்கு எந்த நேரத்திலும் எங்களை அணுகலாம்."
                        else "Reach out for disaster assistance, volunteer mobilization, community problem verification, or environmental projects across Tamil Nadu Delta.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )
                }
            }
        }

        // Fast Action Buttons Row (Call, Email, WhatsApp, Map)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ContactActionTile(
                    icon = Icons.Default.Call,
                    label = if (isTamil) "அழைக்க" else "Call",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                ) {
                    dialNumber(context, AppDataStore.PHONE_PRIMARY)
                }

                ContactActionTile(
                    icon = Icons.Default.Email,
                    label = if (isTamil) "மின்னஞ்சல்" else "Email",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                ) {
                    sendEmail(context, AppDataStore.EMAIL)
                }

                ContactActionTile(
                    icon = Icons.AutoMirrored.Filled.Chat,
                    label = "WhatsApp",
                    color = Color(0xFF16A34A),
                    modifier = Modifier.weight(1f)
                ) {
                    openWhatsApp(context, AppDataStore.WHATSAPP_NUMBER)
                }

                ContactActionTile(
                    icon = Icons.Default.Map,
                    label = if (isTamil) "வரைபடம்" else "Map",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f)
                ) {
                    openMap(context, "46 West Street Vedaranyam Nagapattinam Tamil Nadu 614810")
                }
            }
        }

        // Office Address Card
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
                        Text(text = "🏢", fontSize = 24.sp)
                        Column {
                            Text(
                                text = if (isTamil) "பதிவு செய்யப்பட்ட அலுவலக முகவரி" else "Registered Head Office Address",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Vedaranyam • Nagapattinam District",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isTamil) AppDataStore.ADDRESS_TA else AppDataStore.ADDRESS,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isTamil) "தொடர்பு எண்கள்:" else "Official Phone Numbers:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilledTonalButton(
                            onClick = { dialNumber(context, AppDataStore.PHONE_PRIMARY) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(AppDataStore.PHONE_PRIMARY_RAW, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                        FilledTonalButton(
                            onClick = { dialNumber(context, AppDataStore.PHONE_SECONDARY) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(AppDataStore.PHONE_SECONDARY_RAW, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isTamil) "மின்னஞ்சல் முகவரிகள்:" else "Official Email IDs:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { sendEmail(context, AppDataStore.EMAIL_TRUST) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
                        ) {
                            Text(AppDataStore.EMAIL_TRUST, fontSize = 10.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                        }
                        OutlinedButton(
                            onClick = { sendEmail(context, AppDataStore.EMAIL_COORDINATOR) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
                        ) {
                            Text(AppDataStore.EMAIL_COORDINATOR, fontSize = 10.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isTamil) "பணி நேரம்: திங்கள் - சனி (காலை 9:00 - மாலை 6:00)" else "Office Hours: Mon - Sat (9:00 AM - 6:00 PM)",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Chief Leadership Profile
        item {
            LeaderProfileCard(isTamil = isTamil)
        }

        // Trust Bank Account Details for Donation / CSR
        item {
            DonationBankCard(isTamil = isTamil)
        }

        // Government Registration & NGO DARPAN Credentials
        item {
            StatutoryCredentialsCard(isTamil = isTamil)
        }

        // Emergency 24x7 Helpline
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isTamil) "24x7 பேரிடர் அவசர உதவி" else "24x7 Disaster Emergency Desk",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = AppDataStore.PHONE_EMERGENCY,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Button(
                        onClick = { dialNumber(context, AppDataStore.PHONE_EMERGENCY) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("SOS")
                    }
                }
            }
        }

        // District Coordinator Desks
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = if (isTamil) "மாவட்ட ஒருங்கிணைப்பாளர்கள்" else "District Coordination Units",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val desks = listOf(
                        Triple("Thanjavur & Kumbakonam", "Mr. R. Vetrivel", "+91 94431 82901"),
                        Triple("Tiruvarur & Mannargudi", "Mrs. S. Thenmozhi", "+91 94431 82902"),
                        Triple("Nagapattinam & Vedaranyam", "Mr. K. Manimaran", "+91 94431 82903"),
                        Triple("Mayiladuthurai & Sirkazhi", "Mr. T. Anbarasan", "+91 94431 82904"),
                        Triple("Cuddalore & Chidambaram", "Mr. P. Ilango", "+91 94431 82905")
                    )

                    desks.forEach { (dist, officer, num) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = dist, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                                Text(text = officer, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            IconButton(onClick = { dialNumber(context, num) }) {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = "Call $officer",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContactActionTile(
    icon: ImageVector,
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = color.copy(alpha = 0.12f),
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

private fun dialNumber(context: Context, number: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
        }
        context.startActivity(intent)
    } catch (_: Exception) {}
}

private fun sendEmail(context: Context, email: String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, "Query regarding Delta Volunteers Trust")
        }
        context.startActivity(intent)
    } catch (_: Exception) {}
}

private fun openWhatsApp(context: Context, phone: String) {
    try {
        val cleanPhone = phone.replace("+", "").replace(" ", "").replace("-", "")
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://wa.me/$cleanPhone")
        }
        context.startActivity(intent)
    } catch (_: Exception) {}
}

private fun openMap(context: Context, query: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:0,0?q=${Uri.encode(query)}")
        }
        context.startActivity(intent)
    } catch (_: Exception) {}
}
