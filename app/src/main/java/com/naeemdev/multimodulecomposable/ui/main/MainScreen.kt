package com.naeemdev.multimodulecomposable.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.naeemdev.common.items.NavigationItem
import com.naeemdev.common.theme.SpeechRed
import com.naeemdev.multimodulecomposable.R
import com.naeemdev.nowplaying.NowPlayingScreen
import com.naeemdev.popular.PopularScreen
import com.naeemdev.upcoming.UpcomingScreen

@Composable
fun MainScreen() {
    SettingUpBottomNavigationBarAndCollapsing()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingUpBottomNavigationBarAndCollapsing() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(modifier = Modifier,
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier
                    .height(56.dp),
                navController
            )
        }
    ) {
        MainScreenNavigationConfigurations(navController)
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = NavigationItem.NowPlaying.route) {
        composable(NavigationItem.NowPlaying.route) {
            NowPlayingScreen()
        }
        composable(NavigationItem.Popular.route) {
            PopularScreen()
        }
        composable(NavigationItem.Upcoming.route) {
            UpcomingScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier, navController: NavController) {
    val bottomNavigationItems = listOf(
        NavigationItem.NowPlaying,
        NavigationItem.Popular,
        NavigationItem.Upcoming
    )
    BottomNavigation(
        modifier
            .graphicsLayer {
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
                clip = true
            },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.route) },
                label = { Text(text = item.route) },
                selectedContentColor = SpeechRed,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true

                    }
                }
            )
        }
    }
}
