package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppScreen
import com.example.ui.DeltaViewModel
import com.example.ui.components.DeltaBottomNavigation
import com.example.ui.components.DeltaDrawerContent
import com.example.ui.components.DeltaTopBar
import com.example.ui.components.SosDialog
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                DeltaVolunteersApp()
            }
        }
    }
}

@Composable
fun DeltaVolunteersApp(
    viewModel: DeltaViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val isTamil by viewModel.isTamil.collectAsState()
    val newsList by viewModel.newsList.collectAsState()
    val showSos by viewModel.showSosDialog.collectAsState()
    val selectedFocusArea by viewModel.selectedFocusArea.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Handle back button: close drawer first, or return to Home if on other screen
    BackHandler(enabled = drawerState.isOpen || currentScreen != AppScreen.HOME) {
        if (drawerState.isOpen) {
            coroutineScope.launch { drawerState.close() }
        } else {
            viewModel.navigateTo(AppScreen.HOME)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DeltaDrawerContent(
                currentScreen = currentScreen,
                isTamil = isTamil,
                onNavigate = { screen ->
                    viewModel.navigateTo(screen)
                },
                onCloseDrawer = {
                    coroutineScope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .testTag("main_scaffold"),
            topBar = {
                DeltaTopBar(
                    isTamil = isTamil,
                    onToggleLanguage = { viewModel.toggleLanguage() },
                    onOpenSos = { viewModel.openSosDialog() },
                    onOpenMenu = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )
            },
            bottomBar = {
                DeltaBottomNavigation(
                    currentScreen = currentScreen,
                    isTamil = isTamil,
                    onNavigate = { screen -> viewModel.navigateTo(screen) }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentScreen) {
                    AppScreen.HOME -> HomeScreen(
                        isTamil = isTamil,
                        newsList = newsList,
                        onNavigate = { screen -> viewModel.navigateTo(screen) },
                        onSelectFocusArea = { area ->
                            viewModel.selectFocusArea(area)
                            viewModel.navigateTo(AppScreen.FOCUS_AREAS)
                        }
                    )
                    AppScreen.ABOUT -> AboutScreen(
                        isTamil = isTamil
                    )
                    AppScreen.FOCUS_AREAS -> FocusAreasScreen(
                        isTamil = isTamil,
                        onNavigate = { screen -> viewModel.navigateTo(screen) },
                        selectedArea = selectedFocusArea,
                        onSelectArea = { area -> viewModel.selectFocusArea(area) }
                    )
                    AppScreen.VOLUNTEER -> VolunteerScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                    AppScreen.REPORT_NEED -> ReportNeedScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                    AppScreen.BENEFICIARY_FORM -> BeneficiaryFormScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                    AppScreen.DISASTER -> DisasterScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                    AppScreen.WATER -> WaterScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                    AppScreen.PROJECTS -> ProjectsScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                    AppScreen.EVENTS -> EventsScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                    AppScreen.GALLERY -> GalleryScreen(
                        isTamil = isTamil
                    )
                    AppScreen.NEWS -> NewsScreen(
                        viewModel = viewModel,
                        isTamil = isTamil,
                        onNavigate = { screen -> viewModel.navigateTo(screen) }
                    )
                    AppScreen.CONTACT -> ContactScreen(
                        isTamil = isTamil
                    )
                    AppScreen.ADMIN -> AdminScreen(
                        viewModel = viewModel,
                        isTamil = isTamil
                    )
                }
            }
        }
    }

    // Emergency SOS Modal
    if (showSos) {
        SosDialog(
            isTamil = isTamil,
            onDismiss = { viewModel.closeSosDialog() }
        )
    }
}
