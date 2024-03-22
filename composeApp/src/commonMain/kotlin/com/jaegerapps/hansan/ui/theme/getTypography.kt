package com.jaegerapps.hansan.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.nanumgoth_regular
import hansan.composeapp.generated.resources.nanumgothic_bold
import hansan.composeapp.generated.resources.poppings_light
import hansan.composeapp.generated.resources.poppings_regular
import hansan.composeapp.generated.resources.poppins_extra_light
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalResourceApi::class)
@Composable
fun getTypography(): Typography {
/*
    val nanumGothicRegular = FontFamily(
        Font(Res.font.nanumgoth_regular, FontWeight.Normal, FontStyle.Normal)
    )
    val nanumGothicBold = FontFamily(
        Font(Res.font.nanumgothic_bold, FontWeight.Bold, FontStyle.Normal)
    )*/
    val poppingsRegular = FontFamily(
        Font(Res.font.poppings_regular, FontWeight.Normal, FontStyle.Normal)
    )
    val poppingsLight = FontFamily(
        Font(Res.font.poppins_extra_light, FontWeight.Normal, FontStyle.Normal)
    )
    return Typography(
        displayLarge = TextStyle(
            fontFamily = poppingsRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 52.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = poppingsRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = poppingsRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = poppingsRegular,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = poppingsLight,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = poppingsLight,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
        )
    )
}