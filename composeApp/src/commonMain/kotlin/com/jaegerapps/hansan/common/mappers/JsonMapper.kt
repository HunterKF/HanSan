package com.jaegerapps.hansan.common.mappers

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordEntity
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.common.models.stringToType

fun WordEntity.toWordModel(): WordModel {
    return WordModel(
        dictionaryWord = dictionary_form,
        definition = dictionary_definition,
        type = stringToType(type),
        fhPresentDeclarative = WordTenseModel(
            formal_high_present_declarative,
            Tense.PRESENT_DECLARATIVE,
            formality = Formality.FORMAL_HIGH
        ),
        fhPastDeclarative = WordTenseModel(
            formal_high_past_declarative,
            Tense.PAST_DECLARATIVE,
            formality = Formality.FORMAL_HIGH
        ),
        fhFutureDeclarative = WordTenseModel(
            formal_high_future_declarative,
            Tense.FUTURE_DECLARATIVE,
            formality = Formality.FORMAL_HIGH
        ),
        flPresentDeclarative = WordTenseModel(
            formal_low_present_declarative,
            Tense.PRESENT_DECLARATIVE,
            formality = Formality.FORMAL_LOW
        ),
        flPastDeclarative = WordTenseModel(
            formal_low_past_declarative,
            Tense.PAST_DECLARATIVE,
            formality = Formality.FORMAL_LOW
        ),
        flFutureDeclarative = WordTenseModel(
            formal_low_future_declarative,
            Tense.FUTURE_DECLARATIVE,
            formality = Formality.FORMAL_LOW
        ),
        ilPresentDeclarative = WordTenseModel(
            informal_low_present_declarative,
            Tense.PRESENT_DECLARATIVE,
            formality = Formality.INFORMAL_LOW
        ),
        ilPastDeclarative = WordTenseModel(
            informal_low_past_declarative,
            Tense.PAST_DECLARATIVE,
            formality = Formality.INFORMAL_LOW
        ),
        ilFutureDeclarative = WordTenseModel(
            formal_high_past_declarative,
            Tense.FUTURE_DECLARATIVE,
            formality = Formality.INFORMAL_LOW
        ),

        )
}

fun TenseEntity.toTenseModel(): TenseModel {
    return TenseModel(
        getTenseFromString(tense),
        getFormalityFromString(formality),
        conjugation,
        explanation,
        example_gada,
        example_boda,
        example_mokda,
        example_hada
    )
}