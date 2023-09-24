package ui.screens.shared

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import ui.theme.AppTheme
import ui.utils.manipulate

enum class ButtonState {
    IDLE, PRESSED,
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var buttonState by remember { mutableStateOf(ButtonState.IDLE) }
    val darkenFactor by animateFloatAsState(if (buttonState == ButtonState.PRESSED) .8f else 1.0f)

    Box(
        modifier = modifier
            .background(
                color = AppTheme.colors.component.manipulate(darkenFactor),
                shape = RoundedCornerShape(percent = 12),
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .pointerInput(buttonState) {
                awaitPointerEventScope {
                    buttonState = if (buttonState == ButtonState.PRESSED) {
                        waitForUpOrCancellation()
                        ButtonState.IDLE
                    } else {
                        awaitFirstDown(false)
                        ButtonState.PRESSED
                    }
                }
            }
            .padding(all = AppTheme.dimensions.mediumPadding),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            ProvideTextStyle(
                value = AppTheme.typography.body,
                content = content
            )
        }
    }
}
