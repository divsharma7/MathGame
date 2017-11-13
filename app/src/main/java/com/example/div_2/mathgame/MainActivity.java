package com.example.div_2.mathgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timer;
    int ans=0;
    int flag=0;
    int time_flag=0;
    //char arr[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView oper=(TextView)findViewById(R.id.textView3);
        final CoordinatorLayout coord=(CoordinatorLayout)findViewById(R.id.coord);
        timer=(TextView)findViewById(R.id.timer);
        Random rand=new Random();
        int n1=(int)rand.nextInt(1000)%1000;
        int n2=(int) rand.nextInt(1000)%1000;
        TextView num1=(TextView)findViewById(R.id.textView);
        TextView num2=(TextView) findViewById(R.id.textView2);

        if(n2>n1){
            int temp=n1;
            n1=n2;
            n2=temp;
            flag=1;

        }
        num1.setText(""+n1);
        num2.setText(""+n2);
        final EditText num3=(EditText) findViewById(R.id.textView4);
        final Button submit=(Button) findViewById(R.id.button);
        submit.setText("ΣUβΜIτ");
        char[] arr={'+','-','*','/','%'};
        int pick_op=rand.nextInt(5)%5;
        oper.setText(""+arr[pick_op]);
        System.out.println(pick_op + "this is operator " + n1 + " " + n2);
        oper.setText(""+arr[pick_op]);

        switch(arr[pick_op]) {

            case '+':

                ans=n1+n2;
                break;
            case '-':
                if(flag==1){
                    ans=n2-n1;
                }
                else
                    ans=n1-n2;
                break;
            case '*':
                ans=n1*n2;
                break;
            case '/':
                if(flag==1){
                    ans=n2/n1;
                }
                else
                    ans=n1/n2;
                break;
            case '%':
                if(flag==1){
                    ans=n2%n1;
                }
                else
                    ans=n1%n2;
                break;

        }

        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000 < 6){
                    timer.setTextColor(Color.parseColor("#FFEB0050"));
                    submit.setTextColor(Color.parseColor("#FFEB0050"));
                }
                else
                { timer.setTextColor(Color.parseColor("#FF61A521"));
                    submit.setTextColor(Color.parseColor("#FF61A521"));}




                timer.setText("Timer: " +String.valueOf(millisUntilFinished / 1000));


            }
            public void onFinish() {
                time_flag=1;
                Snackbar snackbar = Snackbar
                        .make(coord, "Try Again!! Time Up", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Restart", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = getIntent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(intent);
                            }
                        });
                snackbar.show();

            }
        }.start();


        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(time_flag==1){
                    return;
                }

                if(num3.getText().toString().length()==0){
                    return;
                }
                if(Integer.parseInt(num3.getText().toString())==ans){
                    Log.d("Hi i am ghere","TAG");
                    Snackbar snackbar = Snackbar
                            .make(coord, "Congrats! Got it right!!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Restart", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                  Intent intent = getIntent();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(intent);
                                }
                            });
                    snackbar.show();
                }


                else{

                    Snackbar snackbar = Snackbar
                            .make(coord, "Oops, Try Again!!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Restart", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = getIntent();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(intent);

                                }
                            });

                    snackbar.show();
                }
            }
        });






    }
}
