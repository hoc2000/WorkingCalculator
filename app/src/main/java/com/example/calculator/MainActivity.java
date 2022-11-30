package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    Button buttonCE , buttonC, buttonBS, buttonDivine;
    Button button7, button8, button9, buttonMutiply;
    Button button4, button5, button6, buttonMinus;
    Button button1, button2, button3, buttonPlus;
    Button buttonwhat, button0, buttonDot, buttonEqual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutionTv = findViewById(R.id.solution);
        resultTv = findViewById(R.id.txtResult);

        assignId(buttonCE,R.id.btnce);
        assignId(buttonC,R.id.btnc);
        assignId(buttonBS,R.id.btnbs);
        assignId(buttonDivine,R.id.btnDivide);
        assignId(button7,R.id.btnSeven);
        assignId(button8,R.id.btnEight);
        assignId(button9,R.id.btnNine);
        assignId(buttonMutiply,R.id.btnMultiply);
        assignId(button4,R.id.btnFour);
        assignId(button5,R.id.btnFive);
        assignId(button6,R.id.btnSix);
        assignId(buttonMinus,R.id.btnSubtract);
        assignId(button1,R.id.btnOne);
        assignId(button2,R.id.btnTwo);
        assignId(button3,R.id.btnThree);
        assignId(buttonPlus,R.id.btnAdd);
        assignId(buttonwhat,R.id.btnplusorminus);
        assignId(button0,R.id.btnZero);
        assignId(buttonDot,R.id.btndot);
        assignId(buttonEqual,R.id.btnEquals);
    }

    void assignId(Button btn, int id){
        btn =findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button button =(Button) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("C")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("CEB")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}