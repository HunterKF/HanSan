package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.screens.practice.domain.models.Level

class LevelUseCase {
    companion object {
        fun updateLevelUp(level: Level): Level {
            return when (level) {
                Level.LEVEL_ONE -> Level.LEVEL_TWO
                Level.LEVEL_TWO ->  Level.LEVEL_THREE
                Level.LEVEL_THREE -> Level.LEVEL_FOUR
                Level.LEVEL_FOUR -> Level.LEVEL_FIVE
                Level.LEVEL_FIVE -> Level.LEVEL_SIX
                Level.LEVEL_SIX -> Level.LEVEL_SEVEN
                Level.LEVEL_SEVEN -> Level.LEVEL_EIGHT
                Level.LEVEL_EIGHT -> Level.LEVEL_EIGHT //Max level reached
            }
        }
        fun updateLevelDown(level: Level): Level {
            return when (level) {
                Level.LEVEL_ONE -> Level.LEVEL_ONE // Min level reached
                Level.LEVEL_TWO -> Level.LEVEL_ONE
                Level.LEVEL_THREE -> Level.LEVEL_TWO
                Level.LEVEL_FOUR -> Level.LEVEL_THREE
                Level.LEVEL_FIVE -> Level.LEVEL_FOUR
                Level.LEVEL_SIX -> Level.LEVEL_FIVE
                Level.LEVEL_SEVEN -> Level.LEVEL_SIX
                Level.LEVEL_EIGHT -> Level.LEVEL_SEVEN
            }
        }
    }
}