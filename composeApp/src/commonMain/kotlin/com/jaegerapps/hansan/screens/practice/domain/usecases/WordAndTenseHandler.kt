package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.FormalityType.*
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word

class WordAndTenseHandler {
    companion object {

        fun selectVerb(
            list: List<VerbModel>
        ): VerbModel {
            return list.random()
        }
        fun newWord(
            verb: VerbModel,
            targetFormality: FormalityType,
            targetTenses: List<Tense>,
        ): Word {

            val formality = when (targetFormality) {
                FORMAL_HIGH -> verb.formalities.formalHigh
                FORMAL_LOW -> verb.formalities.formalLow
                INFORMAL_LOW -> verb.formalities.informalLow
            }
            val tenses = formality.conjugation.filter { it.tense in targetTenses }
            return tenses.random()
        }

        fun newTense(list: List<TenseModel>): TenseModel {
            return list.random()
        }
    }

}