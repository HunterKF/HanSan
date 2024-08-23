package com.jaegerapps.hansan.common.util

import java.util.Locale

actual val myLang:String?
    get() = Locale.getDefault().language

            //https://stackoverflow.com/questions/65908933/how-can-i-get-locale-and-language-from-the-device-with-kotlinmultiplatform