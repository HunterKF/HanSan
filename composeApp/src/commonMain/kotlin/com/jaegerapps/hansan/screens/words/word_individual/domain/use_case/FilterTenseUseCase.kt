package com.jaegerapps.hansan.screens.words.word_individual.domain.use_case

import com.jaegerapps.hansan.common.models.Category
import com.jaegerapps.hansan.common.models.DetailedTense
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word

class FilterTenseUseCase {

    companion object {
        private val presentList = listOf(
            DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH,
            DetailedTense.PRESENT_DECLARATIVE_FORMAL_LOW,
            DetailedTense.PRESENT_DECLARATIVE_INFORMAL,
            DetailedTense.PRESENT_PROGRESSIVE_FORMAL_HIGH,
            DetailedTense.PRESENT_PROGRESSIVE_FORMAL_LOW,
            DetailedTense.PRESENT_PROGRESSIVE_INFORMAL_LOW,
        )
        private val pastList = listOf(
            DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH,
            DetailedTense.PAST_DECLARATIVE_FORMAL_LOW,
            DetailedTense.PAST_DECLARATIVE_INFORMAL,
            DetailedTense.PAST_PROGRESSIVE_FORMAL_HIGH,
            DetailedTense.PAST_PROGRESSIVE_FORMAL_LOW,
            DetailedTense.PAST_PROGRESSIVE_INFORMAL,
        )
        private val futureList = listOf(
            DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH,
            DetailedTense.FUTURE_DECLARATIVE_FORMAL_LOW,
            DetailedTense.FUTURE_DECLARATIVE_INFORMAL,
            DetailedTense.FUTURE_PROGRESSIVE_FORMAL_HIGH,
            DetailedTense.FUTURE_PROGRESSIVE_FORMAL_LOW,
            DetailedTense.FUTURE_PROGRESSIVE_INFORMAL,
        )
        private val otherList = listOf(
            DetailedTense.OTHER_IMPERATIVE_FORMAL_HIGH,
            DetailedTense.OTHER_IMPERATIVE_FORMAL_LOW,
            DetailedTense.OTHER_IMPERATIVE_INFORMAL_LOW,
            DetailedTense.OTHER_PERMISSION_FORMAL_HIGH,
            DetailedTense.OTHER_PERMISSION_FORMAL_LOW,
            DetailedTense.OTHER_PERMISSION_INFORMAL_LOW,
            DetailedTense.OTHER_PROHIBITION_FORMAL_HIGH,
            DetailedTense.OTHER_PROHIBITION_FORMAL_LOW,
            DetailedTense.OTHER_PROHIBITION_INFORMAL_LOW,
            DetailedTense.OTHER_DESIRE_FORMAL_HIGH,
            DetailedTense.OTHER_DESIRE_FORMAL_LOW,
            DetailedTense.OTHER_DESIRE_INFORMAL_LOW,
            DetailedTense.OTHER_NECESSITY_FORMAL_HIGH,
            DetailedTense.OTHER_NECESSITY_FORMAL_LOW,
            DetailedTense.OTHER_NECESSITY_INFORMAL_LOW,
            DetailedTense.OTHER_POTENTIAL_FORMAL_HIGH,
            DetailedTense.OTHER_POTENTIAL_FORMAL_LOW,
            DetailedTense.OTHER_POTENTIAL_INFORMAL_LOW,
            DetailedTense.OTHER_PROPOSITIVE_FORMAL_HIGH,
            DetailedTense.OTHER_PROPOSITIVE_FORMAL_LOW,
            DetailedTense.OTHER_PROPOSITIVE_INFORMAL_LOW,
        )
        fun filterTense(currentWord: VerbModel, category: Category): List<Word> {
            var pairList = emptyList<Word>()
            val filter = when (category) {
                Category.PRESENT -> presentList
                Category.PAST -> pastList
                Category.FUTURE -> futureList
                Category.OTHER -> otherList
            }
            println(currentWord)
            println("This is the filter: $filter")
            pairList =
                pairList.plus(currentWord.formalities.formalHigh.conjugation.filter { it.detailedTense in filter })
            pairList =
                pairList.plus(currentWord.formalities.formalLow.conjugation.filter { it.detailedTense in filter })
            pairList =
                pairList.plus(currentWord.formalities.informalLow.conjugation.filter { it.detailedTense in filter })
            return pairList

        }
    }
}