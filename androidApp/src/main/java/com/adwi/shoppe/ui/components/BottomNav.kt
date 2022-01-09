package com.adwi.shoppe.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.navigation.HomeSections
import com.adwi.shoppe.ui.theme.Dimensions

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    items: List<HomeSections>,
    currentScreen: String,
    onItemSelected: (String) -> Unit,
    shape: Shape = RoundedCornerShape(percent = 35),
    elevation: Dp = 20.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    gradientColor: Color = MaterialTheme.colorScheme.primaryContainer,
    selectedColor: Color = MaterialTheme.colorScheme.onBackground,
    notSelectedColor: Color = MaterialTheme.colorScheme.secondary,
    message: String = "",
) {
    val density = LocalDensity.current
    val transition = updateTransition(targetState = message.isNotBlank(), label = null)

    val rotation by transition.animateFloat(
        transitionSpec = { tween(4000) }, label = ""
    ) {
        println(it)
        if (it) .5f else 0f
    }

//    val background by transition.animateColor { state ->
//        if (state == EnterExitState.Visible) Color.Blue else Color.Gray
//    }

    val horizontalGradientBrush = Brush.linearGradient(
        colors = listOf(gradientColor, backgroundColor)
    )

    Card(
        shape = shape,
        elevation = elevation,
        backgroundColor = Color.Transparent,
        modifier = modifier
            .height(Dimensions.BottomBar.BottomNavHeight),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .background(horizontalGradientBrush)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .layoutId("row")
                    .fillMaxSize()
            ) {
                items.forEach { item ->
                    val contentColor by animateColorAsState(
                        targetValue = if (currentScreen == item.route) selectedColor else notSelectedColor.copy(
                            alpha = .5f
                        ),
                        animationSpec = tween(500)
                    )
                    AnimatedVisibility(
                        visible = message.isEmpty(),
                        enter = slideInVertically() + expandVertically() + fadeIn(),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        CustomBottomNavigationItem(
                            item = item,
                            onClick = { onItemSelected(item.route) },
                            contentColor = contentColor,
                            modifier = Modifier
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = message.isNotEmpty(),
                enter = slideInVertically() + expandVertically() + fadeIn(),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .layoutId("snack")
                        .fillMaxSize()
                ) {
                    Text(text = "snackbarMessage")
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigationItem(
    modifier: Modifier = Modifier,
    item: HomeSections,
    onClick: () -> Unit,
    contentColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        IconButton(
            onClick = onClick, modifier = Modifier
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = contentColor,
                modifier = modifier.padding(12.dp)
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
@Preview
fun Prev1() {
    CustomBottomNavigation(
        currentScreen = HomeSections.DASHBOARD.route,
        items = listOf(
            HomeSections.DASHBOARD,
            HomeSections.MANAGER,
            HomeSections.PLANNER,
            HomeSections.SETTINGS
        ),
        onItemSelected = {},
    )
}