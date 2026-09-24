package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppDataStore

val DeltaBrandBlue = Color(0xFF0284C7)
val DeltaBrandYellow = Color(0xFFFFE600)
val DeltaTamilRed = Color(0xFFB91C1C)
val PriyamGreen = Color(0xFF15803D)
val MintBannerBg = Color(0xFFDCFCE7)

@Composable
fun DeltaOfficialLogoCard(
    modifier: Modifier = Modifier,
    isTamil: Boolean = false,
    showRegistration: Boolean = true
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Main Blue Logo Block exactly matching the official emblem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(DeltaBrandBlue)
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left graphic: Hands cradling globe & heart
                    Box(
                        modifier = Modifier.size(68.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Background soft glow
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f))
                        )
                        // Globe and heart icon combination
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text("❤️", fontSize = 16.sp)
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("🌍", fontSize = 24.sp)
                            }
                            Text("👐", fontSize = 26.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // Right text: DELTA Volunteers
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "DELTA",
                            color = DeltaBrandYellow,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                        Text(
                            text = "Volunteers",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }

            // Tamil Motto in Crimson Red underneath
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AppDataStore.MOTTO_TA, // "ஒப்புரவறிதல்"
                    color = DeltaTamilRed,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            }

            Text(
                text = if (isTamil) "டெல்டா தன்னார்வலர்கள் அறக்கட்டளை • மக்கள் இயக்கம்"
                else "Delta Volunteers Trust • People's Initiative for a Better Delta",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            if (showRegistration) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified NGO",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${AppDataStore.REGD_NO}  |  NGO DARPAN ID: ${AppDataStore.NGO_DARPAN_ID}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun JointInitiativeBannerCard(
    modifier: Modifier = Modifier,
    isTamil: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dual/Tri Trust Logos Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Priyam Trust Column
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("🌱", fontSize = 18.sp)
                        Text(
                            text = "PRIYAM TRUST",
                            fontWeight = FontWeight.Black,
                            fontSize = 11.sp,
                            color = PriyamGreen
                        )
                    }
                    Text(
                        text = "RegNo: 72/2008",
                        fontSize = 9.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Vertical Divider
                Box(
                    modifier = Modifier
                        .height(36.dp)
                        .width(1.dp)
                        .background(Color.LightGray)
                )

                // Delta Volunteers Column
                Column(
                    modifier = Modifier.weight(1.3f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("🌊", fontSize = 18.sp)
                        Text(
                            text = "DELTA VOLUNTEERS",
                            fontWeight = FontWeight.Black,
                            fontSize = 11.sp,
                            color = DeltaBrandBlue
                        )
                    }
                    Text(
                        text = "Regd. No. 19/2024",
                        fontSize = 9.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Vertical Divider
                Box(
                    modifier = Modifier
                        .height(36.dp)
                        .width(1.dp)
                        .background(Color.LightGray)
                )

                // NDSO Column
                Column(
                    modifier = Modifier.weight(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("🤝", fontSize = 18.sp)
                        Text(
                            text = "NDSO",
                            fontWeight = FontWeight.Black,
                            fontSize = 12.sp,
                            color = Color(0xFFC2410C)
                        )
                    }
                    Text(
                        text = "Partner NGO",
                        fontSize = 9.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Joint Initiative Highlight Ribbon
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MintBannerBg)
                    .border(1.dp, Color(0xFF86EFAC), RoundedCornerShape(8.dp))
                    .padding(vertical = 8.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (isTamil) AppDataStore.JOINT_INITIATIVE_TITLE_TA else AppDataStore.JOINT_INITIATIVE_TITLE_EN,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF14532D),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = if (isTamil) AppDataStore.JOINT_INITIATIVE_SUBTITLE_TA else AppDataStore.JOINT_INITIATIVE_SUBTITLE_EN,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = Color(0xFF166534),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun OfficialContactCard(
    modifier: Modifier = Modifier,
    isTamil: Boolean = false
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Head Office",
                    tint = MaterialTheme.colorScheme.primary
                )
                Column {
                    Text(
                        text = if (isTamil) "தலைமையகம் & பதிவு அலுவலகம்" else "Registered Head Office",
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
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Action Quick Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${AppDataStore.PHONE_PRIMARY_RAW}"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isTamil) "அழைக்க" else "Call",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/${AppDataStore.WHATSAPP_NUMBER}"))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${AppDataStore.PHONE_PRIMARY_RAW}"))
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF16A34A)),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "WhatsApp",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${AppDataStore.EMAIL}"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isTamil) "மின்னஞ்சல்" else "Email",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ThirukkuralQuoteCard(
    modifier: Modifier = Modifier,
    isTamil: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFEF9C3) // Soft golden parchment tone
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE047)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("📜", fontSize = 20.sp)
                Text(
                    text = "திருக்குறள் நெறி • THIRUKKURAL GUIDANCE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = Color(0xFF854D0E)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${AppDataStore.THIRUKKURAL_LINE1}\n${AppDataStore.THIRUKKURAL_LINE2}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF713F12),
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = AppDataStore.THIRUKKURAL_REF,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DeltaTamilRed,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isTamil) AppDataStore.THIRUKKURAL_MEANING_TA else AppDataStore.THIRUKKURAL_MEANING_EN,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = Color(0xFF78350F),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun DonationBankCard(
    modifier: Modifier = Modifier,
    isTamil: Boolean = false
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("🏦", fontSize = 26.sp)
                    Column {
                        Text(
                            text = if (isTamil) "வங்கி கணக்கு விவரங்கள்" else "Trust Bank Account Details",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isTamil) "நன்கொடை & CSR திட்டங்களுக்காக" else "For Donations & CSR Partnerships",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // 80G Tax Exemption Chip
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFDCFCE7),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF86EFAC))
                ) {
                    Text(
                        text = "80G (5) TAX EXEMPT",
                        color = Color(0xFF166534),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Details Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    BankDetailRow("Account Name", "DELTA VOLUNTEERS TRUST")
                    BankDetailRow("Bank", AppDataStore.BANK_NAME)
                    BankDetailRow("Branch", AppDataStore.BANK_BRANCH)
                    BankDetailRow("Account Type", AppDataStore.BANK_ACCOUNT_TYPE)
                    BankDetailRow("Account No.", AppDataStore.BANK_ACCOUNT_NO, isHighlighted = true)
                    BankDetailRow("IFSC Code", AppDataStore.BANK_IFSC_CODE, isHighlighted = true)
                    BankDetailRow("MICR Code", AppDataStore.BANK_MICR_CODE)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Income Tax Exemption: ${AppDataStore.IT_EXEMPTION} • Donations eligible for tax benefits.",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Copy Bank Details Button
            OutlinedButton(
                onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? android.content.ClipboardManager
                    val clip = android.content.ClipData.newPlainText(
                        "Delta Volunteers Trust Bank Details",
                        "Bank: ${AppDataStore.BANK_NAME}\nBranch: ${AppDataStore.BANK_BRANCH}\nA/C Name: ${AppDataStore.TRUST_NAME}\nA/C No: ${AppDataStore.BANK_ACCOUNT_NO}\nIFSC: ${AppDataStore.BANK_IFSC_CODE}\nMICR: ${AppDataStore.BANK_MICR_CODE}"
                    )
                    clipboard?.setPrimaryClip(clip)
                    android.widget.Toast.makeText(context, "Bank details copied to clipboard!", android.widget.Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = if (isTamil) "📋 வங்கி விவரங்களை நகலெடுக்க (Copy)" else "📋 Copy Bank Account Details",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun BankDetailRow(
    label: String,
    value: String,
    isHighlighted: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = if (isHighlighted) 13.sp else 12.sp,
            fontWeight = if (isHighlighted) FontWeight.ExtraBold else FontWeight.Bold,
            color = if (isHighlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun LeaderProfileCard(
    modifier: Modifier = Modifier,
    isTamil: Boolean = false
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(54.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("👨‍💼", fontSize = 28.sp)
                    }
                }
                Column {
                    Text(
                        text = AppDataStore.LEADER_NAME,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = AppDataStore.LEADER_ROLE,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${AppDataStore.LEADER_TITLE_DELTA}\n${AppDataStore.LEADER_TITLE_PRIYAM}",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Direct Call & Email Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${AppDataStore.LEADER_CELL}"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Call: ${AppDataStore.LEADER_CELL}", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                FilledTonalButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${AppDataStore.LEADER_EMAIL}"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Email Leader", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun StatutoryCredentialsCard(
    modifier: Modifier = Modifier,
    isTamil: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("🏛️", fontSize = 24.sp)
                Text(
                    text = if (isTamil) "சட்டரீதியான பதிவுகள் & அரசு அங்கீகாரங்கள்" else "Statutory Registrations & Approvals",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            val items = listOf(
                Pair("Trust Registration", AppDataStore.REGD_NO),
                Pair("Income Tax Exemption", AppDataStore.IT_EXEMPTION),
                Pair("NITI Aayog NGO DARPAN ID", AppDataStore.NGO_DARPAN_ID),
                Pair("FSSAI Registration No.", AppDataStore.FSSAI_REG_NO),
                Pair("Affiliated Partner 1", "${AppDataStore.PARTNER_TRUST_NAME} (${AppDataStore.PARTNER_TRUST_REGD})"),
                Pair("Affiliated Partner 2", AppDataStore.PARTNER_NDSO_NAME),
                Pair("Service Track Record", "20 Years of Community & Disaster Service")
            )

            items.forEach { (label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "• $label",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1.1f)
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1.3f)
                    )
                }
            }
        }
    }
}
