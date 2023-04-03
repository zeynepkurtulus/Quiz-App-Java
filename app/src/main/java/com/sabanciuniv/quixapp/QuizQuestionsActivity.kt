package com.sabanciuniv.quixapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener{
    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String? = null
    private var  mCorrectAnswers : Int = 0
    private var prograssBar : ProgressBar? = null
    private var progressText : TextView?  = null // 0/9 part
    private var quizQuestion : TextView? = null
    private var img : ImageView? = null
    private var optOne : TextView? = null
    private var optTwo : TextView? = null
    private var optThree : TextView? = null
    private var optFour : TextView? = null
     private var btnSubmit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mUserName = intent.getStringExtra(Constants.USER_NAME) // intent gt me to teh other activity and helped me to receive the username constant
        prograssBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.questionId)
        quizQuestion = findViewById(R.id.questionTxt)
        img = findViewById(R.id.image)
        optOne = findViewById(R.id.opt1)
        optTwo = findViewById(R.id.opt2)
        optThree = findViewById(R.id.opt3)
        optFour = findViewById(R.id.opt4)
        btnSubmit = findViewById(R.id.btnSubmit )
        mQuestionsList = Constants.getQuestions()
        // I need to add OnClickListener to each text view so that they can be clickable
        optOne?.setOnClickListener(this)
        optTwo?.setOnClickListener(this)
        optThree?.setOnClickListener(this)
        optFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        // I need to access mu questions list which is inside constants file and I have written a function for that
        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionsView()
        val questions: Question = mQuestionsList!![mCurrentPosition-1]
        //mCurrentPosition++
        img?.setImageResource(questions.image)
        prograssBar?.progress = mCurrentPosition
        progressText?.text = "$mCurrentPosition / ${prograssBar?.max}"
        quizQuestion?.text = questions.question

        optOne?.text = questions.optOne
        optTwo?.text = questions.optTwo
        optThree?.text = questions.optThree
        optFour?.text = questions.optFour

        if (mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
         optOne?.let {
             options.add(0, it)

         }
        optTwo?.let {
            options.add(1, it)
        }

        optThree?.let {
            options.add(2, it)

        }

        optFour?.let {
            options.add(3, it)
        }

        for (opt in options){
           opt.setTextColor(Color.parseColor("#7A8089"))
            opt.typeface = Typeface.DEFAULT
            opt.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }
    //parameters are which textview we want to change and the option number
    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()
        //the option that we selected
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )

    }

    override fun onClick(view: View?) {
        when(view?.id) { // means when the text views are clicked on
            R.id.opt1 -> { // when the text view with the id opt 1 is click I want to to do the following
                optOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.opt2 -> { // when the text view with the id opt 2 is click I want to to do the following
                optTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.opt3 -> { // when the text view with the id opt 3 is click I want to to do the following
                optThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.opt4 -> { // when the text view with the id opt 4 is click I want to to do the following
                optFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            //here I want to check if the user's answer is correct or wrong
            R.id.btnSubmit -> {
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    // I have to keep setting a question as long as I have questions to ask
                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }
                        else->{
                            // if there are no questions left we should send the app tp the results activity by creating an intent
                            //Toast.makeText(this,"YOU ARE DONE!", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            startActivity(intent)
                            finish()
                        }

                    }
                }else{ // we we are not at the begginning we want to get the question from the questions list
                    val question = mQuestionsList?.get(mCurrentPosition -1) // -1 bc arraylist starts from index 0
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text = "FINISH"
                    }
                    else{
                        btnSubmit?.text = "NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0

                }

            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        // here what we do is: assign a red or green color to the option that is selected WE DONT CHECK WHETHER THE ANSWER IS TRUE OR FALSE HERE
        when(answer){
            1->{
                optOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2->{
                optTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3->{
                optThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4->{
                optFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}