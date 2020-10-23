package com.example.collegex.data

import java.util.*

class SampleDataClass {

    companion object{
        private val sampleText1 = "a simple note"
        private val sampleText2 = "a note \n a feed"
        private val sampleText3 = "n publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used before final copy is available, but it may also be used to temporarily replace copy in a process called greeking, which allows designers to consider form without the meaning " +
                "of the text influencing the design.\n".trimIndent()


        private fun getDate(diff : Long): Date{

            return Date(Date().time + diff)
        }

        fun getNote()= arrayListOf(
            NoteEntity(1, getDate(0), sampleText1 ),
            NoteEntity(2, getDate(1), sampleText2 ),
            NoteEntity(3, getDate(2), sampleText3 )

        )
    }
}