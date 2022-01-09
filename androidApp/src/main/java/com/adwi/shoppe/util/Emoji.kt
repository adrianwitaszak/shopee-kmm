package com.adwi.shoppe.util

object Emoji {

    val smiley = getEmojiByUnicode(0x1F60A)
    val heart = getEmojiByUnicode(0x2764)
    val loveFace = getEmojiByUnicode(0x1F60D)
    val funnyFace = getEmojiByUnicode(0x1F602)
    val yummyFace = getEmojiByUnicode(0x1F60B)
    val claps = getEmojiByUnicode(0x1F44F)
    val fire = getEmojiByUnicode(0x1F525)
    val angryFace = getEmojiByUnicode(0x1F621)
    val shittyFace = getEmojiByUnicode(0x1F4A9)
    val ghost = getEmojiByUnicode(0x1F47B)
    val alien = getEmojiByUnicode(0x1F47D)
    val thumbsUp = getEmojiByUnicode(0x1F44D)
    val thumbsDown = getEmojiByUnicode(0x1F44E)
    val punch = getEmojiByUnicode(0x1F44A)
    val brokenHeart = getEmojiByUnicode(0x1F494)


    fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }
}