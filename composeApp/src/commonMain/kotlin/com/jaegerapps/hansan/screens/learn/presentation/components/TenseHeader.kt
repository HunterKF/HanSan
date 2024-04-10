package com.jaegerapps.hansan.screens.learn.presentation.components

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.tense_header_future
import hansan.composeapp.generated.resources.tense_header_past
import hansan.composeapp.generated.resources.tense_header_present
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

enum class TenseHeader {
    PRESENT,
    PAST,
    FUTURE
}

@OptIn(ExperimentalResourceApi::class)
fun getStringFromHeader(tenseHeader: TenseHeader): StringResource {
    return when (tenseHeader) {
        TenseHeader.PRESENT -> Res.string.tense_header_present
        TenseHeader.PAST -> Res.string.tense_header_past
        TenseHeader.FUTURE -> Res.string.tense_header_future
    }
}