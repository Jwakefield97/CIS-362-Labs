package com.example.jake.automotivecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class LoanSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);
        TextView loanReportET = findViewById(R.id.textView8);

        Intent intent = getIntent();

        String report = intent.getStringExtra("LoanReport");

        String monthlyPay = intent.getStringExtra("MonthlyPayment");
        loanReportET.setText(monthlyPay +report);
    }

    public void goDataEntry(View view) {
        finish();
    }
}
