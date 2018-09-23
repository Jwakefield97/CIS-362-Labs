package com.example.jake.flipcardsanimated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Button answerBtn = findViewById(R.id.button2);
        answerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent showAnswer = new Intent(AnswerActivity.this, QuestionActivity.class);
                startActivity(showAnswer);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        overridePendingTransition(R.anim.answer_out, R.anim.question_in);
    }
}
