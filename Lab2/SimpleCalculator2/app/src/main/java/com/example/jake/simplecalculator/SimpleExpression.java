package com.example.jake.simplecalculator;

public class SimpleExpression {
    private Integer mOperand1;
    private Integer mOperand2;
    private String mOperator;
    private Integer mValue;


    public SimpleExpression(){
        mOperand1 = 0;
        mOperand2 = 0;
        mOperator = "+";
        mValue = 0;
    }


    public Integer getmOperand1() {
        return mOperand1;
    }

    public void setmOperand1(Integer mOperand1) {
        this.mOperand1 = mOperand1;
    }

    public Integer getmOperand2() {
        return mOperand2;
    }

    public void setmOperand2(Integer mOperand2) {
        this.mOperand2 = mOperand2;
    }

    public String getmOperator() {
        return mOperator;
    }

    public void setmOperator(String mOperator) {
        this.mOperator = mOperator;
    }

    public Integer getValue() {
        computeValue();
        return mValue;
    }

    public void clearOperands(){
        mOperand1 = 0;
        mOperand2 = 0;
    }

    private void computeValue(){
        mValue = 0;
        if(mOperator.contentEquals("+")){
            mValue = mOperand1 + mOperand2;
        }else if (mOperator.contentEquals("-")){
            mValue = mOperand1 - mOperand2;
        }else if (mOperator.contentEquals("x")){
            mValue = mOperand1 * mOperand2;
        }else if (mOperator.contentEquals("/") && mOperand2 != 0){
            mValue = mOperand1 / mOperand2;
        }else{
            mValue = mOperand1 % mOperand2;
        }
    }
}
