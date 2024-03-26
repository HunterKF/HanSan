package com.jaegerapps.hansan.screens.practice.domain.hangul

import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.FIRST_HANGUL_UNICODE
import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.JAMO
import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.LAST_HANGUL_UNICODE
/*https://github.com/hanju-jo/hangul-toolkit/blob/master/src/main/kotlin/hangul/toolkit/HangulToolkit.kt
* This HangulToolkit was created by a GitHub user named Hanju*/
private fun Char.isJamo(): Boolean {
    return this in JAMO
}
fun Char.isHangul(): Boolean {
    if (this.isJamo()) {
        return true
    }
    val code = this.toInt()
    return code in FIRST_HANGUL_UNICODE..LAST_HANGUL_UNICODE
}

fun String.isHangul(): Boolean {
    return this.toCharArray()
            .all { it.isHangul() }
}