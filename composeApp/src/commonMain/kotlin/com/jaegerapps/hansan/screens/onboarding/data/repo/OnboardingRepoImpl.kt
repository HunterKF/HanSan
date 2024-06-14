package com.jaegerapps.hansan.screens.onboarding.data.repo

import com.jaegerapps.hansan.common.mappers.toVerbModel
import com.jaegerapps.hansan.common.models.VerbEntity
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.Knower.e
import com.jaegerapps.hansan.screens.onboarding.data.local.json.LocalJsonDataSource
import com.jaegerapps.hansan.screens.onboarding.data.local.room.LocalRoomDataSource
import com.jaegerapps.hansan.screens.onboarding.data.local.shared_preferences.LocalSharedPrefDataSource
import com.jaegerapps.hansan.screens.onboarding.domain.repo.OnboardingRepo
import com.jaegerapps.hansan.screens.onboarding.domain.toGrammarEntities
import com.jaegerapps.hansan.screens.onboarding.domain.toTranslationEntities
import com.jaegerapps.hansan.screens.onboarding.domain.toWordEntities

class OnboardingRepoImpl(
   private val localJsonDataSource: LocalJsonDataSource,
   private val localRoomDataSource: LocalRoomDataSource,
   private val localSharedPrefDataSource: LocalSharedPrefDataSource
): OnboardingRepo {
    override suspend fun insertWords() {
        try {
            Knower.d("insertWords", "Starting now.")

            val verbEntityList = localJsonDataSource.getWords()
            if (verbEntityList != null) {
                localRoomDataSource.addWords(list = verbEntityList.flatMap { it.toWordEntities() })
                localRoomDataSource.addTranslations(translationList = verbEntityList.flatMap { it.toTranslationEntities() })
                insertGrammarLists(verbEntityList.first())
                localSharedPrefDataSource.toggleOnboarding()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Knower.e("insertWords", "An error occurred. ${e.message}")
        }

    }

    override suspend fun completeOnboarding() {
       localSharedPrefDataSource.toggleOnboarding()
    }

    private suspend fun insertGrammarLists(entity: VerbEntity) {
        localRoomDataSource.addGrammar(entity.toGrammarEntities())
    }
}