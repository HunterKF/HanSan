package com.jaegerapps.hansan.screens.practice.domain.models

enum class Level {
    LEVEL_ONE,
    LEVEL_TWO,
    LEVEL_THREE,
    LEVEL_FOUR,
    LEVEL_FIVE,
    LEVEL_SIX,
    LEVEL_SEVEN,
    LEVEL_EIGHT
}

fun Level.toInt(): Int {
    return when (this) {
        Level.LEVEL_ONE -> 1
        Level.LEVEL_TWO -> 2
        Level.LEVEL_THREE -> 3
        Level.LEVEL_FOUR -> 4
        Level.LEVEL_FIVE -> 5
        Level.LEVEL_SIX -> 6
        Level.LEVEL_SEVEN -> 7
        Level.LEVEL_EIGHT -> 8
    }
}

fun Int.toLevel(): Level {
    return when (this) {
        1 -> Level.LEVEL_ONE
        2 -> Level.LEVEL_TWO
        3 -> Level.LEVEL_THREE
        4 -> Level.LEVEL_FOUR
        5 -> Level.LEVEL_FIVE
        6 -> Level.LEVEL_SIX
        7 -> Level.LEVEL_SEVEN
        8 -> Level.LEVEL_EIGHT
        else -> throw IllegalArgumentException("Invalid level number.")
    }
}