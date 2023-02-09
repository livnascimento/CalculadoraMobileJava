package com.estudo.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button zero, one, two, three, four, five, six, seven, eight, nine, dot,  clear;

    private ImageButton backspace, exe, subtraction, addition, multiplication, division, percent, parentheses;

    private TextView txtExpression, txtResult;

    private boolean closeParentheses = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startComponents();
        getSupportActionBar().hide();

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        dot.setOnClickListener(this);
        percent.setOnClickListener(this);
        parentheses.setOnClickListener(this);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtExpression.setText("");
                txtResult.setText("");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView expression = findViewById(R.id.txt_expression);
                String str = expression.getText().toString();
                if (!str.isEmpty()){
                    str = str.substring(0, str.length() - 1);
                    expression.setText(str);
                }
                txtResult.setText("");
            }
        });

        exe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Expression expression = new ExpressionBuilder(txtExpression.getText().toString()).build();
                    double resultado = expression.evaluate();
                    long longResult = (long) resultado;

                    if (resultado == (double) longResult) txtResult.setText((CharSequence) String.valueOf((longResult)));
                    else txtResult.setText((CharSequence) String.valueOf(resultado));
                } catch (Exception e) {
                    txtResult.setText("Syntax Error");
                }
            }
        });
    }

    private void startComponents() {
        zero = findViewById(R.id.btn_zero);
        one = findViewById(R.id.btn_one);
        two = findViewById(R.id.btn_two);
        three = findViewById(R.id.btn_three);
        four = findViewById(R.id.btn_four);
        five = findViewById(R.id.btn_five);
        six = findViewById(R.id.btn_six);
        seven = findViewById(R.id.btn_seven);
        eight = findViewById(R.id.btn_eight);
        nine = findViewById(R.id.btn_nine);

        addition = findViewById(R.id.btn_addition);
        subtraction = findViewById(R.id.btn_subtraction);
        multiplication = findViewById(R.id.btn_multiplication);
        division = findViewById(R.id.btn_division);

        dot = findViewById(R.id.btn_dot);
        exe = findViewById(R.id.btn_exe);
        backspace = findViewById(R.id.btn_backspace);
        clear = findViewById(R.id.btn_clear);
        parentheses = findViewById(R.id.btn_parentheses);

        txtExpression = findViewById(R.id.txt_expression);
        txtResult = findViewById(R.id.txt_result);
    }

    public void writeExpression(String str, boolean cls) {

        if (txtResult.getText().equals("")) txtExpression.setText(" ");

        if (cls) {
            txtResult.setText(" ");
            txtExpression.append(str);
        } else {
            txtExpression.append(txtResult.getText());
            txtExpression.append(str);
            txtResult.setText(" ");
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_zero:
                writeExpression("0", true);
                break;
            case R.id.btn_one:
                writeExpression("1", true);
                break;
            case R.id.btn_two:
                writeExpression("2", true);
                break;
            case R.id.btn_three:
                writeExpression("3", true);
                break;
            case R.id.btn_four:
                writeExpression("4", true);
                break;
            case R.id.btn_five:
                writeExpression("5", true);
                break;
            case R.id.btn_six:
                writeExpression("6", true);
                break;
            case R.id.btn_seven:
                writeExpression("7", true);
                break;
            case R.id.btn_eight:
                writeExpression("8", true);
                break;
            case R.id.btn_nine:
                writeExpression("9", true);
                break;

            case R.id.btn_addition:
                writeExpression("+ ", false);
                break;
            case R.id.btn_subtraction:
                writeExpression("- ", false);
                break;
            case R.id.btn_multiplication:
                writeExpression("* ", false);
                break;
            case R.id.btn_division:
                writeExpression("/ ", false);
                break;
            case R.id.btn_dot:
                writeExpression(".", true);
                break;
            case R.id.btn_parentheses:
                if (!closeParentheses){
                    writeExpression("(", false);
                    closeParentheses = true;
                } else {
                    writeExpression(") ", false);
                    closeParentheses = false;
                }
        }

    }
}