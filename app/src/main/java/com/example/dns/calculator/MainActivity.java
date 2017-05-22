package com.example.dns.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.answer);

    }

    public void Click(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.ac:
            {
                textView.setText("0");
                break;
            }
            case R.id.c:
            {
                char[] str = textView.getText().toString().toCharArray();
                String s;
                if(str.length==1||str.equals("0"))
                {
                    s="0";
                }
                else
                {
                    s = new String(str,0,str.length-1);
                }
                textView.setText(s);
                break;
            }
            case R.id.dot:
            {
                textView.setText(textView.getText()+".");
                break;
            }
            case R.id.multi:
            {
                textView.setText(textView.getText()+"*");
                break;
            }
            case R.id.seven:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("7");
                }
                else
                {
                    textView.setText(textView.getText()+"7");
                }
                break;
            }
            case R.id.eight:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("8");
                }
                else
                {
                    textView.setText(textView.getText()+"8");
                }
                break;
            }
            case R.id.nine:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("9");
                }
                else
                {
                    textView.setText(textView.getText()+"9");
                }
                break;
            }
            case R.id.divide:
            {
                textView.setText(textView.getText()+"/");
                break;
            }
            case R.id.four:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("4");
                }
                else
                {
                    textView.setText(textView.getText()+"4");
                }
                break;
            }
            case R.id.five:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("5");
                }
                else
                {
                    textView.setText(textView.getText()+"5");
                }
                break;
            }
            case R.id.six:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("6");
                }
                else
                {
                    textView.setText(textView.getText()+"6");
                }
                break;
            }
            case R.id.plus:
            {
                textView.setText(textView.getText()+"+");
                break;
            }
            case R.id.one:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("1");
                }
                else
                {
                    textView.setText(textView.getText()+"1");
                }
                break;
            }
            case R.id.two:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("2");
                }
                else
                {
                    textView.setText(textView.getText()+"2");
                }
                break;
            }
            case R.id.three:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("3");
                }
                else
                {
                    textView.setText(textView.getText()+"3");
                }
                break;
            }
            case R.id.minus:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("-");
                }
                else
                {
                    textView.setText(textView.getText()+"-");
                }
                break;
            }
            case R.id.left_bracket:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("(");
                }
                else
                {
                    textView.setText(textView.getText()+"(");
                }
                break;
            }
            case R.id.right_bracket:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("0");
                }
                else
                {
                    textView.setText(textView.getText()+")");
                }
                break;
            }
            case R.id.zero:
            {
                String s = textView.getText().toString();
                if(s.equals("0"))
                {
                    textView.setText("0");
                }
                else
                {
                    textView.setText(textView.getText()+"0");
                }
                break;
            }
            case R.id.button_calc:
            {
                Calculator calculator = new Calculator(textView.getText().toString());
                try
                {
                    textView.setText(String.valueOf(calculator.Calculate()));
                } catch (DivideNullException e)
                {
                    textView.setText("0");
                    Toast.makeText(MainActivity.this,e.getMessage()+" "+String.valueOf(e.getNumber())+"/0",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (OperationErrorException e) {
                    textView.setText("0");
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}