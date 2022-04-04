package com.example.tipcalculatorv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

// In version 1, we complete the GUI, adding two labels and two TextViews to display the
// tip amount and total amount and we added one button
// Version 2, we wil add colors, center some text, an add a few style elements
// Version 3, add click event for calculations
// Version 4, the totals are updated after any key is pressed

public class MainActivity extends AppCompatActivity {

    private static final String MA = "MainActivity";
    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;
    private EditText numberGuestEditText;
    public final static int SPACING_HORIZONTAL = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(MA, "Inside onCreate");
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f, 100f, 5);
        setContentView(R.layout.activity_main);
        Configuration config = getResources( ).getConfiguration( );
        modifyLayout( config );

        billEditText = findViewById(R.id.et_Bill_Amount);
        tipEditText = findViewById(R.id.et_Enter_Tip);
        numberGuestEditText = findViewById(R.id.et_Enter_No_Guests);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
        numberGuestEditText.addTextChangedListener(tch);
    }



    @Override
    public void onConfigurationChanged( Configuration newConfig ) {
        Log.w(MA, "Inside onConfigurationChanged Method");
        super.onConfigurationChanged( newConfig );
        modifyLayout( newConfig );
    }

    private void modifyLayout(Configuration newConfig) {
        Log.w(MA, "Inside modifyLayout");
        //Reference to Red Line
        View redLine = findViewById(R.id.red_line);
        ViewGroup.MarginLayoutParams redLineParams = (ViewGroup.MarginLayoutParams) redLine.getLayoutParams();
        //Reference Input Labels
        TextView billInputLabelTextView = findViewById(R.id.tv_Bill);
        TextView tipInputLabelTextView = findViewById(R.id.tv_Tip);
        TextView guestInputLabelTextView = findViewById(R.id.tv_No_Guests_Label);


        //Reference Output Labels
        TextView tipTextView = findViewById(R.id.tv_Tip_Total);
        TextView totalTextView = findViewById(R.id.tv_Bill_Total);
        TextView guestTipTextView = findViewById(R.id.tv_Tip_Per_Guest_Output);
        TextView guestTotalTextView = findViewById(R.id.tv_Total_Per_Guest_Output);

        //Reference Output TextViews
        TextView totalTipOutputTextView = findViewById(R.id.tv_Bill);
        TextView tipPerGuestOutputTextView = findViewById(R.id.tv_Bill);
        TextView totalCostOutputTextView= findViewById(R.id.tv_Bill);
        TextView totalPerGuestOutputTextView = findViewById(R.id.tv_Bill);

        //Reference EditText
        EditText billET = findViewById(R.id.et_Bill_Amount);
        EditText tipET = findViewById(R.id.et_Enter_Tip);
        EditText numberGuestET = findViewById(R.id.et_Enter_No_Guests);
        //EditText Reference to Id already made
        ViewGroup.MarginLayoutParams etBill = (ViewGroup.MarginLayoutParams) billET.getLayoutParams();
        ViewGroup.MarginLayoutParams etTip = (ViewGroup.MarginLayoutParams) tipET.getLayoutParams();
        ViewGroup.MarginLayoutParams etGuest = (ViewGroup.MarginLayoutParams) numberGuestET.getLayoutParams();

        //Set Param references
        ViewGroup.MarginLayoutParams billInputLabel = (ViewGroup.MarginLayoutParams) billInputLabelTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams tipInputLabel = (ViewGroup.MarginLayoutParams) tipInputLabelTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams guestInputLabel = (ViewGroup.MarginLayoutParams) guestInputLabelTextView.getLayoutParams();

        ViewGroup.MarginLayoutParams tipTotalLabel = (ViewGroup.MarginLayoutParams) tipTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams totalTipOutput = (ViewGroup.MarginLayoutParams) totalTipOutputTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams tipPerGuestLabel = (ViewGroup.MarginLayoutParams) guestTipTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams tipPerGuestOutput = (ViewGroup.MarginLayoutParams) tipPerGuestOutputTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams totalLabel = (ViewGroup.MarginLayoutParams) totalTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams totalCostOutput = (ViewGroup.MarginLayoutParams) totalCostOutputTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams totalPerGuestLabel = (ViewGroup.MarginLayoutParams) guestTotalTextView.getLayoutParams();
        ViewGroup.MarginLayoutParams totalPerGuestOutput = (ViewGroup.MarginLayoutParams) totalPerGuestOutputTextView.getLayoutParams();


        if( newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            redLineParams.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            etBill.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            etTip.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            etGuest.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            billInputLabel.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            tipInputLabel.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            guestInputLabel.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            tipTotalLabel.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            totalTipOutput.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            tipPerGuestLabel.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            tipPerGuestOutput.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            totalLabel.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            totalCostOutput.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            totalPerGuestLabel.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            totalPerGuestOutput.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );

        }
    }
    //Called when the user clicks on the Calculate Button
    public void calculate ()
    {

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        String guestString = numberGuestEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.tv_Tip_Total);
        TextView totalTextView = findViewById(R.id.tv_Bill_Total);
        TextView guestTipTextView = findViewById(R.id.tv_Tip_Per_Guest_Output);
        TextView guestTotalTextView = findViewById(R.id.tv_Total_Per_Guest_Output);


        try {


            //convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            int numberOfGuest = Integer.parseInt(guestString);

            //update the Model
           tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            tipCalc.setGuest(numberOfGuest);

            //Ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            float tipPerGuest = tipCalc.tipPerGuestAmount();
            float totalPerGuest = tipCalc.totalPerGuestAmount();

            //Update the view with formatted tip and total amounts
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
            guestTipTextView.setText(money.format(tipPerGuest));
            guestTotalTextView.setText(money.format(totalPerGuest));

            }

        catch (NumberFormatException nfe)
        {
            // alert message
        }

    }

    private class TextChangeHandler implements TextWatcher
    {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            calculate();
        }
    }


}