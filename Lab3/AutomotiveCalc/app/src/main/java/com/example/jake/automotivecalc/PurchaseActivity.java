package com.example.jake.automotivecalc;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class PurchaseActivity extends AppCompatActivity {
    //Auto model
    Auto auto;

    //data being passed to LoanActivity
    String loanReport;
    String monthlyPayment;

    //layout objects
    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init class vars
        carPriceET = findViewById(R.id.editText);
        downPayET = findViewById(R.id.editText2);
        loanTermRG = findViewById(R.id.radioGroup);
        auto = new Auto();
    }

    private void collectAutoInputData(){
        auto.setPrice(Double.valueOf(carPriceET.getText().toString())); //get price
        auto.setDownPayment(Double.valueOf(downPayET.getText().toString())); //get downpayment

        //set loan term
        Integer radioId = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = findViewById(radioId);
        auto.setLoanTerm(term.getText().toString());
    }

    private void buildLoanReport(){
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1) + String.format("%.02f",auto.monthlyPayment());

        loanReport = res.getString(R.string.report_line6) + String.format("%10.02f",auto.getPrice());
        loanReport += res.getString(R.string.report_line7) + String.format("%10.02f",auto.getDownPayment());

        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", auto.taxAmount());
        loanReport += res.getString(R.string.report_line10) + String.format("%18.02f", auto.totalCost());
        loanReport += res.getString(R.string.report_line11) + String.format("%12.02f", auto.borrowedAmount());
        loanReport += res.getString(R.string.report_line12) + String.format("%12.02f", auto.interestAmount());

        loanReport += "\n\n" + res.getString(R.string.report_line8) + " " + auto.getLoanTerm() + " years.";

        loanReport += "\n\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);
    }

    public void activateLoanSummary(View view){
        collectAutoInputData();
        buildLoanReport();

        //create intent to switch to
        Intent launchReport = new Intent(this,LoanSummaryActivity.class);

        //pass these objects to the activity
        launchReport.putExtra("LoanReport",loanReport);
        launchReport.putExtra("MonthlyPayment",monthlyPayment);

        //start activity with intent
        startActivity(launchReport);
    }

}
