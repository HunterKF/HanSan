package com.jaegerapps.hansan.screens.practice.domain.hangul

import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.CHO
import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.FIRST_HANGUL_UNICODE
import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.JONG
import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.JUNG
import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.NUM_JONG
import com.jaegerapps.hansan.screens.practice.domain.hangul.Constants.NUM_JUNG

/*https://github.com/hanju-jo/hangul-toolkit/blob/master/src/main/kotlin/hangul/toolkit/HangulToolkit.kt
* This HangulToolkit was created by a GitHub user named Hanju*/
class HangulToolkit {
    fun compose(string: String): Boolean {
        return string.isHangul()
    }
}