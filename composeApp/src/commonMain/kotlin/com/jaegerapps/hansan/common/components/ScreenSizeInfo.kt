package com.jaegerapps.hansan.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

/*Gets the screen size for the keyboard*/
data class ScreenSizeInfo(val hPX: Int, val wPX: Int, val hDP: Dp, val wDP: Dp)
/*
* Solution from yuroyami
* https://github.com/JetBrains/compose-multiplatform/discussions/3225#discussioncomment-8764530*/
@Composable
expect fun getScreenSizeInfo(): ScreenSizeInfo