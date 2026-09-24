package com.example.model

data class FocusArea(
    val id: String,
    val iconEmoji: String,
    val titleEn: String,
    val titleTa: String,
    val taglineEn: String,
    val taglineTa: String,
    val descriptionEn: String,
    val descriptionTa: String,
    val keyInitiativesEn: List<String>,
    val keyInitiativesTa: List<String>,
    val impactTargetEn: String,
    val impactTargetTa: String
)

data class DisasterGuide(
    val id: String,
    val titleEn: String,
    val titleTa: String,
    val iconEmoji: String,
    val summaryEn: String,
    val summaryTa: String,
    val beforeActionEn: List<String>,
    val beforeActionTa: List<String>,
    val duringActionEn: List<String>,
    val duringActionTa: List<String>,
    val afterActionEn: List<String>,
    val afterActionTa: List<String>,
    val emergencyTipsEn: String,
    val emergencyTipsTa: String
) {
    val subtitleEn: String get() = summaryEn
    val subtitleTa: String get() = summaryTa
    val dosEn: List<String> get() = beforeActionEn + duringActionEn
    val dosTa: List<String> get() = beforeActionTa + duringActionTa
    val dontsEn: List<String> get() = afterActionEn
    val dontsTa: List<String> get() = afterActionTa
}

data class EmergencyKitItem(
    val id: String,
    val nameEn: String,
    val nameTa: String,
    val descriptionEn: String = "",
    val descriptionTa: String = "",
    val iconEmoji: String = "🎒",
    val isMandatory: Boolean = true
) {
    val importanceEn: String get() = descriptionEn
    val importanceTa: String get() = descriptionTa.ifEmpty { descriptionEn }
}

data class WaterTopic(
    val id: String,
    val titleEn: String,
    val titleTa: String,
    val iconEmoji: String,
    val summaryEn: String,
    val summaryTa: String,
    val keyPointsEn: List<String>,
    val keyPointsTa: List<String>,
    val practicalTipEn: String,
    val practicalTipTa: String
) {
    val practicalTipsEn: List<String> get() = keyPointsEn + practicalTipEn
    val practicalTipsTa: List<String> get() = keyPointsTa + practicalTipTa
}

data class ProjectItem(
    val id: String,
    val titleEn: String,
    val titleTa: String,
    val location: String,
    val district: String,
    val status: String, // "Ongoing" or "Completed"
    val objectiveEn: String,
    val objectiveTa: String,
    val activitiesEn: List<String>,
    val activitiesTa: List<String>,
    val beneficiaries: String,
    val impactMetric: String,
    val iconEmoji: String,
    val category: String = "Water & Ecology"
) {
    val categoryEmoji: String get() = iconEmoji
    val activitiesStringEn: String get() = activitiesEn.joinToString("; ")
    val activitiesStringTa: String get() = activitiesTa.joinToString("; ")
    val impactEn: String get() = impactMetric
    val impactTa: String get() = impactMetric
    val beneficiariesEn: String get() = beneficiaries
    val beneficiariesTa: String get() = beneficiaries
}

data class EventItem(
    val id: String,
    val titleEn: String,
    val titleTa: String,
    val category: String,
    val dateStr: String,
    val timeStr: String,
    val location: String,
    val district: String,
    val descriptionEn: String,
    val descriptionTa: String,
    val targetAudience: String,
    val iconEmoji: String
) {
    val venue: String get() = location
}

data class GalleryItem(
    val id: String,
    val titleEn: String,
    val titleTa: String,
    val category: String,
    val location: String,
    val descriptionEn: String,
    val descriptionTa: String,
    val iconEmoji: String,
    val statNumber: String,
    val dateStr: String = "2026"
) {
    val captionEn: String get() = descriptionEn
    val captionTa: String get() = descriptionTa
}

data class StatCard(
    val number: String,
    val labelEn: String,
    val labelTa: String,
    val iconEmoji: String
)
