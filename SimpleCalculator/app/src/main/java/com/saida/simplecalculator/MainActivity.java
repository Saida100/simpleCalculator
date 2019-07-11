package com.saida.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button btnClear, btnDivide, btnMultiPy,
            btnMinus, btnPlus, btnPoint,
            btnEqual, btnSeven, btnEight,
            btnNine, btnFour, btnFive,
            btnSix, btnOne, btnTwo, btnThree, btnZero;

    EditText editTextResult;
    ImageButton btnBackSpace;
    int lenValue1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnOne.getText().toString());
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnTwo.getText().toString());
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnThree.getText().toString());
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnFour.getText().toString());
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnFive.getText().toString());


            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnSix.getText().toString());


            }
        });
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnSeven.getText().toString());


            }
        });
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnEight.getText().toString());
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendButtonName(btnNine.getText().toString());


            }
        });


        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextResult.getText().toString().trim();
                if (!inputText.contains("=") && (!inputText.endsWith("+0") &&
                        !inputText.endsWith("-0") &&
                        !inputText.endsWith("*0") &&
                        !inputText.endsWith(":0"))
                        && (!inputText.equals("0"))
                ) {
                    appendButtonName("0");
                }

            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextResult.setText("");

            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonName("+");


            }
        });


        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonName("-");

            }
        });
        btnMultiPy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonName("*");

            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonName(":");


            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextResult.getText().toString().trim();
                int len = inputText.length();
                if (len > 0) {
                    if (!inputText.contains("+") && !inputText.contains("-") && !inputText.contains("*") && !inputText.contains(":")) {
                        if (!inputText.contains(".")) {
                            editTextResult.append(".");
                        }
                    } else if ((inputText.contains("+") && !inputText.substring(inputText.indexOf("+")).contains("."))
                            || (inputText.contains("-") && !inputText.substring(inputText.indexOf("-")).contains("."))
                            || (inputText.contains("*") && !inputText.substring(inputText.indexOf("*")).contains("."))
                            || (inputText.contains(":") && !inputText.substring(inputText.indexOf(":")).contains("."))
                    ) {
                        editTextResult.append(".");

                    }

                }

            }


        });


        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextResult.getText().toString().trim();
                int len = text.length();
                setBtnEqual(btnEqual.getText().toString());

            }


        });
        btnBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextResult.getText().toString();
                int len = editTextResult.getText().toString().trim().length();
                if (len > 0) {
                    text = text.substring(0, text.length() - 1);
                    editTextResult.setText(text);
                    editTextResult.setSelection(text.length());
                }
            }
        });

    }


    public void setBtnEqual(String buttonName) {
        int len = editTextResult.getText().length();
        String getText = editTextResult.getText().toString().trim();
        if (len > 0 && (getText.contains("+") || getText.contains("-") || getText.contains("*") || getText.contains(":"))
                && !String.valueOf(getText.charAt(len - 1)).equals("+")
                && !String.valueOf(getText.charAt(len - 1)).equals("-")
                && !String.valueOf(getText.charAt(len - 1)).equals("*")
                && !String.valueOf(getText.charAt(len - 1)).equals(":")
                && !getText.contains("=")) {

            editTextResult.append(buttonName);
            calculate(getText);

        }

    }

    public void operationButtonName(String operation) {
        String getText = editTextResult.getText().toString().trim();
        if (getText.length() > 0
                && !getText.contains("+")
                && !getText.contains("-")
                && !getText.contains("*")
                && !getText.contains(":")
                && !getText.contains("=")) {
            editTextResult.append(operation);
        }
    }


    public void calculate(String text) {
        String[] array;
        Double resultDouble;
        Long resultLong;
        String result;
        if (text.contains("+")) {
            array = text.split(Pattern.quote("+"));
            if (array[0].contains(".") || array[1].contains(".")) {
                resultDouble = Double.parseDouble(array[0]) + Double.parseDouble(array[1]);
                result = String.format("%.2f", resultDouble);
                result = String.valueOf(resultDouble);
            } else {
                resultLong = Long.valueOf(array[0]) + Long.valueOf(array[1]);
                result = String.valueOf(resultLong);

            }
            editTextResult.append(String.valueOf(result));

        }
        if (text.contains("-")) {
            array = text.split(Pattern.quote("-"));
            if (array[0].contains(".") || array[1].contains(".")) {
                resultDouble = Double.parseDouble(array[0]) - Double.parseDouble(array[1]);
                result = formatDouble(resultDouble);

            } else {
                resultLong = Long.parseLong(array[0]) - Long.parseLong(array[1]);
                result = String.valueOf(resultLong);
            }
            editTextResult.append(String.valueOf(result));
        }

        if (text.contains("*")) {
            array = text.split(Pattern.quote("*"));
            if (array[0].contains(".") || array[1].contains(".")) {
                resultDouble = Double.parseDouble(array[0]) * Double.parseDouble(array[1]);
                result = formatDouble(resultDouble);
            } else {
                resultLong = Long.parseLong(array[0]) * Long.parseLong(array[1]);
                result = String.valueOf(resultLong);
            }
            editTextResult.append(result);


        }
        if (text.contains(":")) {
            array = text.split(Pattern.quote(":"));
            if (array[1].equals("0") || array[1].contains(".0")) {
                editTextResult.append("division by zero");
            } else {
                resultDouble = Double.parseDouble(array[0]) / Double.parseDouble(array[1]);
                result = formatDouble(resultDouble);
                editTextResult.append(String.valueOf(result));


            }

        }


    }

    public String formatDouble(Double resultDouble) {
        String result;
        result = String.format("%.2f", resultDouble);
        return result;

    }


    public void appendButtonName(String buttonName) {
        String inputText = editTextResult.getText().toString().trim();
        if (!inputText.contains("+")
                && !inputText.contains("-")
                && !inputText.contains("*")
                && !inputText.contains(":")
                && !inputText.contains("=")) {
            lenValue1 = inputText.length();
            checkLenText(inputText, buttonName, lenValue1);

        } else {
            String textPart2 = inputText.substring(lenValue1 + 1);
            int len = textPart2.length();
            checkLenText(textPart2, buttonName, len);

        }

    }

    public void checkLenText(String text, String buttonName, int len) {
        int lenAfterPoint;
        if (!text.contains(".")) {
            if (len < 10) {
                editTextResult.append(buttonName);
            }
        } else {
            lenAfterPoint = text.substring(text.indexOf(".")).length();
            if (lenAfterPoint <= 10 && lenValue1 < 15) {
                editTextResult.append(buttonName);

            }
        }


    }


    private void initViews() {
        editTextResult = findViewById(R.id.result);
        btnClear = findViewById(R.id.clear);
        btnBackSpace = findViewById(R.id.backSpace);
        btnDivide = findViewById(R.id.divide);
        btnMultiPy = findViewById(R.id.multipy);
        btnMinus = findViewById(R.id.minus);
        btnPlus = findViewById(R.id.plus);
        btnPoint = findViewById(R.id.point);
        btnEqual = findViewById(R.id.equal);
        btnSeven = findViewById(R.id.seven);
        btnEight = findViewById(R.id.eight);
        btnNine = findViewById(R.id.nine);
        btnFour = findViewById(R.id.four);
        btnFive = findViewById(R.id.five);
        btnSix = findViewById(R.id.six);
        btnThree = findViewById(R.id.three);
        btnTwo = findViewById(R.id.two);
        btnOne = findViewById(R.id.one);
        btnZero = findViewById(R.id.zero);

    }


}
