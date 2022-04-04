package com.example.tipcalculatorv1;

public class TipCalculator
{
    private float tip;
    private float bill;
    private int guest;

    public TipCalculator(float newTip, float newBill, int newGuest ) {
        setTip( newTip );
        setBill( newBill );
    }

    public float getTip() {
        return tip;
    }

    public float getBill() {
        return bill;
    }

    public int getGuest() {return guest;}

    public void setTip( float newTip ) {
        if( newTip > 0 )
            tip = newTip;
    }

    public void setBill( float newBill ) {
        if( newBill > 0 )
            bill = newBill;
    }

    public void setGuest(int newGuest) {
        if (newGuest > 0)
            guest = newGuest;
    }

    public float tipAmount() {
        return bill * tip;
    }

    public float totalAmount() {
        return bill + tipAmount( );
    }

    public float tipPerGuestAmount() {return tipAmount() / guest;}

    public float totalPerGuestAmount() {return totalAmount() / guest;}
}
