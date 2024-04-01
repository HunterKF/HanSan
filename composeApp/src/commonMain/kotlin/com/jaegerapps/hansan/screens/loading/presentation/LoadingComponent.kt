package com.jaegerapps.hansan.screens.loading.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.Knower.e
import com.jaegerapps.hansan.screens.loading.domain.repo.LoadingRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadingComponent(
    componentContext: ComponentContext,
    repo: LoadingRepo,
    private val onStart: (List<WordModel>, List<TenseModel>) -> Unit,
) : ComponentContext by componentContext {

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            repo.updateDailyGoals()
            val result = repo.getDefaultData()
            if (result.isFailure) {
                Knower.e("LoadingComponent", "This error shouldn't be possible?")
            } else if (result.isSuccess) {
                val data = result.getOrNull()
                if (data != null) {
                    Knower.d("LoadingComponent", "Leaving LoadingComponent")
                    withContext(Dispatchers.Main) {
                        onStart(data.words, data.tenses)
                    }
                }

            }

        }
    }

}