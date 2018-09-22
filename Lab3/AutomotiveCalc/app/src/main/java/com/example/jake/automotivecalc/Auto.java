package com.example.jake.automotivecalc;

public class Auto {
    static final double STATE_TAX = 0.07;
    static final double INTEREST_RATE = 0.09;

    private double price;
    private double downPayment;
    private int loanTerm;

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setDownPayment(double down){
        this.downPayment = down;
    }

    public double getDownPayment(){
        return downPayment;
    }

    public void setLoanTerm(String term){
        if(term.contains("2")){
            loanTerm = 2;
        }else if(term.contains("3")){
            loanTerm = 3;
        } else {
            loanTerm = 4;
        }
    }

    public int getLoanTerm(){
        return loanTerm;
    }

    public double taxAmount(){
        return price * STATE_TAX;
    }

    public double totalCost(){
        return price + taxAmount();
    }

    public double borrowedAmount(){
        return totalCost() - downPayment;
    }

    public double interestAmount(){
        return borrowedAmount() * INTEREST_RATE;
    }

    public double monthlyPayment(){
        return borrowedAmount() / (loanTerm * 12);
    }
}
