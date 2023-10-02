package com.calculator;

import java.util.*;

public class Main {

    static String currentLang = "";
    static String[] arInputNumbers = {"", ""};
    static String[] arArabNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] arRomeNumbers = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static String operator = "";

    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        System.out.println("Input:");
        String input = s.next();
        String result = calc(input);
        System.out.println("Output:");
        System.out.println(result);
    }

    public static String calc(String input) throws Exception {
        for (int i = 0; i < input.length(); i++) {
            var value = Character.toString(input.charAt(i));
            boolean isOperator = false;

            if (value.equals("+")) {
                operator += value;
                isOperator = true;
            }
            if (value.equals("-")) {
                operator += value;
                isOperator = true;
            }
            if (value.equals("*")) {
                operator += value;
                isOperator = true;
            }
            if (value.equals("/")) {
                operator += value;
                isOperator = true;
            }

            if (!isOperator) {
                if (operator.isEmpty()) {
                    arInputNumbers[0] += value;
                } else {
                    arInputNumbers[1] += value;
                }
                if (currentLang.isEmpty()) {
                    for (int j = 0; j < arArabNumbers.length; j++) {
                        if (arArabNumbers[j].equals(value)) {
                            currentLang = "arab";
                        }
                        if (arRomeNumbers[j].equals(value)) {
                            currentLang = "rome";
                        }
                    }
                } else {
                    for (int j = 0; j < arArabNumbers.length; j++) {
                        if (arArabNumbers[j].equals(value)) {
                            if (!currentLang.equals("arab")) {
                                errorMessage("используются одновременно разные системы счисления");
                            }
                        }
                        if (arRomeNumbers[j].equals(value)) {
                            if (!currentLang.equals("rome")) {
                                errorMessage("используются одновременно разные системы счисления");
                            }
                        }
                    }
                }
            }
        }

        if(operator.isEmpty()) {
            errorMessage("строка не является математической операцией");
        }

        if(operator.length() > 1) {
            errorMessage("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        int a = 0;
        int b = 0;

        if (currentLang.equals("arab")) {
            a = Integer.valueOf(arInputNumbers[0]);
            b = Integer.valueOf(arInputNumbers[1]);
        }

        if (currentLang.equals("rome")) {
            a = Arrays.asList(arRomeNumbers).indexOf(arInputNumbers[0]);
            b = Arrays.asList(arRomeNumbers).indexOf(arInputNumbers[1]);

            if (a < b && operator.equals("-")) {
                errorMessage("в римской системе нет отрицательных чисел");
            }
        }

        if (a > 10 || b > 10 || a < 1 || b < 1) {
            errorMessage("калькулятор должен принимать на вход числа от 1 до 10 включительно, не более");
        }

        int result = 0;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }

        String resultString = "";

        if (currentLang.equals("arab")) {
            resultString = String.valueOf(result);
        }

        if (currentLang.equals("rome")) {
            resultString = ConverterRoman.init(result);
        }

        return resultString;
    }

    static void errorMessage(String error) throws Exception {
        throw new Exception("throws Exception //т.к. " + error);
    }
}
