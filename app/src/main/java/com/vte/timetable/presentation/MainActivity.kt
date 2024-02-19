/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.vte.timetable.presentation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyColumnDefaults
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.rememberScalingLazyListState
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable(route = "home",
                    enterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    exitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
                    popEnterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    popExitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) }
                ) {
                    HomeScreen(navController)
                }
                composable(route = "mon",
                    enterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    exitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
                    popEnterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    popExitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) }

                ) {
                    DayScreen(navController, ListMonday)
                }
                composable(route = "tue",
                    enterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    exitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
                    popEnterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    popExitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) }
                ) {
                    DayScreen(navController, ListTuesday)
                }
                composable(route = "wed",
                    enterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    exitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
                    popEnterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    popExitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) }
                ) {
                    DayScreen(navController, ListWednesday)
                }
                composable(route = "thu",
                    enterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    exitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
                    popEnterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    popExitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) }
                ) {
                    DayScreen(navController, ListThursday)
                }
                composable(route = "fri",
                    enterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    exitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
                    popEnterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
                    popExitTransition = { return@composable slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) }
                ) {
                    DayScreen(navController, ListFriday)
                }
            }
        }
    }
}

@Composable
fun TextForTableDay(dayText: String,){
    Text(text = dayText, fontSize = 20.sp)
}

@Composable
fun TextForTablePeriod(tableText: String, subText: String){
    Text(text = tableText, fontSize = 20.sp)
    Text(text = subText, fontSize = 16.sp)

}

@Composable
fun ListViewT(navController: NavController,scalingLazyListState: ScalingLazyListState ) {
    val focusRequester = remember{ FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    ScalingLazyColumn(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxSize()
            .onRotaryScrollEvent {
                coroutineScope.launch {
                    scalingLazyListState.scrollBy(it.verticalScrollPixels)
                    if (it.verticalScrollPixels > 0) {
                        scalingLazyListState.animateScrollToItem((scalingLazyListState.centerItemIndex) + (1 / 2))
                    } else if (it.verticalScrollPixels < 0) {
                        scalingLazyListState.animateScrollToItem((scalingLazyListState.centerItemIndex) - (1 / 2))
                    }
                }
                true
            }
            .focusRequester(focusRequester)
            .focusable(),
        state = scalingLazyListState,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        flingBehavior = ScalingLazyColumnDefaults.snapFlingBehavior(state = scalingLazyListState, snapOffset = 0.dp)
    ) {
        items(0) {
        }
        item {
            TextForTableDay("Time Table")
        }
        item {
            Card(
                onClick = {navController.navigate("mon")},
                modifier = Modifier.height(50.dp)
                // backgroundPainter = CardDefaults.cardBackgroundPainter(startBackgroundColor = Color.DarkGray)

            ){
                TextForTableDay("Monday")
            }
        }
        item {
            Card(
                onClick = {navController.navigate("tue")},
                modifier = Modifier.height(50.dp)
            ){
                TextForTableDay("Tuesday")
            }
        }
        item {
            Card(
                onClick = {navController.navigate("wed")},
                modifier = Modifier.height(50.dp)
            ){
                TextForTableDay("Wednesday")
            }
        }
        item {
            Card(
                onClick = {navController.navigate("thu")},
                modifier = Modifier.height(50.dp)
            ){
                TextForTableDay("Thursday")
            }
        }
        item {
            Card(
                onClick = {navController.navigate("fri")},
                modifier = Modifier.height(50.dp)
            ){
                TextForTableDay("Friday")
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun HomeScreen(navController: NavController){
    val scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        timeText = {TimeText()},
        vignette = {},
        positionIndicator = {PositionIndicator(scalingLazyListState = scalingLazyListState)
        }
    ) {
        ListViewT(navController = navController,scalingLazyListState)
    }
}


@Composable
fun ListViewDay(PeriodList: List<String>, scalingLazyListState: ScalingLazyListState ) {
    val focusRequester = remember{ FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    ScalingLazyColumn(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxSize()
            .onRotaryScrollEvent {
                coroutineScope.launch {
                    scalingLazyListState.scrollBy(it.verticalScrollPixels)
                    if (it.verticalScrollPixels > 0) {
                        scalingLazyListState.animateScrollToItem((scalingLazyListState.centerItemIndex) + (1 / 2))
                    } else if (it.verticalScrollPixels < 0) {
                        scalingLazyListState.animateScrollToItem((scalingLazyListState.centerItemIndex) - (1 / 2))
                    }
                }
                true
            }
            .focusRequester(focusRequester)
            .focusable(),
        state = scalingLazyListState,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        flingBehavior = ScalingLazyColumnDefaults.snapFlingBehavior(state = scalingLazyListState, snapOffset = 0.dp)
    ) {
        items(0) {
        }
        item {
            TextForTableDay(PeriodList[0])
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[1],"9.00 - 9.50")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[2],"9.50 - 10.40")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[3],"11.40 - 12.30")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[4],"11.40 - 12.30")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[5],"12.30 - 1.20")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[6],"1.20 - 2.10")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[7],"2.10 - 3.00")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[8],"3.10 - 4.00")
            }
        }
        item {
            Card(
                onClick = {},
                modifier = Modifier.height(70.dp)
            ){
                TextForTablePeriod(PeriodList[9],"4.00 - 4.50")
            }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

val ListMonday: List<String> = listOf("Monday","EEE","MFC","UI Design","ADM","Break","EOC Lab","EOC Lab","Break","Break")
val ListTuesday: List<String> = listOf("Tuesday","Java","EOC","Java Lab","Java Lab","Break","MFC","Break","DSA Lab","DSA Lab")
val ListWednesday: List<String> = listOf("Wednesday","DSA","EOC","EEE","Java","Break","MFC Lab","MFC Lab","Break","Break")
val ListThursday: List<String> = listOf("Thursday","ADM","DSA","Java","MFC","Break","UID Lab","UID Lab","Break","Break")
val ListFriday: List<String> = listOf("Friday","UI Design","Ma-Om","DSA","Break","Break","EEE Lab","EEE Lab","Break","Break")



@Composable
fun DayScreen(navController: NavController, pList: List<String>){
    val scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        timeText = {TimeText()},
        vignette = {},
        positionIndicator = {PositionIndicator(scalingLazyListState = scalingLazyListState)}
    ) {
        ListViewDay(pList,scalingLazyListState)
    }
}