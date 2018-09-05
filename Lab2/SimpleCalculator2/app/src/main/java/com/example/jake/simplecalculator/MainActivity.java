package com.example.jake.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView mNumberDisplay;
    private SimpleExpression mExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumberDisplay = findViewById(R.id.textView);
        mExpression = new SimpleExpression();
    }

    public void goAC(View view){
        mExpression.clearOperands();
        mNumberDisplay.setText("0");
    }

    public void goOperand(View view){
        String val = (String) mNumberDisplay.getText();
        //had to do this differently from the book because it was parsing to null
        String digit = ((Button) view).getText().toString();
        if(val.charAt(0) == '0'){
            mNumberDisplay.setText(digit);
        }else{
            mNumberDisplay.setText((String) mNumberDisplay.getText() + digit.charAt(0));
        }
    }

    public void goOperator(View view){
        String operator = ((Button) view).getText().toString();
        try{
            String val  = (String) mNumberDisplay.getText();
            mExpression.setmOperand1((int)Integer.parseInt(val.toString()));
        }catch (NumberFormatException e){
            mExpression.setmOperand1(0);
        }
        mNumberDisplay.setText("0");
        mExpression.setmOperator(operator);
    }

    public void goCompute(View view){
        try{
            String val = (String) mNumberDisplay.getText();
            mExpression.setmOperand2((int) Integer.parseInt(val.toString()));
        } catch (NumberFormatException e){
            mExpression.setmOperand2(0);
        }
        mNumberDisplay.setText(mExpression.getValue().toString());
    }
}
