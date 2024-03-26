package com.jaegerapps.hansan.screens.practice.presentation.components.uimodel


sealed class CharacterKeyModel(
    val key: String,
    val shiftKey: String? = null,
) {
    object Yo : CharacterKeyModel(
        key = "ㅛ",
    )

    object Yeo : CharacterKeyModel(
        key = "ㅕ",
    )

    object Ya : CharacterKeyModel(
        key = "ㅑ",
    )

    object Eh : CharacterKeyModel(
        key = "ㅐ",
    )

    object Ea : CharacterKeyModel(
        key = "ㅔ",
    )

    object Oh : CharacterKeyModel(
        key = "ㅗ",
    )

    object Ou : CharacterKeyModel(
        key = "ㅓ",
    )

    object Ah : CharacterKeyModel(
        key = "ㅏ",
    )

    object Ee : CharacterKeyModel(
        key = "ㅣ",
    )

    object Yoo : CharacterKeyModel(
        key = "ㅠ",
    )

    object Oo : CharacterKeyModel(
        key = "ㅜ",
    )

    object Eu : CharacterKeyModel(
        key = "ㅡ",
    )

    object Bieup : CharacterKeyModel(
        key = "ㅂ",
        shiftKey = "ㅃ"
    )

    object Jieut : CharacterKeyModel(
        key = "ㅈ",
        shiftKey = "ㅉ"
    )

    object Dieut : CharacterKeyModel(
        key = "ㄷ",
        shiftKey = "ㄸ"
    )

    object Gieuk : CharacterKeyModel(
        key = "ㄱ",
        shiftKey = "ㄲ"
    )

    object Shieut : CharacterKeyModel(
        key = "ㅅ",
        shiftKey = "ㅆ"
    )

    object Mieum : CharacterKeyModel(
        key = "ㅁ",
    )

    object Nieun : CharacterKeyModel(
        key = "ㄴ",
    )

    object Nieung : CharacterKeyModel(
        key = "ㅇ",
    )

    object Rieul : CharacterKeyModel(
        key = "ㄹ",
    )

    object Hieut : CharacterKeyModel(
        key = "ㅎ",
    )

    object Kiiuk : CharacterKeyModel(
        key = "ㅋ",
    )
    object Tiuut : CharacterKeyModel(
        key = "ㅌ",
    )

    object Chieut : CharacterKeyModel(
        key = "ㅊ",
    )

    object Piuop : CharacterKeyModel(
        key = "ㅍ",
    )
    companion object {

        val topRow = listOf(
            Bieup,
            Jieut,
            Dieut,
            Gieuk,
            Shieut,
            Yo,
            Yeo,
            Ya,
            Eh,
            Ea
        )
        val middleRow = listOf(
            Mieum,
            Nieun,
            Nieung,
            Rieul,
            Hieut,
            Oh,
            Ou,
            Ah,
            Ee
        )
        val bottomRow = listOf(
            Kiiuk,
            Tiuut,
            Chieut,
            Piuop,
            Yoo,
            Oo,
            Eu
        )
    }

}