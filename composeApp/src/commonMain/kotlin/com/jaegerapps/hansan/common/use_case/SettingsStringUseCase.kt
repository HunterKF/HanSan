package com.jaegerapps.hansan.common.use_case

class SettingsStringUseCase {
    companion object {
        fun convertToString(list: List<Any>): String {
            return list.joinToString(",")
        }

        fun convertToList(string: String): List<String> {
            return string.split(",")
        }
    }
}