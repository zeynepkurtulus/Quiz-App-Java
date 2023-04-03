package com.sabanciuniv.quixapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart : Button = findViewById(R.id.btn_start)
        val etName : EditText = findViewById(R.id.et_name)
        btnStart.setOnClickListener {
            if (etName.text.isEmpty()){
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_LONG).show()
            }
            else{ // if there is a name entered we move to questions so a different screen
                // here we need to create an intend intend will tell us which activity to move to: we want to move to the questions activity
                // inside the brackets: from where I am going from to where I want to go to
                val givenName = "Welcome To The Quiz App " + etName.text.toString() + " !"
                Toast.makeText(this,givenName, Toast.LENGTH_LONG).show()
               val intent : Intent = Intent(this, QuizQuestionsActivity::class.java)
                // we need to start the intent
                 // an intent can have extra info to store:
                intent.putExtra(Constants.USER_NAME, etName.text.toString()) // accessing info of the user name from the first page the user has entered it
                startActivity(intent) // this will move us to the other screen but it will not close the current screen we also need to close the current screen
                finish() // önceki sayfayı kapatır back tuşuyla geri dönemezsin, back e basarsan uygulamadan çıkar


            }

        }
    }
}