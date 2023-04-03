package com.sabanciuniv.quixapp

//in this class I can declare what I need inside the question for our questions we want to have an image question itself and options

data class Question(
    val id : Int, // id of the question so tat we know which question we are at
    val question : String,
    val image : Int,
    val optOne : String,
    val optTwo : String,
    val optThree : String,
    val optFour : String,
    val correctAnswer : Int


)
