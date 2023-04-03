package com.sabanciuniv.quixapp

object Constants {

    const val  USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    // we need to have a function that accesses the questions, this function will return th questions itself so an array of Question Data class
    fun getQuestions():ArrayList<Question>
    {
        val questionsList = ArrayList<Question>()
        val q1 = Question(
            1, "What country does this flag belong to ?", R.drawable.ic_flag_of_belgium, "Belgium", "Germany", "Spain", "USA",1
        )

        val q2 = Question(
            2, "What country does this flag belong to ?", R.drawable.ic_flag_of_germany, "Switzerland", "Holland", "Germany", "USA",3
        )

        val q3 = Question(
            3, "What country does this flag belong to ?", R.drawable.ic_flag_of_argentina, "UK", "Poland", "Argentina", "Israel",3
        )
        val q4 = Question(
            4, "What country does this flag belong to ?", R.drawable.ic_flag_of_denmark, "France", "Germany", "Italy", "Denmark",4
        )

        val q5 = Question(
            5, "What country does this flag belong to ?", R.drawable.ic_flag_of_new_zealand, "New Zealand", "Italy", "Spain", "Turkey",1
        )

        val q6 = Question(
            6, "What country does this flag belong to ?", R.drawable.ic_flag_of_kuwait, "Belgium", "Kuwait", "Germany", "France",2
        )
        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        questionsList.add(q4)
        questionsList.add(q5)
        questionsList.add(q6)

        return questionsList
    }


}