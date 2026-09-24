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
import com.example.model.ProjectItem
import com.example.ui.DeltaViewModel

@Composable
fun ProjectsScreen(
    viewModel: DeltaViewModel,
    isTamil: Boolean
) {
    val selectedProject by viewModel.selectedProject.collectAsState()

    var statusFilter by remember { mutableStateOf("All") } // "All", "Ongoing", "Completed"

    val filteredProjects = remember(statusFilter) {
        if (statusFilter == "All") {
            AppDataStore.PROJECTS
        } else {
            AppDataStore.PROJECTS.filter { it.status == statusFilter }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .testTag("projects_screen"),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
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
                            Text(text = "📋", fontSize = 32.sp)
                            Column {
                                Text(
                                    text = if (isTamil) "களத் திட்டங்கள் & தாக்கம்" else "Field Projects & Impact",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Text(
                                    text = if (isTamil) "டெல்டாவில் நடைபெறும் மக்கள் நலப் பணிகள்" else "Ongoing and completed initiatives",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTamil) "நீர்நிலைகள் தூர்வாருதல், அலையாத்திக் காடுகள் வளர்ப்பு, பள்ளி மாணவர் குடிநீர் திட்டங்கள் மற்றும் பேரிடர் மீட்பு முகாம்கள்."
                            else "Transparent progress tracking across Cauvery Delta districts with tangible beneficiary impacts.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Filter Chips
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("All", "Ongoing", "Completed").forEach { status ->
                        val isSelected = statusFilter == status
                        val label = when (status) {
                            "All" -> if (isTamil) "அனைத்தும் (${AppDataStore.PROJECTS.size})" else "All Projects (${AppDataStore.PROJECTS.size})"
                            "Ongoing" -> if (isTamil) "நடைபெறுபவை" else "Ongoing"
                            "Completed" -> if (isTamil) "நிறைவடைந்தவை" else "Completed"
                            else -> status
                        }
                        FilterChip(
                            selected = isSelected,
                            onClick = { statusFilter = status },
                            label = { Text(label) }
                        )
                    }
                }
            }

            // Projects List
            items(filteredProjects) { project ->
                ProjectCardItem(
                    project = project,
                    isTamil = isTamil,
                    onClick = { viewModel.selectProject(project) }
                )
            }
        }

        // Project Detail Dialog
        selectedProject?.let { project ->
            ProjectDetailDialog(
                project = project,
                isTamil = isTamil,
                onDismiss = { viewModel.selectProject(null) }
            )
        }
    }
}

@Composable
fun ProjectCardItem(
    project: ProjectItem,
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
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Category & Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = project.categoryEmoji, fontSize = 24.sp)
                    Text(
                        text = project.category,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Status chip
                val statusBg = if (project.status == "Ongoing") Color(0xFFE0F2FE) else Color(0xFFDCFCE7)
                val statusFg = if (project.status == "Ongoing") Color(0xFF0369A1) else Color(0xFF15803D)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = statusBg
                ) {
                    Text(
                        text = if (project.status == "Ongoing") {
                            if (isTamil) "நடைபெறுகிறது" else "Ongoing"
                        } else {
                            if (isTamil) "நிறைவடைந்தது" else "Completed"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = statusFg,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (isTamil) project.titleTa else project.titleEn,
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
                    text = "${project.location} • ${project.district}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isTamil) project.objectiveTa else project.objectiveEn,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Impact badge
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🌟 ", fontSize = 14.sp)
                    Text(
                        text = if (isTamil) "தாக்கம்: ${project.impactTa}" else "Impact: ${project.impactEn}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun ProjectDetailDialog(
    project: ProjectItem,
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = project.categoryEmoji, fontSize = 28.sp)
                        Column {
                            Text(
                                text = if (isTamil) project.titleTa else project.titleEn,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${project.location}, ${project.district}",
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

                Text(
                    text = if (isTamil) "திட்ட நோக்கம்:" else "Project Objective:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Text(
                    text = if (isTamil) project.objectiveTa else project.objectiveEn,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = if (isTamil) "முக்கிய செயல்பாடுகள்:" else "Field Activities:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Text(
                    text = if (isTamil) project.activitiesTa.joinToString("\n• ", prefix = "• ") else project.activitiesEn.joinToString("\n• ", prefix = "• "),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = if (isTamil) "பயனாளிகள் & தாக்கம்:" else "Beneficiaries & Impact:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Text(
                    text = if (isTamil) "${project.beneficiariesTa}\n${project.impactTa}" else "${project.beneficiariesEn}\n${project.impactEn}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isTamil) "மூடுக" else "Close")
                }
            }
        }
    }
}
